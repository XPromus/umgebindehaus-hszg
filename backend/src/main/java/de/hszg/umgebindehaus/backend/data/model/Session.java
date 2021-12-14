package de.hszg.umgebindehaus.backend.data.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Session implements SceneProperties{

    private String id;
    private String name;
    private LocalDateTime time;
    private Weather weather;
    private double timeScale;
    private boolean automaticWeather;
    private boolean automaticTime;

    public Session(){
        name = "_undefined_";
        time = LocalDateTime.now();
        weather = new Weather();
        timeScale = 1.0;
        automaticWeather = true;
        automaticTime = true;
    }

    @NotNull
    public String getId(){
        return id;
    }
    public void setId(@NotNull String id){
        this.id = id;
    }

    @Override
    @NotNull
    public String getName(){
        return name;
    }
    @Override
    public void setName(@NotNull String name){
        this.name = name;
    }

    @Override
    @NotNull
    public LocalDateTime getTime(){
        return time;
    }
    @Override
    public void setTime(@NotNull LocalDateTime time){
        this.time = time;
    }

    @Override
    @NotNull
    public Weather getWeather(){
        return weather;
    }
    @Override
    public void setWeather(@NotNull Weather weather){
        this.weather = weather;
    }

    @Override
    @NotNull
    public Double getTimeScale(){
        return timeScale;
    }
    @Override
    public void setTimeScale(@NotNull Double timeScale){
        this.timeScale = timeScale;
    }

    @Override
    @NotNull
    public Boolean getAutomaticWeather(){
        return automaticWeather;
    }
    @Override
    public void setAutomaticWeather(Boolean automaticWeather){
        this.automaticWeather = automaticWeather;
    }

    @Override
    @NotNull
    public Boolean getAutomaticTime(){
        return automaticTime;
    }
    @Override
    public void setAutomaticTime(@NotNull Boolean automaticTime){
        this.automaticTime = automaticTime;
    }
}