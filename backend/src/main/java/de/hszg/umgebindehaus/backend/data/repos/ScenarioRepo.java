package de.hszg.umgebindehaus.backend.data.repos;

import de.hszg.umgebindehaus.backend.data.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenarioRepo extends JpaRepository<Scenario, Integer>{

    List<Scenario> findAllByName(String name);
}