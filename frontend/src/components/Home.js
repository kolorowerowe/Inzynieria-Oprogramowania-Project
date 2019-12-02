import React, {Component} from 'react';
import "./Home.css"
import {Accordion, Button, Card, Col, Container, Form, Row} from "react-bootstrap";


class Home extends Component {

    state = {};

    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }


    render() {
        return (
            <div className="Home">
                <Container id="home_unlogged">
                    <Row>
                        <Col md={6}  className="login-register login">

                            <Accordion defaultActiveKey="0">
                                <Card>
                                    <Accordion.Toggle as={Card.Header} eventKey="1">
                                        <h2>
                                            Zaloguj
                                        </h2>
                                    </Accordion.Toggle>
                                    <Accordion.Collapse eventKey="1">
                                        <Card.Body>
                                            <Form>
                                                <Form.Group as={Row} controlId="formHorizontalEmail">
                                                    <Form.Label column sm={3}>
                                                        Email
                                                    </Form.Label>
                                                    <Col sm={9}>
                                                        <Form.Control type="email" placeholder="Email" />
                                                    </Col>
                                                </Form.Group>
                                                <Form.Group as={Row} controlId="formHorizontalPassword">
                                                    <Form.Label column sm={3}>
                                                        Password
                                                    </Form.Label>
                                                    <Col sm={9}>
                                                        <Form.Control type="password" placeholder="Password" />
                                                    </Col>
                                                </Form.Group>
                                                <Form.Group as={Row}>
                                                    <Col sm={{ span: 10, offset: 2 }}>
                                                        <Button type="submit">Sign in</Button>
                                                    </Col>
                                                </Form.Group>
                                            </Form>
                                        </Card.Body>
                                    </Accordion.Collapse>
                                </Card>
                            </Accordion>


                        </Col>
                        <Col md={6}>
                            <h2 className="login-register register">
                                Zarejestruj się
                            </h2>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <h2 className="login-register-info">
                                Info
                            </h2>
                        </Col>
                    </Row>
                </Container>

            </div>
        );
    }
}

export default Home;