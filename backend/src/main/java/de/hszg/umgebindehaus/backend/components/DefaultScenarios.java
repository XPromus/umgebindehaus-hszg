package de.hszg.umgebindehaus.backend.components;

import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.model.Weather;
import de.hszg.umgebindehaus.backend.service.ScenePropertiesEdit;
import de.hszg.umgebindehaus.backend.service.ScenarioService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("MagicNumber")
@Component
public class DefaultScenarios{

    public DefaultScenarios(ScenarioService scenarioService){
        this.scenarioService = scenarioService;

        initDefaults();
    }

    private final ScenarioService scenarioService;

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
        defaultWeather = new Weather();

        final Set<Scenario> set = new HashSet<>();

        final String s1Name = "Example 1";
        final Optional<Scenario> existingS1 = scenarioService.getScenarioByName(s1Name);
        final Scenario s1 = existingS1.orElseGet(() -> scenarioService.createScenario(s1Name));
        set.add(s1);
        defaultScenario = s1;

        final String s2Name = "Example 2";
        final Optional<Scenario> existingS2 = scenarioService.getScenarioByName(s2Name);
        final Scenario s2 = existingS2.orElseGet(() -> {
            Scenario newS2 = scenarioService.createScenario(s2Name);

            final ScenePropertiesEdit initEdit = new ScenePropertiesEdit();
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