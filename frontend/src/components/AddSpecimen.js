import React, {Component} from 'react';
import {Container, Row, Col} from 'react-bootstrap';
import "./AddSpecimen.css"


const InitialState = {
    numberOfPagesError: "",
    titleError: "",
    authorError: "",
    stateValue: "Average"
}

class Specimen extends Component {

    state = InitialState;
    specimens = [];
    form = document.forms['addSpecimenForm'];

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleStateChange = this.handleStateChange.bind(this);
    }

    validateForm = (data) => {
        let titleError = "";
        let numberOfPagesError = "";
        let authorError =  "";
        let hasAnyErrors = false;
        console.log(data.get("title"));

        if(!data.get("numberOfPages").match(/[1-9][0-9]*/)) {
            numberOfPagesError="Invalid number of pages";
            this.setState({numberOfPagesError});
            hasAnyErrors=true;
        }
        if(data.get("title")==="") {
            titleError = "Title cannot be empty";
            this.setState({titleError});
            hasAnyErrors=true;
        }
        if(data.get("author")==="") {
            authorError = "Specify the author";
            this.setState({authorError});
            hasAnyErrors=true;
        }

        if(hasAnyErrors) {
            return false;
        }

        return true;
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        const isValid = this.validateForm(data);
        console.log(data);

        if(isValid) {
            let post_data = {
                id: data.get("id"),
                //userId: data.get("userId"),
                title: data.get("title"),
                state: data.get("state"),
                numberOfPages: data.get("numberOfPages"),
                author: data.get("author"),
                //releaseDate: data.get("releaseDate"),
                //releaseNumber: data.get("releaseNumber"),
                //isbn: data.get("isbn"),
                //publishingHouse:data.get("publishingHouse"),
                //rentalTime: data.get("rentalTime")
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
                    window.location.reload();
                }
            ).catch(function () {
                console.log("Error while sending")
            });
            this.setState(InitialState);
        }
    }

    handleStateChange(event) {
        this.setState({stateValue: event.target.stateValue});
    }

    componentDidMount() {
        fetch('/api/specimens/all')
            .then(response => response.json())
            .then(specimens_res => {
                console.log(specimens_res);
                this.specimens = specimens_res;
                console.log(specimens_res);
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
                        <Col className="specimenCol" md={1}>ID</Col>
                        <Col className="specimenCol" md={3}>Title</Col>
                        <Col className="specimenCol" md={1}>State</Col>
                        <Col className="specimenCol" md={1}>NumOfPages</Col>
                        <Col className="specimenCol" md={3}>Author</Col>
                        {/*<Col className="specimenCol" md={2}>ReleasheDate</Col>*/}
                        {/*<Col className="specimenCol" md={2}>ReleasheNumber</Col>*/}
                        {/*<Col className="specimenCol" md={3}>ISBN</Col>*/}
                        {/*<Col className="specimenCol" md={1}>PublishingHouse</Col>*/}
                        {/*<Col className="specimenCol" md={1}>RentalTime</Col>*/}
                    </Row>
                    {this.specimens.map((specimen) => <Row className="specimenRow">
                        <Col className="specimenCol" md={1}>{specimen.id}</Col>
                        <Col className="specimenCol" md={3}>{specimen.title}</Col>
                        <Col className="specimenCol" md={1}>{specimen.state}</Col>
                        <Col className="specimenCol" md={1}>{specimen.numberOfPages}</Col>
                        <Col className="specimenCol" md={3}>{specimen.author}</Col>
                        {/*<Col className="specimenCol" md={2}>{specimen.releaseDate}</Col>*/}
                        {/*<Col className="specimenCol" md={2}>{specimen.releaseNumber}</Col>*/}
                        {/*<Col className="specimenCol" md={3}>{specimen.isbn}</Col>*/}
                        {/*<Col className="specimenCol" md={1}>{specimen.publishingHouse}</Col>*/}
                        {/*<Col className="specimenCol" md={1}>{specimen.rentalTime}</Col>*/}
                    </Row>)}
                </Container>
                <br/>
                <form id="addSpecimenForm" onSubmit={this.handleSubmit}>

                    <label>
                        Id:
                        <input type="text" name="id"/>
                    </label>
                    <br/>
                    <label>
                        Title:
                        <input type="text" name="title" required={true}/>
                        {/*<div className="errorMessage">{this.state.titleError}</div>*/}
                    </label>
                    <br/>
                    <label>
                        State:
                        {/*<input type="select" name="state" />*/}
                        <select name="state" value={this.state.stateValue} onChange={this.handleStateChange}>
                            <option value={"Dog-eaten"}>Dog-eaten</option>
                            <option value={"Poor"}>Poor</option>
                            <option value={"Average"}>Average</option>
                            <option value={"Good"}>Good</option>
                            <option value={"Perfect"}>Perfect</option>
                        </select>
                    </label>
                    <br/>
                    <label>
                        NumberOfPages:
                        <input type="text" name="numberOfPages" />
                        <div className="errorMessage">{this.state.numberOfPagesError}</div>
                    </label>
                    <br/>
                    <label>
                        Author:
                        <input type="text" name="author" required={true}/>
                        {/*<div className="errorMessage">{this.state.authorError}</div>*/}
                    </label>
                    {/*<br/>*/}
                    {/*<label>*/}
                    {/*    ReleaseNumber:*/}
                    {/*    <input type="text" name="releaseNumber" />*/}
                    {/*</label>*/}
                    {/*<br/>*/}
                    {/*<label>*/}
                    {/*    ISBN:*/}
                    {/*    <input type="text" name="isbn" />*/}
                    {/*</label>*/}
                    {/*<br/>*/}
                    {/*<label>*/}
                    {/*    PublishingHouse:*/}
                    {/*    <input type="text" name="publishingHouse" />*/}
                    {/*</label>*/}
                    {/*<br/>*/}
                    {/*<label>*/}
                    {/*    RentalTime:*/}
                    {/*    <input type="text" name="rentalTime" />*/}
                    {/*</label>*/}
                    {/*<br/>*/}

                    <input type="submit" value="Submit" />
                </form>
            </div>
        );
    }
}

export default Specimen;