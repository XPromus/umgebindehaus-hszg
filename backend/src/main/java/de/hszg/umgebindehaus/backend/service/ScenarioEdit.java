package de.hszg.umgebindehaus.backend.service;

import de.hszg.umgebindehaus.backend.data.model.Weather;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

public class ScenarioEdit{// DataTransferObject

    private Integer scenarioId;
    private String newName = null;
    private LocalDateTime newTime = null;
    private Double newTimeScale = null;
    private Boolean newAutomaticWeather = null;
    private Boolean newAutomaticTime = null;

    private Double newWeatherWindDirection = null;
    private Double newWeatherWindSpeed = null;
    private Weather.CloudType newWeatherCloudiness = null;

    @Nullable
    public Integer getScenarioId(){
        return scenarioId;
    }
    public void setScenarioId(@Nullable Integer scenarioId){
        this.scenarioId = scenarioId;
    }

    @Nullable
    public String getNewName(){
        return newName;
    }
    public void setNewName(@Nullable String newName){
        this.newName = newName;
    }

    @Nullable
    public LocalDateTime getNewTime(){
        return newTime;
    }
    public void setNewTime(@Nullable LocalDateTime newTime){
        this.newTime = newTime;
    }

    @Nullable
    public Double getNewTimeScale(){
        return newTimeScale;
    }
    public void setNewTimeScale(@Nullable Double newTimeScale){
        this.newTimeScale = newTimeScale;
    }

    @Nullable
    public Boolean getNewAutomaticWeather(){
        return newAutomaticWeather;
    }
    public void setNewAutomaticWeather(@Nullable Boolean newAutomaticWeather){
        this.newAutomaticWeather = newAutomaticWeather;
    }

    @Nullable
    public Boolean getNewAutomaticTime(){
        return newAutomaticTime;
    }
    public void setNewAutomaticTime(@Nullable Boolean newAutomaticTime){
        this.newAutomaticTime = newAutomaticTime;
    }

    @Nullable
    public Double getNewWeatherWindDirection(){
        return newWeatherWindDirection;
    }
    public void setNewWeatherWindDirection(@Nullable Double newWeatherWindDirection){
        this.newWeatherWindDirection = newWeatherWindDirection;
    }

    @Nullable
    public Double getNewWeatherWindSpeed(){
        return newWeatherWindSpeed;
    }
    public void setNewWeatherWindSpeed(@Nullable Double newWeatherWindSpeed){
        this.newWeatherWindSpeed = newWeatherWindSpeed;
    }

    @Nullable
    public Weather.CloudType getNewWeatherCloudiness(){
        return newWeatherCloudiness;
    }
    public void setNewWeatherCloudiness(@Nullable Weather.CloudType newWeatherCloudiness){
        this.newWeatherCloudiness = newWeatherCloudiness;
    }

}