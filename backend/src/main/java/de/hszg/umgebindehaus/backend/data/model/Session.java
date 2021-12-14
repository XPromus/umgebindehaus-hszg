package de.hszg.umgebindehaus.backend.data.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Session{

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

    @NotNull
    public String getName(){
        return name;
    }
    public void setName(@NotNull String name){
        this.name = name;
    }

    @NotNull
    public LocalDateTime getTime(){
        return time;
    }
    public void setTime(@NotNull LocalDateTime time){
        this.time = time;
    }

    @NotNull
    public Weather getWeather(){
        return weather;
    }
    public void setWeather(@NotNull Weather weather){
        this.weather = weather;
    }

    public double getTimeScale(){
        return timeScale;
    }
    public void setTimeScale(double timeScale){
        this.timeScale = timeScale;
    }

    public boolean isAutomaticWeather(){
        return automaticWeather;
    }
    public void setAutomaticWeather(boolean automaticWeather){
        this.automaticWeather = automaticWeather;
    }

    public boolean isAutomaticTime(){
        return automaticTime;
    }
    public void setAutomaticTime(boolean automaticTime){
        this.automaticTime = automaticTime;
    }
}