import axios from "axios";

enum Cloudiness {
    CLEAR = "CLEAR",
    CLOUDY_1 = "CLOUDY_1",
    CLOUDY_2 = "CLOUDY_2",
    RAIN = "RAIN"
}

interface WeatherResponse {
    windDirection: number;
    windSpeed: number;
    cloudiness: Cloudiness
}

export interface SzenarioResponse {
    id: number;
    name: string;
    time: string; // date time
    weather: WeatherResponse
    timeScale: number; // double e.g: 0.12
    automaticWeather: boolean;
    automaticTime: boolean;
}

export const callCreateSzenario = (name: string): Promise<SzenarioResponse> => {
    return axios.post("/scenario/create", name, {
        headers: { // nur notwendig, da string als body content notwendig ist
            "Content-Type": "text/plain"
        }
    })
        .then(response => Promise.resolve(response.data))
        .catch(error => Promise.reject(error))
}
export const callEditSzenario = (id: number,name: string,time: number,timeScale: number, automaticWeather: boolean, automaticTime: boolean, weatherWindDiretion: number, weatherWindSpeed: number, weatherCloudiness: string): Promise<SzenarioResponse> => {
    return axios.post("/scenario/edit/" + id, {
        newName: name, newTime: "2022-01-11T11:04:17Z", newTimeScale: timeScale, newAutomaticWeather: automaticWeather, newAutomaticTime: automaticTime, newWeatherWindDirection: weatherWindDiretion, newWeatherWindSpeed: weatherWindSpeed, newWeatherCloudiness: weatherCloudiness
    })
        .then(response => Promise.resolve(response.data))
        .catch(error => Promise.reject(error))
}

export const callDeleteSzenario = (id: number): Promise<SzenarioResponse> => {
    return axios.delete(`/scenario/delete/${id}`)
        .then(response => Promise.resolve(response.data))
        .catch(error => Promise.reject(error))
}

export const callScenarioAll = () => {
    return axios.get("/scenario/all")
}