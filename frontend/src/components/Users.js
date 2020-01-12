import React, {Component} from 'react';
import {Container, Row, Col} from 'react-bootstrap';
import "./Users.css"


class Users extends Component {

    state = {};
    users = [];
    form = document.forms['addUserForm'];

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        console.log(data);

        let post_data = {
            id: data.get("user_id"),
            name : data.get("name")
        };
        console.log(post_data);
        fetch('/api/users/put', {
            method: 'POST',
            body: JSON.stringify(post_data),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            function () {
                console.log("Successfully send form data");
                window.location.reload();
            }
        ).catch(function () {
            console.log("Error while sending")
        });


    }

    componentDidMount() {
        fetch('/api/users/all')
            .then(response => response.json())
            .then(users_res => {
                this.users = users_res;
                //this.setState({update: 1});

            });
    }


    render() {
        return (
            <div className="userss">
                <h2>Users</h2>
                below you an find all users <br/>
                <Container>
                    <Row className="userRow">
                        <Col className="userCol" md={1}>ID</Col>
                        <Col className="userCol" md={3}>NAME</Col>
                        <Col className="userCol" md={3}>MAIL</Col>
                        <Col className="userCol" md={4}>ADDRESS</Col>
                    </Row>
                    {this.users.map((user) => <Row className="userRow">
                        <Col className="userCol" md={1}>{user.user_id}</Col>
                        <Col className="userCol" md={3}>{user.name}</Col>
                        <Col className="userCol" md={3}>{user.email}</Col>
                        <Col className="userCol" md={4}>{user.address}</Col>
                    </Row>)}
                </Container>
                <br/>
                <form id="addUserForm" onSubmit={this.handleSubmit}>

                    <label>
                        Id:
                        <input type="text" name="id" />
                    </label>
                    <label>
                        Name:
                        <input type="text" name="name" />
                    </label>
                    <input type="submit" value="Submit" />
                </form>
            </div>
        );
    }
}

export default Users;