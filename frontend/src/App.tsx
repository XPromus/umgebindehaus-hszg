import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button, Container, Input, InputGroup} from 'reactstrap';
import {EditSzenarioForm} from "./components/EditSzenarioForm";
import {CreateSzenarioForm} from "./components/CreateSzenarioForm";
import {AllScenariosList} from "./components/AllScenariosList";

function showForm() {
    // @ts-ignore
    document.getElementById("input-form-content").style["animation-name"] = "motionForm";
    // @ts-ignore
    document.getElementById("input-form-content").style["animation-duration"] = "2s";
    // @ts-ignore
    document.getElementById("input-form-content").style["animation-delay"] = "2s";
    // @ts-ignore
    document.getElementById("input-form-content").style["animation-fill-mode"] = "forwards";

    // @ts-ignore
    document.getElementById("input-id-content").style["animation-name"] = "motionInputID";
    // @ts-ignore
    document.getElementById("input-id-content").style["animation-duration"] = "2s";
    // @ts-ignore
    document.getElementById("input-id-content").style["animation-fill-mode"] = "forwards";
}

function App() {
    return (
        <div className="App">
            <Container>
                <CreateSzenarioForm/>
                <h3 className="scenario-header">Alle Szenarios</h3>
                <AllScenariosList/>

                <div className="input-id-content" id="input-id-content">
                    <InputGroup>
                        <Input className="session-ID-input"/>
                        <Button color="primary" className="session-ID-button" onClick={showForm}>
                            Session suchen
                        </Button>
                    </InputGroup>
                </div>
                <EditSzenarioForm/>

            </Container>
        </div>
    );
}

export default App;
