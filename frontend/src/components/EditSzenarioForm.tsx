import {Alert, Button, Card, CardTitle, Col, Form, FormGroup, Input, Label} from "reactstrap";
import React, {useState} from "react";
import {callDeleteSzenario, callEditSzenario, SzenarioResponse} from "../rest/szenarioCalls";
import moment from "moment";

export interface EditSzenarioFormProps{
    szenario:SzenarioResponse
}

export const EditSzenarioForm = (props:EditSzenarioFormProps) => {
    const szenario = props.szenario;
    const [id, setId] = useState<number>(szenario.id);
    const [name, setName] = useState<string>(szenario.name);
    const [time, setTime] = useState<string>(convertTime(szenario.time));
    const [date, setDate] = useState<string>(convertDate(szenario.time));
    const [timeScale, setTimeScale] = useState<number>(szenario.timeScale);
    const [automaticWeather, setAutomaticWeather] = useState<boolean>(szenario.automaticWeather);
    const [automaticTime, setAutomaticTime] = useState<boolean>(szenario.automaticTime);
    const [weatherWindDirection, setWeatherWindDirection] = useState<number>(szenario.weather.windDirection);
    const [weatherWindSpeed, setWeatherWindSpeed] = useState<number>(szenario.weather.windSpeed);
    const [weatherCloudiness, setWeatherCloudiness] = useState<string>(szenario.weather.cloudiness);

    const now = moment();
    function convertTime(value:string){
        const momentTime = moment(value, moment.ISO_8601)
        return momentTime.format("HH:mm")
    }
    function convertDate(value:string){
        const momentDate = moment(value, moment.ISO_8601)
        return momentDate.format("YYYY-MM-DD")
    }

    // function printConsole() {
    //     const formValues = {name, time, timeScale, automaticWeather, automaticTime}
    //     // @ts-ignore
    //     console.log("formValues", formValues);
    //     // @ts-ignore
    // }

    const [deleteSzenario, setDeleteSzenario] = useState<SzenarioResponse>();
    const [deleteSzenarioFailed, setDeleteSzenarioFailed] = useState<boolean>(false);

    const onDelete = () => {
        console.log("id: ", id);
        callDeleteSzenario(id)
            .then(szenario => {
                setDeleteSzenario(szenario);
                setDeleteSzenarioFailed(false);
            })
            .catch(() => {
                setDeleteSzenario(undefined);
                setDeleteSzenarioFailed(true)
            })

        // FIXME: handling
    }
    const [editSzenario, setEditSzenario] = useState<SzenarioResponse>();
    const [editSzenarioFailed, setEditSzenarioFailed] = useState<boolean>(false);

    const onSave = () => {
        console.log("id: ", id, "name: ", name, "time: ", now.toISOString(), "weatherWindDirection: ", weatherWindDirection, "weatherWindSpeed: ", weatherWindSpeed, "weatherCloudiness: ", weatherCloudiness, "timeScale: ", timeScale, "automaticWeather: ", automaticWeather, "automaticTime: ", automaticTime);
        callEditSzenario(id, name, now.toISOString(), timeScale, automaticWeather, automaticTime, weatherWindDirection, weatherWindSpeed, weatherCloudiness)
            .then(szenario => {
                setEditSzenario(szenario);
                setEditSzenarioFailed(false);
            })
            .catch(() => {
                setEditSzenario(undefined);
                setEditSzenarioFailed(true)
            })
    }

    function convertCloudiness(value: string) {
        switch (value) {
            case "Regen": {
                setWeatherCloudiness("RAIN")
                break;
            }
            case "Klar": {
                setWeatherCloudiness("CLEAR")
                break;
            }
            case "Wolkig 1": {
                setWeatherCloudiness("CLOUDY_1")
                break;
            }
            case "Wolkig 2": {
                setWeatherCloudiness("CLOUDY_2")
                break;
            }
            default: {
                //statements;
                break;
            }
        }
        return undefined;
    }

    function cloudinessToString(value: string) {
        switch (value) {
            case "RAIN": {
                return "Regen"
            }
            case "CLEAR": {
                return("Klar")
            }
            case "CLOUDY_1": {
                return "Wolkig 1"
            }
            case "CLOUDY_2": {
                return "Wolkig 2"
            }
            default: {
                //statements;
                break;
            }
        }
        return undefined
    }

    function setTimeToMoment(value: string) {
        now.set({"hour": parseInt(value.slice(0, 2)), "minute": parseInt(value.slice(4, 6)), "second": 0, "millisecond":0})
    }

    function setDateToMoment(value: string) {
        now.set({
            "year": parseInt(value.slice(0, 4)),
            "month": parseInt(value.slice(5, 7))-1,
            "date": parseInt(value.slice(8, 10))
        })
    }

    return (

        <div className="input-form" id="input-form-content">
            <Card body>
                <CardTitle tag="h5" className="card-title">
                    Szenario bearbeiten
                </CardTitle>
                {
                    editSzenarioFailed && (
                        <Alert color="error"> Szenario konnte nicht bearbeitet werden</Alert>
                    )
                }
                <Form>
                    {/*<FormGroup row>*/}
                    {/*    <Label*/}
                    {/*        for="idScenario"*/}
                    {/*        sm={2}*/}
                    {/*    >*/}
                    {/*        ID*/}
                    {/*    </Label>*/}
                    {/*    <Col sm={10}>*/}
                    {/*        <Input*/}
                    {/*            value={id}*/}
                    {/*            onChange={(e: any) => setId(e.target.value)}*/}
                    {/*            name="id"*/}
                    {/*            placeholder="ID deines Szenarios"*/}
                    {/*            type="number"*/}
                    {/*        />*/}
                    {/*    </Col>*/}
                    {/*</FormGroup>*/}
                    <FormGroup row>
                        <Label
                            for="nameScenario"
                            sm={2}
                        >
                            Name
                        </Label>
                        <Col sm={10}>
                            <Input
                                value={name}
                                onChange={(e: any) => setName(e.target.value)}
                                name="name"
                                placeholder="Name deines Szenarios"
                                type="text"
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label for="dateScenario" sm={2}>
                            Date
                        </Label>
                        <Col sm={10}>
                            <Input
                                onChange={(e: any) => setDateToMoment(e.target.value)}
                                name="date"
                                placeholder="date placeholder"
                                type="date"
                                value={date}
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label for="timeScenario" sm={2}>
                            Time
                        </Label>
                        <Col sm={10}>
                            <Input
                                onChange={(e: any) => setTimeToMoment(e.target.value)}
                                name="time"
                                placeholder="time placeholder"
                                type="time"
                                value={time}
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label
                            for="windDirectionScenario"
                            sm={2}
                        >
                            WindDirection
                        </Label>
                        <Col sm={10}>
                            <Input
                                value={weatherWindDirection}
                                onChange={(e: any) => setWeatherWindDirection(parseFloat(e.target.value))}
                                name="windDirection"
                                placeholder="Richtung des Windes"
                                type="text"
                            />
                        </Col>

                    </FormGroup>
                    <FormGroup row>
                        <Label
                            for="windSpeed"
                            sm={2}
                        >
                            WindSpeed
                        </Label>
                        <Col sm={10}>
                            <Input
                                value={weatherWindSpeed}
                                onChange={(e: any) => setWeatherWindSpeed(parseFloat(e.target.value))}
                                name="windSpeed"
                                placeholder="Geschwindigkeit des Windes"
                                type="text"

                            />
                        </Col>

                    </FormGroup>

                    <FormGroup row>
                        <Label
                            for="cloudiness"
                            sm={2}
                        >
                            Cloudiness
                        </Label>
                        <Col sm={10}>
                            <Input
                                value={cloudinessToString(weatherCloudiness)}
                                type="select"
                                name="cloudiness"
                                onChange={(e: any) => convertCloudiness(e.target.value)}>
                                <option>Klar</option>
                                <option>Wolkig 1</option>
                                <option>Wolkig 2</option>
                                <option>Regen</option>
                            </Input>
                        </Col>

                    </FormGroup>
                    <FormGroup row>
                        <Label
                            for="timeScaleScenario"
                            sm={2}
                        >
                            Time Scale
                        </Label>
                        <Col sm={10}>
                            <Input
                                value={timeScale}
                                onChange={(e: any) => setTimeScale(parseInt(e.target.value))}
                                name="timeScale"
                                placeholder="Zeitskala deines Szenarios"
                                type="number"
                            />
                        </Col>
                    </FormGroup>

                    <FormGroup row>
                        <Label
                            for="checkboxAutomaticWeather"
                            sm={2}
                        >
                            Automatic Weather
                        </Label>
                        <Col
                            sm={{
                                size: 10
                            }}
                        >
                            <FormGroup check>
                                <Input
                                    checked={automaticWeather}
                                    type="checkbox"
                                    onChange={(e: any) => setAutomaticWeather(e.target.checked)}
                                />
                            </FormGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label
                            for="checkboxAutomaticTime"
                            sm={2}
                        >
                            Automatic Time
                        </Label>
                        <Col
                            sm={{
                                size: 10
                            }}
                        >
                            <FormGroup check>
                                <Input
                                    checked={automaticTime}
                                    id="checkboxAutomaticTime"
                                    onChange={(e: any) => setAutomaticTime(e.target.checked)}
                                    type="checkbox"
                                />
                            </FormGroup>
                        </Col>
                    </FormGroup>
                    <FormGroup
                        row
                    >
                        <Col
                            sm={{

                                size: 6
                            }}
                        >

                            <Button onClick={onSave} color="success">
                                speichern
                            </Button>
                        </Col>
                        <Col
                            sm={{

                                size: 6
                            }}
                        >
                            <Button onClick={onDelete} color="danger">
                                Löschen
                            </Button>
                        </Col>
                    </FormGroup>
                </Form>
                {deleteSzenarioFailed && (
                    <Alert color="danger">
                        Löschen fehlgeschlagen
                    </Alert>
                )}
                {/*<pre>*/}
                {/*    <code>*/}
                {/*        {*/}
                {/*            JSON.stringify(editSzenario, null, 2)*/}
                {/*        }*/}
                {/*    </code>*/}
                {/*</pre>*/}


            </Card>

        </div>
    )
}
