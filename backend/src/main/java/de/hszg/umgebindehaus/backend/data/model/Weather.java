package de.hszg.umgebindehaus.backend.data.model;

import javax.persistence.*;

@Embeddable
public class Weather{

    public Weather(){
        this(0.0, 0.0, CloudType.CLEAR);
    }

    public Weather(Double windDirection, Double windSpeed, CloudType cloudiness){
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

    public Double getWindDirection(){
        return windDirection;
    }
    public void setWindDirection(Double windDirection){
        this.windDirection = windDirection;
    }

    public Double getWindSpeed(){
        return windSpeed;
    }
    public void setWindSpeed(Double windSpeed){
        this.windSpeed = windSpeed;
    }

    public CloudType getCloudiness(){
        return cloudiness;
    }
    public void setCloudiness(CloudType cloudiness){
        this.cloudiness = cloudiness;
    }

    public enum CloudType{
        CLEAR, CLOUDY_1, CLOUDY_2, RAIN
    }
}