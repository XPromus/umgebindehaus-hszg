package de.hszg.umgebindehaus.backend.data.repos;

import de.hszg.umgebindehaus.backend.data.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScenarioRepo extends JpaRepository<Scenario, Integer>{

    Optional<Scenario> findByName(String name);
}