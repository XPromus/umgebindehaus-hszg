package de.hszg.umgebindehaus.backend.service;

import de.hszg.umgebindehaus.backend.components.DefaultScenarios;
import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.repos.ScenarioRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ScenarioService{

    private final ScenarioRepo scenarioRepo;
    private final DefaultScenarios defaults;

    public ScenarioService(ScenarioRepo scenarioRepo, DefaultScenarios defaults){
        this.scenarioRepo = scenarioRepo;
        this.defaults = defaults;
    }

    @Transactional
    public Scenario createScenario(String name){
        final Scenario ret = new Scenario();
        ret.setName(name);

        // apply defaults
        final var defaultScenario = defaults.getDefaultScenario();
        ret.setTime(defaultScenario.getTime());
        ret.setTimeScale(defaultScenario.getTimeScale());
        ret.setAutomaticTime(defaultScenario.getAutomaticTime());
        ret.setAutomaticWeather(defaultScenario.getAutomaticWeather());
        ret.setWeather(defaultScenario.getWeather());

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
    public Scenario editScenario(@NotNull Scenario scenario, @NotNull ScenePropertiesEdit changes){
        changes.applyChanges(scenario);
        return scenarioRepo.save(scenario);
    }

    @Transactional
    public void deleteScenario(Integer scenario){
        scenarioRepo.getOne(scenario);// throw EntityNotFoundException if it does not exist
        scenarioRepo.deleteById(scenario);
    }
}