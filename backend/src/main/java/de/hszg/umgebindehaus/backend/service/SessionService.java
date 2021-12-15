package de.hszg.umgebindehaus.backend.service;

import de.hszg.umgebindehaus.backend.api.error.ResourceNotFoundException;
import de.hszg.umgebindehaus.backend.components.UniqueWordGenerator;
import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.model.Session;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionService{

    private final UniqueWordGenerator idGenerator;

    private final Map<String, Session> sessions;

    public SessionService(UniqueWordGenerator idGenerator){
        this.idGenerator = idGenerator;

        sessions = new ConcurrentHashMap<>();
    }

    @NotNull
    public Session createSession(){
        synchronized (sessions){// this should still be full synchronized to ensure unique keys
            String id = idGenerator.nextWord();
            while(sessions.containsKey(id))// should not happen but just to make sure
                id = idGenerator.nextWord();

            Session session = new Session();
            session.setId(id);

            sessions.put(id, session);
            return session;
        }
    }

    @NotNull
    public Session getSessionById(@NotNull String id){
        var session = sessions.get(id);
        if(session == null)
            throw new ResourceNotFoundException(String.format("no session with id %s registered", id));
        return session;
    }

    public void removeSession(@NotNull String id){
        if(!sessions.containsKey(id))
            throw new ResourceNotFoundException(String.format("no session with id %s registered", id));
        sessions.remove(id);
    }

    public void editSession(@NotNull String sessionId, @NotNull ScenePropertiesEdit changes){
        final var session = getSessionById(sessionId);
        changes.applyChanges(session);
    }

    public void loadScenario(@NotNull String sessionId, @NotNull Scenario scenario){
        final var loadEdit = new ScenePropertiesEdit();
        loadEdit.setNewTime(scenario.getTime());
        loadEdit.setNewTimeScale(scenario.getTimeScale());
        loadEdit.setNewAutomaticTime(scenario.getAutomaticTime());
        loadEdit.setNewAutomaticWeather(scenario.getAutomaticWeather());
        final var weather = scenario.getWeather();
        loadEdit.setNewWeatherWindSpeed(weather.getWindSpeed());
        loadEdit.setNewWeatherWindDirection(weather.getWindDirection());
        loadEdit.setNewWeatherCloudiness(weather.getCloudiness());

        editSession(sessionId, loadEdit);
    }
}