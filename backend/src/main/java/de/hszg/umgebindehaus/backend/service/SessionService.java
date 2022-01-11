package de.hszg.umgebindehaus.backend.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import de.hszg.umgebindehaus.backend.api.error.ResourceNotFoundException;
import de.hszg.umgebindehaus.backend.components.UniqueWordGenerator;
import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.model.Session;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class SessionService{

    private final UniqueWordGenerator idGenerator;
    private final ScenarioService scenarioService;

    private final Cache<String, Session> sessions;

    public SessionService(UniqueWordGenerator idGenerator, ScenarioService scenarioService){
        this.idGenerator = idGenerator;
        this.scenarioService = scenarioService;

        sessions = Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.DAYS)
                .build();
    }

    @NotNull
    public Session createSession(){
        synchronized (sessions){// this should still be full synchronized to ensure unique keys
            String id = idGenerator.nextWord();
            while(sessions.getIfPresent(id) != null) {// should not happen but just to make sure
                id = idGenerator.nextWord();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Session session = new Session();
            session.setId(id);

            sessions.put(id, session);
            return session;
        }
    }

    @NotNull
    public Session getSessionById(@NotNull String id){
        var session = sessions.get(id, e -> {
            throw new ResourceNotFoundException(String.format("no session with id %s registered", id));
        });
        return session;
    }

    public void removeSession(@NotNull String id){
        sessions.get(id, e -> {
            throw new ResourceNotFoundException(String.format("no session with id %s registered", id));
        });
        sessions.invalidate(id);
    }

    public void editSession(@NotNull Session session, @NotNull ScenePropertiesEdit changes){
        changes.applyChanges(session);
    }

    public void loadScenario(@NotNull Session session, @NotNull Scenario scenario){
        final var loadEdit = new ScenePropertiesEdit();
        loadEdit.setNewTime(scenario.getTime());
        loadEdit.setNewTimeScale(scenario.getTimeScale());
        loadEdit.setNewAutomaticTime(scenario.getAutomaticTime());
        loadEdit.setNewAutomaticWeather(scenario.getAutomaticWeather());
        final var weather = scenario.getWeather();
        loadEdit.setNewWeatherWindSpeed(weather.getWindSpeed());
        loadEdit.setNewWeatherWindDirection(weather.getWindDirection());
        loadEdit.setNewWeatherCloudiness(weather.getCloudiness());

        editSession(session, loadEdit);
    }

    /**
     * saves the session to a scenario; ony the fields in filter are stored
     * @param src the session to save
     * @param dest the scenario to store the properties to
     * @param filter set of fields to save; valid values are:
     *               Time, TimeScale, AutomaticWeather, AutomaticTime, WeatherWindDirection, WeatherWindSpeed, WeatherCloudiness
     */
    public void saveSession(@NotNull Session src, @NotNull Scenario dest, @NotNull Set<String> filter){
        final var edit = new ScenePropertiesEdit();

        filter.forEach(prop -> {
            switch(prop){
                case "Time":
                    edit.setNewTime(src.getTime());
                    break;
                case "TimeScale":
                    edit.setNewTimeScale(src.getTimeScale());
                    break;
                case "AutomaticWeather":
                    edit.setNewAutomaticWeather(src.getAutomaticWeather());
                    break;
                case "AutomaticTime":
                    edit.setNewAutomaticTime(src.getAutomaticTime());
                    break;
                case "WeatherWindDirection":
                    edit.setNewWeatherWindDirection(src.getWeather().getWindDirection());
                    break;
                case "WeatherWindSpeed":
                    edit.setNewWeatherWindSpeed(src.getWeather().getWindSpeed());
                    break;
                case "WeatherCloudiness":
                    edit.setNewWeatherCloudiness(src.getWeather().getCloudiness());
                    break;
            }
        });

        scenarioService.editScenario(dest, edit);
    }
}