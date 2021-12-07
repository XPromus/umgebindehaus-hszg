package de.hszg.umgebindehaus.backend.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
public class Weather{

    public Weather(){
        this(0.0, 0.0, CloudType.CLEAR);
    }

    public Weather(@NotNull Double windDirection, @NotNull Double windSpeed, @NotNull CloudType cloudiness){
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.cloudiness = cloudiness;
    }

    @Column(name = "weather_wind_Direction")
    private Double windDirection;
    @Column(name = "weather_wind_speed")
    private Double windSpeed;
    @Column(name = "weather_cloudiness")
    private CloudType cloudiness;

    @NotNull
    public Double getWindDirection(){
        return windDirection;
    }
    public void setWindDirection(@NotNull Double windDirection){
        this.windDirection = windDirection;
    }

    @NotNull
    public Double getWindSpeed(){
        return windSpeed;
    }
    public void setWindSpeed(@NotNull Double windSpeed){
        this.windSpeed = windSpeed;
    }

    @NotNull
    public CloudType getCloudiness(){
        return cloudiness;
    }
    public void setCloudiness(@NotNull CloudType cloudiness){
        this.cloudiness = cloudiness;
    }

    public void applyFrom(Weather other){
        setWindDirection(other.getWindDirection());
        setWindSpeed(other.getWindSpeed());
        setCloudiness(other.getCloudiness());
    }

    public enum CloudType{
        CLEAR, CLOUDY_1, CLOUDY_2, RAIN
    }
}