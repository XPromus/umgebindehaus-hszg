package de.hszg.umgebindehaus.backend.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.model.Weather;
import de.hszg.umgebindehaus.backend.data.repos.ScenarioRepo;
import io.swagger.v3.core.util.Json;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DefaultScenarios{

    private static final String FILE_NAME = "defaultScenarios.json";

    public DefaultScenarios(ScenarioRepo scenarioRepo){
        this.scenarioRepo = scenarioRepo;

        initDefaults();
    }

    private final ScenarioRepo scenarioRepo;

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
        final var res = new ClassPathResource(FILE_NAME);
        try(final var reader = new InputStreamReader(res.getInputStream())){
            defaultWeather = new Weather();

            final ObjectMapper deserializer = Json.mapper();
            final DataHolder data = deserializer.readValue(reader, DataHolder.class);

            assert data.defaultWeather != null;
            assert data.defaultScenarios != null;
            assert data.defaultScenarios.size() > 0;

            final var scenarios = new HashSet<Scenario>(data.defaultScenarios.size());
            Scenario firstScenario = null;
            for(final Scenario s : data.defaultScenarios){
                final var storedScenario = scenarioRepo.findByName(s.getName()).orElseGet(() -> scenarioRepo.save(s));
                scenarios.add(storedScenario);
                if(firstScenario == null)
                    firstScenario = storedScenario;
            }

            defaultWeather = data.defaultWeather;
            defaultScenario = firstScenario;
            defaultScenarios = Collections.unmodifiableSet(scenarios);
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    private static final class DataHolder{
        public Weather defaultWeather;
        public List<Scenario> defaultScenarios;
    }
}