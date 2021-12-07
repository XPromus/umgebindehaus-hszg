package de.hszg.umgebindehaus.backend.service;

import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.repos.ScenarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Scenario> listAllScenariosWithName(String name){
        return scenarioRepo.findAllByName(name);
    }

    public void deleteScenario(Scenario scenario){
        scenarioRepo.getOne(scenario.getId());// throw EntityNotFoundException if it does not exist
        scenarioRepo.delete(scenario);
    }
}