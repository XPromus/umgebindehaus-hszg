package de.hszg.umgebindehaus.backend.config;

import de.hszg.umgebindehaus.backend.data.model.Scenario;
import de.hszg.umgebindehaus.backend.data.model.Weather;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("MagicNumber")
public class DefaultScenarios{

    public static final Weather DEFAULT_WEATHER = new Weather();
    public static final Set<Scenario> DEFAULT_SCENARIOS;

    static{
        Set<Scenario> set = new HashSet<>();

        Scenario s1 = new Scenario();
        s1.setName("Example 1");
        set.add(s1);

        Scenario s2 = new Scenario();
        s2.setName("Example 2");
        s2.setTime(LocalDateTime.of(2021, 6, 20, 13, 0, 0));
        s2.setTimeScale(5.0);
        s2.setAutomaticWeather(Boolean.FALSE);
        s2.setWeather(new Weather(12.0, 2.5, Weather.CloudType.CLOUDY_1));
        set.add(s2);

        DEFAULT_SCENARIOS = Collections.unmodifiableSet(set);
    }

}