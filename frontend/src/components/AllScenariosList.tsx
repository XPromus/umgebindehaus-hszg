import {Container, Row, Col, Card, CardBody, CardTitle, CardText, Button} from "reactstrap";
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
                                    <CardText>blablabla</CardText>
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