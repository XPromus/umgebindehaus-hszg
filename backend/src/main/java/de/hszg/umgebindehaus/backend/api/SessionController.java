package de.hszg.umgebindehaus.backend.api;

import de.hszg.umgebindehaus.backend.data.model.Session;
import de.hszg.umgebindehaus.backend.service.ScenarioEdit;
import de.hszg.umgebindehaus.backend.service.SessionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/session")
public class SessionController{

    private final SessionService sessionService;

    public SessionController(SessionService sessionService){
        this.sessionService = sessionService;
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
    public void editSessionProps(@PathVariable String id, @RequestBody ScenarioEdit edit){
        sessionService.editSession(id, edit);
    }
}