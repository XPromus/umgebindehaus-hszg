package de.hszg.umgebindehaus.backend.service;

import de.hszg.umgebindehaus.backend.api.error.ResourceNotFoundException;
import de.hszg.umgebindehaus.backend.components.DefaultScenarios;
import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.model.Weather;
import de.hszg.umgebindehaus.backend.data.repos.ScenarioRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ScenarioService{

    private final ScenarioRepo scenarioRepo;

    public ScenarioService(ScenarioRepo scenarioRepo){
        this.scenarioRepo = scenarioRepo;
    }

    @Transactional
    public Scenario createScenario(String name){
        final Scenario ret = new Scenario();
        ret.setName(name);

        return scenarioRepo.save(ret);
    }

    public Optional<Scenario> getScenarioById(Integer id){
        return scenarioRepo.findById(id);
    }

    public List<Scenario> listAllScenarios(){
        return scenarioRepo.findAll();
    }

    public Optional<Scenario> getScenarioByName(String name){
        return scenarioRepo.findByName(name);
    }

    @Transactional
    public Scenario editScenario(ScenarioEdit changes){
        Optional<Scenario> optionalScenario = getScenarioById(changes.getScenarioId());
        if (optionalScenario.isEmpty()) {
            throw new ResourceNotFoundException("No scenario with the id " + changes.getScenarioId() + " was found.");
        }

        final Scenario scenario = optionalScenario.get();
        if(changes.getNewName() != null){
            scenario.setName(changes.getNewName());
        }
        if(changes.getNewTime() != null){
            scenario.setTime(changes.getNewTime());
        }
        if(changes.getNewTimeScale() != null){
            scenario.setTimeScale(changes.getNewTimeScale());
        }
        if(changes.getNewAutomaticWeather() != null){
            scenario.setAutomaticWeather(changes.getNewAutomaticWeather());
        }
        if(changes.getNewAutomaticTime() != null){
            scenario.setAutomaticTime(changes.getNewAutomaticTime());
        }
        final Weather weather = scenario.getWeather();
        if(changes.getNewWeatherWindDirection() != null){
            weather.setWindDirection(changes.getNewWeatherWindDirection());
        }
        if(changes.getNewWeatherWindSpeed() != null){
            weather.setWindSpeed(changes.getNewWeatherWindSpeed());
        }
        if(changes.getNewWeatherCloudiness() != null){
            weather.setCloudiness(changes.getNewWeatherCloudiness());
        }
        return scenarioRepo.save(scenario);
    }

    @Transactional
    public void deleteScenario(Integer scenario){
        scenarioRepo.getOne(scenario);// throw EntityNotFoundException if it does not exist
        scenarioRepo.deleteById(scenario);
    }
}