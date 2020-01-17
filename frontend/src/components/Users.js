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
            id: data.get("id"),
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
                this.setState({update: 1});

            });
    }


    render() {
        return (
            <div className="userss">
                <h2>Users</h2>
                <p>below you an find all users</p> <br/>
                <form id="addUserForm" onSubmit={this.handleSubmit}>
                    <label>
                        <Row>
                            <Col>Id:</Col>
                            <Col><input type="text" className="form-control" name="id"/></Col>
                        </Row>
                    </label><br/>
                    <label>
                        <Row>
                            <Col>Name:</Col>
                            <Col><input type="text" className="form-control" name="name"/></Col>
                        </Row>
                    </label><br/>
                    <input type="submit" className="btn btn-success" value="Submit"/>
                </form>
                <br/>
                <Container>
                    <Row className="userRow">
                        <Col className="userCol" md={1}>ID</Col>
                        <Col className="userCol" md={3}>NAME</Col>
                        <Col className="userCol" md={3}>MAIL</Col>
                        <Col className="userCol" md={4}>ADDRESS</Col>
                    </Row>
                    {this.users.map((user) => <Row className="userRow">
                        <Col className="userCol" md={1}>{user.id}</Col>
                        <Col className="userCol" md={3}>{user.name}</Col>
                        <Col className="userCol" md={3}>{user.email}</Col>
                        <Col className="userCol" md={4}>{user.address}</Col>
                    </Row>)}
                </Container>
                <br/>

            </div>
        );
    }
}

export default Users;