import React, {Component} from 'react';
import {Container, Row, Col} from 'react-bootstrap';
import "./AddSpecimen.css"


class Specimen extends Component {

    state = {}; // # po kiego to jest ?
    specimens = [];
    form = document.forms['addSpecimenForm'];

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
            userId: data.get("userId"),
            title : data.get("title"),
            state: data.get("state"),
            numberOfPages: data.get("numberOfPages"),
            author: data.get("author"),
            releaseDate: data.get("releaseDate"),
            releaseNumber: data.get("releaseNumber"),
            isbn: data.get("isbn"),
            publishingHouse:data.get("publishingHouse"),
            rentalTime: data.get("rentalTime")
        };
        console.log(post_data);
        fetch('/api/specimens/put', {
            method: 'POST',
            body: JSON.stringify(post_data),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            function () {
                console.log("Successfully send form data");
                //window.location.reload();
            }
        ).catch(function () {
            console.log("Error while sending")
        });


    }

    componentDidMount() {
        fetch('/api/specimens/all')
            .then(response => response.json())
            .then(specimens_res => {
                console.log(specimens_res);
                this.specimens = specimens_res;
                this.setState({update: 1});

            });
    }


    render() {
        return (
            <div className="specimens">
                <h2>Add Egzemplarz</h2>
                Miejsce na dodanie egzemplarza<br/>
                <Container>
                    <Row className="specimenRow">
                        <Col className="specimenCol" md={2}>ID</Col>
                        <Col className="specimenCol" md={5}>NAME</Col>
                        <Col className="specimenCol" md={5}>AUTHOR</Col>
                    </Row>
                    {this.specimens.map((specimen) => <Row className="specimenRow">
                        <Col className="specimenCol" md={2}>{specimen.id}</Col>
                        <Col className="specimenCol" md={5}>{specimen.name}</Col>
                        <Col className="specimenCol" md={5}>{specimen.author}</Col>
                    </Row>)}
                </Container>
                <br/>
                <form id="addSpecimenForm" onSubmit={this.handleSubmit}>

                    <label>
                        Id:
                        <input type="text" name="id" />
                    </label>
                    <label>
                        Name:
                        <input type="text" name="name" />
                    </label>
                    <label>
                        Author:
                        <input type="text" name="author" />
                    </label>

                    <input type="submit" value="Submit" />
                </form>
            </div>
        );
    }
}

export default Specimen;