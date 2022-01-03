package de.hszg.umgebindehaus.backend.api;

import de.hszg.umgebindehaus.backend.api.error.ResourceNotFoundException;
import de.hszg.umgebindehaus.backend.data.model.Session;
import de.hszg.umgebindehaus.backend.service.ScenePropertiesEdit;
import de.hszg.umgebindehaus.backend.service.ScenarioService;
import de.hszg.umgebindehaus.backend.service.SessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/session")
public class SessionController{

    private final SessionService sessionService;
    private final ScenarioService scenarioService;

    public SessionController(SessionService sessionService, ScenarioService scenarioService){
        this.sessionService = sessionService;
        this.scenarioService = scenarioService;
    }

    @GetMapping("/create")
    public String createNewSession(){
        return sessionService.createSession().getId();
    }

    @GetMapping("/get/{id}")
    public Session getSession(@PathVariable String id){
        return sessionService.getSessionById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSession(@PathVariable String id){
        sessionService.removeSession(id);
    }

    @PostMapping("/edit/{id}")
    public void editSessionProps(@PathVariable String id, @RequestBody ScenePropertiesEdit edit){
        final Session session = sessionService.getSessionById(id);
        sessionService.editSession(session, edit);
    }

    @PutMapping("/loadScenario/{sessionId}")
    public void loadScenario(@PathVariable String sessionId, @RequestParam Integer scenarioId){
        final Session session = sessionService.getSessionById(sessionId);

        var scenarioOptional = scenarioService.getScenarioById(scenarioId);
        if (scenarioOptional.isEmpty())
            throw new ResourceNotFoundException(String.format("scenario with id %d does not exist", scenarioId));

        sessionService.loadScenario(session, scenarioOptional.get());
    }
}