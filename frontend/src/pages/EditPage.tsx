import {Container} from "reactstrap";
import {EditSzenarioForm} from "../components/EditSzenarioForm";
import {NavbarScenario} from "../components/NavbarScenario";
import {callGetScenario, SzenarioResponse} from "../rest/szenarioCalls";
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

export const EditPage = () => {
    const [getSzenario, setGetSzenario] = useState<SzenarioResponse>();
    const [getSzenarioFailed, setGetSzenarioFailed] = useState<boolean>(false);
    const params = useParams<Record<"id",string>>();
    const [isLoaded, setLoaded] = useState<boolean>(false);
    useEffect(() => {
        if(isLoaded){
            return
        }
        callGetScenario(parseInt(params.id!!))
            .then(szenario => {
                setGetSzenario(szenario);
                setGetSzenarioFailed(false);
                setLoaded(true);
            })
            .catch(() => {
                setGetSzenario(undefined);
                setGetSzenarioFailed(true);
                setLoaded(true);
            })
    })
    console.log(getSzenario);
    return (
        <div>
            <NavbarScenario/>
            <div className="App edit-page">
                <Container>
                    <EditSzenarioForm/>
                </Container>
            </div>
        </div>

    );
}

