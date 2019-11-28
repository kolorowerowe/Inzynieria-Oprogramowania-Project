import React, {Component} from 'react';
import {Container, Row, Col, Image} from 'react-bootstrap';
import "./Users.css"


class Users extends Component {

    state = {};
    users = [];

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        fetch('/api/users/all')
            .then(response => response.json())
            .then(users_res => {
                this.users = users_res;
                this.setState({update: 1});

            });
    }


    render() {
        return (
            <div className="userss">
                <h2>Users</h2>
                below you an find all users <br/>
                <Container>
                    <Row className="userRow">
                        <Col className="userCol" md={2}>ID</Col>
                        <Col className="userCol" md={10}>NAME</Col>
                    </Row>
                    {this.users.map((user) => <Row className="userRow">
                        <Col className="userCol" md={2}>{user.id}</Col>
                        <Col className="userCol" md={10}>{user.name}</Col>
                    </Row>)}
                </Container>
            </div>
        );
    }
}

export default Users;