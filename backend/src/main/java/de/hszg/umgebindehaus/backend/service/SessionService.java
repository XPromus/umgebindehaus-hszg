package de.hszg.umgebindehaus.backend.service;

import de.hszg.umgebindehaus.backend.api.error.ResourceNotFoundException;
import de.hszg.umgebindehaus.backend.data.model.Session;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Service
public class SessionService{

    private final Map<String, Session> sessions = new HashMap<>();

    @NotNull
    public Session createSession(){
        synchronized (sessions){
            ;
        }
    }

    @NotNull
    public Session getSessionById(@NotNull String id){
        synchronized (sessions){
            var session = sessions.get(id);
            if(session == null)
                throw new ResourceNotFoundException(String.format("no session with id %s registered", id));
            return session;
        }
    }

    public void removeSession(@NotNull String id){
        synchronized (sessions){
            if(!sessions.containsKey(id))
                throw new ResourceNotFoundException(String.format("no session with id %s registered", id));
            sessions.remove(id);
        }
    }

    public void editSession(@NotNull String sessionId, @NotNull ScenarioEdit changes){
        final var session = getSessionById(sessionId);
        changes.applyChanges(session);
    }
}