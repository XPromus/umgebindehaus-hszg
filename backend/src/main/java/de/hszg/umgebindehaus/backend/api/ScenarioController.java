package de.hszg.umgebindehaus.backend.api;


import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.service.ScenarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    private final ScenarioService scenarioService;

    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @PostMapping
    public Scenario createScenario(@RequestBody String name) {
        return scenarioService.createScenario(name);
    }

    @GetMapping("/id/{id}")
    public Scenario getScenarioById(@PathVariable Integer id) {
        return scenarioService.getScenarioById(id);
    }

    @GetMapping("/all")
    public List<Scenario> listAllScenarios() {
        return scenarioService.listAllScenarios();
    }

    @GetMapping("/name/{name}")
    public List<Scenario> listAllScenariosWithName(@PathVariable String name) {
        return scenarioService.listAllScenariosWithName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteScenario(@PathVariable Integer id) {
        scenarioService.deleteScenario(id);
    }

}
