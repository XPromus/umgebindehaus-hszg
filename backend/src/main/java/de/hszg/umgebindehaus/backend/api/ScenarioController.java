package de.hszg.umgebindehaus.backend.api;


import de.hszg.umgebindehaus.backend.api.error.DuplicateNameException;
import de.hszg.umgebindehaus.backend.api.error.ResourceNotFoundException;
import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.service.ScenePropertiesEdit;
import de.hszg.umgebindehaus.backend.service.ScenarioService;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    private final ScenarioService scenarioService;

    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createScenario(@RequestBody String name) {
        Scenario scenario = null;

        try {
            scenario = scenarioService.createScenario(name);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateNameException("A Scenario with the given name already exists");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scenario);
    }

    @GetMapping("/id/{id}")
    public Scenario getScenarioById(@PathVariable Integer id) {
        Optional<Scenario> optionalScenario = scenarioService.getScenarioById(id);
        if (optionalScenario.isEmpty()) {
            throw new ResourceNotFoundException("No scenario with the id " + id + " was found.");
        }
        return optionalScenario.get();
    }

    @GetMapping("/all")
    public List<Scenario> listAllScenarios() {
        return scenarioService.listAllScenarios();
    }

    @GetMapping("/name/{name}")
    public Scenario getScenarioByName(@PathVariable String name) {
        Optional<Scenario> optionalScenario = scenarioService.getScenarioByName(name);
        if (optionalScenario.isEmpty()) {
            throw new ResourceNotFoundException("No scenario with the name " + name + " was found.");
        }
        return optionalScenario.get();
    }

    //ToDo Anpassen, sobald deleteScenario eine EntityNotFoundException wirft
    @DeleteMapping("/delete/{id}")
    public void deleteScenario(@PathVariable Integer id) {
        try {
            scenarioService.deleteScenario(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No scenario with the id " + id + " was found.");
        }
    }

    @PostMapping("/edit/{id}")
    public Scenario editScenario(@PathVariable Integer id, @RequestBody ScenePropertiesEdit changes) {
        final Optional<Scenario> scenario = scenarioService.getScenarioById(id);
        if (scenario.isEmpty()) {
            throw new ResourceNotFoundException("No scenario with the id " + id + " was found.");
        }

        return scenarioService.editScenario(scenario.get(), changes);
    }

}
