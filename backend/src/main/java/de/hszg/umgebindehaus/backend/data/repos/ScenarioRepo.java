package de.hszg.umgebindehaus.backend.data.repos;

import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.search.ScenarioSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScenarioRepo extends JpaRepository<Scenario, Integer>, JpaSpecificationExecutor<Scenario> {

    Optional<Scenario> findByName(String name);
}