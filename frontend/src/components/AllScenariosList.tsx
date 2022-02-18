import {Container, Row, Col, Card, CardBody, CardTitle, CardText, Button, List} from "reactstrap";
import {useEffect, useState} from "react";
import {callScenarioAll, SzenarioResponse} from "../rest/szenarioCalls";
import {CreateSzenarioForm} from "./CreateSzenarioForm";
import {useNavigate} from "react-router-dom";
import moment from "moment";

export const AllScenariosList = () => {
    const [scenarios, setScenarios] = useState<SzenarioResponse[]>([]);
    const [isLoaded, setLoaded] = useState<boolean>(false);
    const navigate = useNavigate();
    useEffect(() => {
        if (!isLoaded) {
            callScenarioAll()
                .then(r => {
                    console.log(r.data);
                    setScenarios(r.data);
                    setLoaded(true);
                });
        }
    })

    function momentToTime(value: string) {
        const time = moment(value, moment.ISO_8601);
        return (time.format("D.MMM.Y | HH:mm "))
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

    return (
        <div className={"all-scenarios-list"}>
            <Container>
                <Row xs={3}>
                    {scenarios.map((e, i) => {
                        return (
                            <Col key={i}>
                                <Card>
                                    <CardBody>
                                        <CardTitle>{e.name}</CardTitle>
                                        <CardText tag={"div"}>
                                            <List>
                                                <li>
                                                    Zeit: {momentToTime(e.time)}Uhr
                                                </li>
                                                <li>
                                                    Windrichtung: {e.weather.windDirection}
                                                </li>
                                                <li>
                                                    Windgeschwindigkeit: {e.weather.windSpeed}
                                                </li>
                                                <li>
                                                    Wetter: {cloudinessToString(e.weather.cloudiness)}
                                                </li>
                                                <li>
                                                    Zeitraffer: {e.timeScale}
                                                </li>
                                            </List>

                                        </CardText>

                                        <Button onClick={() => navigate("/edit/" + e.id)}>Bearbeiten</Button>
                                    </CardBody>
                                </Card>
                            </Col>
                        )
                    })}
                    <Col>
                        <CreateSzenarioForm/>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}