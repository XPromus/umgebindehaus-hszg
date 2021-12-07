package de.hszg.umgebindehaus.backend.service;

import de.hszg.umgebindehaus.backend.data.model.Weather;

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

    public Integer getScenarioId(){
        return scenarioId;
    }
    public void setScenarioId(Integer scenarioId){
        this.scenarioId = scenarioId;
    }

    public String getNewName(){
        return newName;
    }
    public void setNewName(String newName){
        this.newName = newName;
    }

    public LocalDateTime getNewTime(){
        return newTime;
    }
    public void setNewTime(LocalDateTime newTime){
        this.newTime = newTime;
    }

    public Double getNewTimeScale(){
        return newTimeScale;
    }
    public void setNewTimeScale(Double newTimeScale){
        this.newTimeScale = newTimeScale;
    }

    public Boolean getNewAutomaticWeather(){
        return newAutomaticWeather;
    }
    public void setNewAutomaticWeather(Boolean newAutomaticWeather){
        this.newAutomaticWeather = newAutomaticWeather;
    }

    public Boolean getNewAutomaticTime(){
        return newAutomaticTime;
    }
    public void setNewAutomaticTime(Boolean newAutomaticTime){
        this.newAutomaticTime = newAutomaticTime;
    }

    public Double getNewWeatherWindDirection(){
        return newWeatherWindDirection;
    }
    public void setNewWeatherWindDirection(Double newWeatherWindDirection){
        this.newWeatherWindDirection = newWeatherWindDirection;
    }

    public Double getNewWeatherWindSpeed(){
        return newWeatherWindSpeed;
    }
    public void setNewWeatherWindSpeed(Double newWeatherWindSpeed){
        this.newWeatherWindSpeed = newWeatherWindSpeed;
    }

    public Weather.CloudType getNewWeatherCloudiness(){
        return newWeatherCloudiness;
    }
    public void setNewWeatherCloudiness(Weather.CloudType newWeatherCloudiness){
        this.newWeatherCloudiness = newWeatherCloudiness;
    }
}