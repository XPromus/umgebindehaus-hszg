package de.hszg.umgebindehaus.backend.service;

import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.model.Weather;
import de.hszg.umgebindehaus.backend.data.repos.ScenarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScenarioService{

    private final ScenarioRepo scenarioRepo;

    public ScenarioService(ScenarioRepo scenarioRepo){
        this.scenarioRepo = scenarioRepo;
    }

    public Scenario createScenario(String name){
        final Scenario ret = new Scenario();
        ret.setName(name);

        return scenarioRepo.save(ret);
    }

    public Scenario getScenarioById(Integer id){
        return scenarioRepo.getOne(id);
    }

    public List<Scenario> listAllScenarios(){
        return scenarioRepo.findAll();
    }

    public Optional<Scenario> getScenarioByName(String name){
        return scenarioRepo.findByName(name);
    }

    public Scenario editScenario(ScenarioEdit changes){
        Scenario scenario = getScenarioById(changes.getScenarioId());
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
        Weather weather = scenario.getWeather();
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

    public void deleteScenario(Integer scenario){
        scenarioRepo.getOne(scenario);// throw EntityNotFoundException if it does not exist
        scenarioRepo.deleteById(scenario);
    }
}