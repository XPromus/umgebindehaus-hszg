import {Alert, Button, Card, CardTitle, Col, Form, FormGroup, Input, Label} from "reactstrap";
import React, {useState} from "react";
import {callCreateSzenario, SzenarioResponse} from "../rest/szenarioCalls";

export const CreateSzenarioForm = () => {
    const [name, setName] = useState<string>("");

    const [createdSzenario, setCreatedSzenario] = useState<SzenarioResponse>();
    const [createSzenarioFailed, setCreateSzenarioFailed] = useState<boolean>(false);

    const onSubmit = () => {
        console.log("name: ", name);
        callCreateSzenario(name)
            .then(szenario => {
                setCreatedSzenario(szenario);
                setCreateSzenarioFailed(false);
            })
            .catch(() => {
                setCreatedSzenario(undefined);
                setCreateSzenarioFailed(true)
            })

        // FIXME: handling
    }
    return (
        <Card>
            <Card body>
                <CardTitle tag="h5" className="card-title">
                    Szenario erstellen
                </CardTitle>
                {
                    createSzenarioFailed && (
                        <Alert color="error">Szenario konnte nicht angelegt werden</Alert>
                    )
                }
                <Form>
                    <FormGroup>
                            <Input
                                onChange={(e: any) => setName(e.target.value)}
                                name="name"
                                placeholder="Name deines Scenarios"
                                type="text"
                            />
                    </FormGroup>
                    <Button onClick={onSubmit} color="success">
                        speichern
                    </Button>
                </Form>
                {
                    createdSzenario && (
                        <div>
                            <h3>Created Szenario: </h3>
                            <pre>
                                <code>{JSON.stringify(createdSzenario, null, 2)}</code>
                            </pre>
                        </div>
                    )
                }
            </Card>
        </Card>
    )
}
