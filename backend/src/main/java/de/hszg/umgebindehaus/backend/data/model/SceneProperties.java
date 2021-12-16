package de.hszg.umgebindehaus.backend.data.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface SceneProperties{

    @NotNull String getName();

    void setName(@NotNull String name);

    @NotNull LocalDateTime getTime();

    void setTime(@NotNull LocalDateTime time);

    @NotNull Weather getWeather();

    void setWeather(@NotNull Weather weather);

    @NotNull Double getTimeScale();

    void setTimeScale(@NotNull Double timeScale);

    @NotNull Boolean getAutomaticWeather();

    void setAutomaticWeather(@NotNull Boolean automaticWeather);

    @NotNull Boolean getAutomaticTime();

    void setAutomaticTime(@NotNull Boolean automaticTime);
}