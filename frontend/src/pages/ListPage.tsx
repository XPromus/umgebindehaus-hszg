import {AllScenariosList} from "../components/AllScenariosList";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Container,} from 'reactstrap';
import {NavbarScenario} from "../components/NavbarScenario";

export const ListPage = () => {
    return (
        <div>
            <NavbarScenario/>
            <div className="App">
                <Container>
                    <h3 className="scenario-header">Alle Szenarios </h3>
                    <AllScenariosList/>
                </Container>
            </div>
        </div>
    );
}
