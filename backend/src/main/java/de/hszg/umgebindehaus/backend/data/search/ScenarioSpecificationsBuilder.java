package de.hszg.umgebindehaus.backend.data.search;

import de.hszg.umgebindehaus.backend.data.model.Scenario;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScenarioSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public ScenarioSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public ScenarioSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Scenario> build() {

        if (params.size() == 0) return null;

        List<Specification> specs = params.stream()
                .map(ScenarioSpecification::new)
                .collect(Collectors.toList());
        Specification result = specs.get(0);

        for (int i = 0; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(specs.get(i))
                    : Specification.where(result).and(specs.get(i));
        }

        return result;

    }

}
