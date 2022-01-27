package de.hszg.umgebindehaus.backend.service;

import de.hszg.umgebindehaus.backend.api.error.ResourceNotFoundException;
import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.repos.ScenarioRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ScenarioService{

    private final ScenarioRepo scenarioRepo;

    public ScenarioService(ScenarioRepo scenarioRepo){
        this.scenarioRepo = scenarioRepo;
    }

    @Transactional
    public Scenario createScenario(String name) {
        final Scenario ret = new Scenario();
        ret.setName(name);
        //ToDo Doppelte Eingabe des selben Namen muss behandelt werden

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
    public Scenario editScenario(@NotNull Scenario scenario, @NotNull ScenePropertiesEdit changes) {
        changes.applyChanges(scenario);
        return scenarioRepo.save(scenario);
    }

    @Transactional
    public void deleteScenario(Integer scenario){
        scenarioRepo.getOne(scenario);// throw EntityNotFoundException if it does not exist
        scenarioRepo.deleteById(scenario);
    }
}