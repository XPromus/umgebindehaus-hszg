package de.hszg.umgebindehaus.backend.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Scenario implements SceneProperties{

    public Scenario(){
        name = "_undefined_";
        time = LocalDateTime.now();
        weather = new Weather();
        timeScale = 1.0;
        automaticWeather = Boolean.TRUE;
        automaticTime = Boolean.TRUE;
    }

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private LocalDateTime time;//NOTE if this fails, use a converter (to long): https://thorben-janssen.com/persist-localdate-localdatetime-jpa/
    @Column(nullable = false)
    @Embedded
    private Weather weather;
    @Column(nullable = false)
    private Double timeScale;
    @Column(nullable = false)
    private Boolean automaticWeather;
    @Column(nullable = false)
    private Boolean automaticTime;

    @NotNull
    public Integer getId(){
        return id;
    }
    public void setId(@NotNull Integer id){
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

    @NotNull
    public Double getTimeScale(){
        return timeScale;
    }
    public void setTimeScale(@NotNull Double timeScale){
        this.timeScale = timeScale;
    }

    @NotNull
    public Boolean getAutomaticWeather(){
        return automaticWeather;
    }
    public void setAutomaticWeather(@NotNull Boolean automaticWeather){
        this.automaticWeather = automaticWeather;
    }

    @NotNull
    public Boolean getAutomaticTime(){
        return automaticTime;
    }
    public void setAutomaticTime(@NotNull Boolean automaticTime){
        this.automaticTime = automaticTime;
    }
}