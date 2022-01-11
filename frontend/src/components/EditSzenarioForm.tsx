import {
    Alert,
    Button,
    Card,
    CardTitle,
    Col,
    Container,
    Dropdown, DropdownItem, DropdownMenu,
    DropdownToggle,
    Form,
    FormGroup,
    Input,
    Label
} from "reactstrap";
import React, {useState} from "react";
import {callCreateSzenario, callDeleteSzenario, SzenarioResponse} from "../rest/szenarioCalls";
import {callEditSzenario} from "../rest/szenarioCalls";

export const EditSzenarioForm = () => {
    const [id, setId] = useState<number>(1);
    const [name, setName] = useState<string>("");
    const [time, setTime] = useState<number>(1);
   // const [weather, setWeather] = useState<string>("");
    const [timeScale, setTimeScale] = useState<number>(1);
    const [automaticWeather, setAutomaticWeather] = useState<boolean>(false);
    const [automaticTime, setAutomaticTime] = useState<boolean>(false);
    const [weatherWindDirection, setWeatherWindDirection] = useState<number>(1);
    const [weatherWindSpeed, setWeatherWindSpeed] = useState<number>(1);
    const [weatherCloudiness, setWeatherCloudiness] = useState<string>("");

    function printConsole() {
        const formValues = {name, time, timeScale, automaticWeather, automaticTime}
        // @ts-ignore
        console.log("formValues", formValues);
        // @ts-ignore
    }

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
        console.log("id: ", id, "name: ", name, "time: ", time,  "weatherWindDirection: ", weatherWindDirection, "weatherWindSpeed: ", weatherWindSpeed, "weatherCloudiness: ", weatherCloudiness, "timeScale: ", timeScale, "automaticWeather: ", automaticWeather, "automaticTime: ", automaticTime);
        callEditSzenario(id, name, time, timeScale, automaticWeather, automaticTime, weatherWindDirection, weatherWindSpeed, weatherCloudiness)
            .then(szenario => {
                setEditSzenario(szenario);
                setEditSzenarioFailed(false);
            })
            .catch(() => {
                setEditSzenario(undefined);
                setEditSzenarioFailed(true)
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
                    <FormGroup row>
                        <Label
                            for="idScenario"
                            sm={2}
                        >
                            ID
                        </Label>
                        <Col sm={10}>
                            <Input
                                onChange={(e: any) => setId(e.target.value)}
                                name="id"
                                placeholder="ID deines Szenarios"
                                type="number"
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label
                            for="nameScenario"
                            sm={2}
                        >
                            Name
                        </Label>
                        <Col sm={10}>
                            <Input
                                onChange={(e: any) => setName(e.target.value)}
                                name="name"
                                placeholder="Name deines Szenarios"
                                type="text"
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label for="timeScenario" sm={2}>
                            Time
                        </Label>
                        <Col sm={10}>
                            <Input
                                onChange={(e: any) => setTime(e.target.value)}
                                name="time"
                                placeholder="time placeholder"
                                type="date"
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label for="timeScenario" sm={2}>
                            Time
                        </Label>
                        <Col sm={10}>
                            <Input
                                onChange={(e: any) => setTime(e.target.value)}
                                name="time"
                                placeholder="time placeholder"
                                type="time"
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
                                onChange={(e: any) => setWeatherCloudiness(e.target.value)}
                                name="cloudiness"
                                placeholder="Wie bewölkt soll es denn sein?"
                                type="text"
                            />
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
                {
                    editSzenario
                }

            </Card>

        </div>
    )
}
