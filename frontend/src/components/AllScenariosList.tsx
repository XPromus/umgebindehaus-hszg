import {Container, Row, Col, Card, CardBody, CardTitle, CardText, Button, List} from "reactstrap";
import {useEffect, useState} from "react";
import {callScenarioAll, SzenarioResponse} from "../rest/szenarioCalls";

export const AllScenariosList = () => {
   const [scenarios, setScenarios] = useState<SzenarioResponse[]>([]);
   const [isLoaded, setLoaded] = useState<boolean>(false);
   useEffect( () => {
       if(!isLoaded){
           callScenarioAll()
               .then(r => {
                   console.log(r.data);
                   setScenarios(r.data);
                   setLoaded(true);
               });
       }
    })

    return(
        <div className={"all-scenarios-list"}>
            <Container>
                <Row xs={3}>
                    {scenarios.map((e,i) => {
                        return(
                        <Col>
                            <Card>
                                <CardBody>
                                    <CardTitle>{e.name}</CardTitle>
                                    <CardText>
                                        <List>
                                            <li>
                                                Zeit: {e.time}
                                            </li>
                                            <li>
                                                Windrichtung: {e.weather.windDirection}
                                            </li>
                                            <li>
                                                Windgeschwindigkeit: {e.weather.windSpeed}
                                            </li>
                                            <li>
                                                Wetter: {e.weather.cloudiness}
                                            </li>
                                            <li>
                                                Zeitraffer: {e.timeScale}
                                            </li>
                                        </List>

                                    </CardText>

                                    <Button>Bearbeiten</Button>
                                </CardBody>
                            </Card>
                        </Col>
                        )
                    })}
                </Row>
            </Container>
        </div>
    )
}