package de.hszg.umgebindehaus.backend.config;

import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.model.Weather;
import de.hszg.umgebindehaus.backend.service.ScenarioEdit;
import de.hszg.umgebindehaus.backend.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("MagicNumber")
public class DefaultScenarios{

    private static final AtomicReference<DefaultScenarios> instance = new AtomicReference<>(null);

    public static DefaultScenarios getInstance(){
        if(instance.get() == null){
            instance.set(new DefaultScenarios());
        }
        return instance.get();
    }

    private DefaultScenarios(){
        initDefaults();
    }

    @Autowired
    private ScenarioService scenarioService;

    private Weather defaultWeather;
    private Scenario defaultScenario;
    private Set<Scenario> defaultScenarios;

    public Weather getDefaultWeather(){
        return defaultWeather;
    }
    public Scenario getDefaultScenario(){
        return defaultScenario;
    }
    public Set<Scenario> getDefaultScenarios(){
        return defaultScenarios;
    }

    private void initDefaults(){
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        defaultWeather = new Weather();

        Set<Scenario> set = new HashSet<>();

        final String s1Name = "Example 1";
        Optional<Scenario> existingS1 = scenarioService.getScenarioByName(s1Name);
        Scenario s1 = existingS1.orElseGet(() -> scenarioService.createScenario(s1Name));
        set.add(s1);
        defaultScenario = s1;

        final String s2Name = "Example 2";
        Optional<Scenario> existingS2 = scenarioService.getScenarioByName(s2Name);
        Scenario s2 = existingS2.orElseGet(() -> {
            Scenario newS2 = scenarioService.createScenario(s2Name);

            ScenarioEdit initEdit = new ScenarioEdit();
            initEdit.setScenarioId(newS2.getId());
            initEdit.setNewTime(LocalDateTime.of(2021, 6, 20, 13, 0, 0));
            initEdit.setNewTimeScale(5.0);
            initEdit.setNewAutomaticWeather(Boolean.FALSE);
            initEdit.setNewWeatherWindDirection(12.0);
            initEdit.setNewWeatherWindDirection(2.5);
            initEdit.setNewWeatherCloudiness(Weather.CloudType.CLOUDY_1);
            newS2 = scenarioService.editScenario(initEdit);

            return newS2;
        });
        set.add(s2);

        defaultScenarios = Collections.unmodifiableSet(set);
    }
}