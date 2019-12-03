import React, {Component} from 'react';
import "./Home.css"
import {Accordion, Button, Card, Col, Container, Form, Row} from "react-bootstrap";


class Home extends Component {

    state = {};

    constructor(props) {
        super(props);
        this.setState({logged:false});
    }

    componentDidMount() {

    }


    render() {
        if (this.state.logged === true) {
            return (
                <div className="Home">
                    <h2> Logged </h2>
                </div>
            );
        } else {
            return (
                <div className="Home">
                    <Container id="home_unlogged">
                        <Row>
                            <Col md={6} className="login-register login">

                                <Accordion defaultActiveKey="0">
                                    <Card>
                                        <Accordion.Toggle as={Card.Header} eventKey="1">
                                            <h2>
                                                Logowanie
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
                                                            <Form.Control type="email" placeholder="email"/>
                                                        </Col>
                                                    </Form.Group>
                                                    <Form.Group as={Row} controlId="formHorizontalPassword">
                                                        <Form.Label column sm={3}>
                                                            Hasło
                                                        </Form.Label>
                                                        <Col sm={9}>
                                                            <Form.Control type="password" placeholder="hasło"/>
                                                        </Col>
                                                    </Form.Group>
                                                    <Form.Group as={Row}>
                                                        <Col sm={{span: 10, offset: 2}}>
                                                            <Button type="submit">Zaloguj się</Button>
                                                        </Col>
                                                    </Form.Group>
                                                </Form>
                                            </Card.Body>
                                        </Accordion.Collapse>
                                    </Card>
                                </Accordion>
                            </Col>

                            <Col md={6} className="login-register register">
                                <Accordion defaultActiveKey="0">
                                    <Card>
                                        <Accordion.Toggle as={Card.Header} eventKey="2">
                                            <h2>
                                                Rejestracja
                                            </h2>
                                        </Accordion.Toggle>
                                        <Accordion.Collapse eventKey="2">
                                            <Card.Body>
                                                <Form>
                                                    <Form.Group as={Row} controlId="formHorizontalName">
                                                        <Form.Label column sm={3}>
                                                            Nazwa użytkownika
                                                        </Form.Label>
                                                        <Col sm={9}>
                                                            <Form.Control type="text" placeholder="nazwa"/>
                                                        </Col>
                                                    </Form.Group>
                                                    <Form.Group as={Row} controlId="formHorizontalEmail">
                                                        <Form.Label column sm={3}>
                                                            Email
                                                        </Form.Label>
                                                        <Col sm={9}>
                                                            <Form.Control type="email" placeholder="email"/>
                                                        </Col>
                                                    </Form.Group>
                                                    <Form.Group as={Row} controlId="formHorizontalPassword">
                                                        <Form.Label column sm={3}>
                                                            Hasło
                                                        </Form.Label>
                                                        <Col sm={9}>
                                                            <Form.Control type="password" placeholder="hasło"/>
                                                        </Col>
                                                    </Form.Group>
                                                    <Form.Group as={Row} controlId="formHorizontalPassword2">
                                                        <Form.Label column sm={3}>
                                                            Powtórz hasło
                                                        </Form.Label>
                                                        <Col sm={9}>
                                                            <Form.Control type="password" placeholder="hasło"/>
                                                        </Col>
                                                    </Form.Group>
                                                    <Form.Group as={Row}>
                                                        <Col sm={{span: 10, offset: 2}}>
                                                            <Button type="submit">Zarejestruj się</Button>
                                                        </Col>
                                                    </Form.Group>
                                                </Form>
                                            </Card.Body>
                                        </Accordion.Collapse>
                                    </Card>
                                </Accordion>
                            </Col>

                            <Col className="login-register-info">
                                <Accordion defaultActiveKey="0">
                                    <Card>
                                        <Accordion.Toggle as={Card.Header} eventKey="3">
                                            <h2>
                                                Info
                                            </h2>
                                        </Accordion.Toggle>
                                        <Accordion.Collapse eventKey="3">
                                            <Card.Body>
                                                Elo melo 320 strona do wymiany/wypozyczenia ksiazek
                                            </Card.Body>
                                        </Accordion.Collapse>
                                    </Card>
                                </Accordion>

                            </Col>
                        </Row>
                    </Container>
                </div>
            );
        }
    }
}

export default Home;