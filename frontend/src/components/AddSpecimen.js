import React, {Component} from 'react';
import {Container, Row, Col} from 'react-bootstrap';
import "./AddSpecimen.css"


const InitialState = {
    numberOfPagesError: "",
    releaseDateError: "",
    rentalTimeError: "",
    stateValue: "Average"
}

class Specimen extends Component {

    state = InitialState;
    specimens = [];
    books = [];
    form = document.forms['addSpecimenForm'];

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleStateChange = this.handleStateChange.bind(this);
    }

    validateForm = (data) => {
        let numberOfPagesError = "";
        let releaseDateError = "";
        let rentalTimeError = "";
        let hasAnyErrors = false;

        if(!data.get("numberOfPages").match(/[1-9][0-9]*/)) {
            numberOfPagesError="Invalid number of pages";
            this.setState({numberOfPagesError});
            hasAnyErrors=true;
        }
        if(Date.parse(data.get("releaseDate")) > Date.now()) {
            releaseDateError = "Invalid Release Date - such day will already come";
            this.setState({releaseDateError});
            hasAnyErrors = true;
        }
        if(Date.parse(data.get("rentalTime"))< Date.now()){
            rentalTimeError = "Invalid Rental Time- such day has already passed";
            this.setState({rentalTimeError});
            hasAnyErrors = true
        }

        if(hasAnyErrors) return false;
        return true;
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        const isValid = this.validateForm(data);
        console.log(data);

        if(isValid) {
            let post_data = {
                //id: data.get("id"),
                //userId: data.get("userId"),
                title: data.get("title"),
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
        fetch('/api/books/all')
            .then(response => response.json())
            .then(books_res => {
                this.books = books_res;
                this.setState({update: 2});

            });
    }


    render() {
        return (
            <div className="specimens">
                <h2>Add Egzemplarz</h2>
                Miejsce na dodanie egzemplarza<br/>
                <Container>
                    <Row className="specimenRow">
                        <Col className="specimenCol" >ID</Col>
                        <Col className="specimenCol" >Title</Col>
                        <Col className="specimenCol" >State</Col>
                        <Col className="specimenCol" >Num OfPages</Col>
                        <Col className="specimenCol" >Author</Col>
                        <Col className="specimenCol" >Release Date</Col>
                        <Col className="specimenCol" >release Number</Col>
                        <Col className="specimenCol" >ISBN</Col>
                        <Col className="specimenCol" >Publishing House</Col>
                        <Col className="specimenCol" >Rental Time</Col>
                    </Row>
                    {this.specimens.map((specimen) => <Row className="specimenRow">
                        <Col className="specimenCol" >{specimen.id}</Col>
                        <Col className="specimenCol" >{specimen.title}</Col>
                        <Col className="specimenCol" >{specimen.state}</Col>
                        <Col className="specimenCol" >{specimen.numberOfPages}</Col>
                        <Col className="specimenCol" >{specimen.author}</Col>
                        <Col className="specimenCol" >{specimen.releaseDate}</Col>
                        <Col className="specimenCol" >{specimen.releaseNumber}</Col>
                        <Col className="specimenCol" >{specimen.isbn}</Col>
                        <Col className="specimenCol" >{specimen.publishingHouse}</Col>
                        <Col className="specimenCol" >{specimen.rentalTime}</Col>
                    </Row>)}
                </Container>
                <br/>
                <h3>Tabela książek</h3>
                <Container>
                    <Row className="bookRow">
                        <Col className="bookCol" >ID</Col>
                        <Col className="bookCol" >NAME</Col>
                        <Col className="bookCol" >AUTHOR</Col>
                    </Row>
                    {this.books.map((book) => <Row className="bookRow">
                        <Col className="bookCol" >{book.id}</Col>
                        <Col className="bookCol" >{book.name}</Col>
                        <Col className="bookCol" >{book.author}</Col>
                    </Row>)}
                </Container>
                <br/>
                <form id="addSpecimenForm" onSubmit={this.handleSubmit}>

                    {/*<label>*/}
                    {/*    Id:*/}
                    {/*    <input type="text" name="id"/>*/}
                    {/*</label>*/}
                    <br/>
                    <label>
                        Title:
                        <input type="text" name="title" required={true}/>
                    </label>
                    <br/>
                    <label>
                        State:
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
                        <input type="text" name="numberOfPages" required={true}/>
                        <div className="errorMessage">{this.state.numberOfPagesError}</div>
                    </label>
                    <br/>
                    <label>
                        Author:
                        <input type="text" name="author" required={true}/>
                    </label>
                    <br/>
                    <label>
                        ReleaseDate:
                        <input type="date" name="releaseDate" required={true} />
                        <div className="errorMessage">{this.state.releaseDateError}</div>
                    </label>
                    <br/>
                    <label>
                        ReleaseNumber:
                        <input type="text" name="releaseNumber" required={true}/>
                    </label>
                    <br/>
                    <label>
                        ISBN:
                        <input type="text" name="isbn" required={true}/>
                    </label>
                    <br/>
                    <label>
                        PublishingHouse:
                        <input type="text" name="publishingHouse" required={true}/>
                    </label>
                    <br/>
                    <label>
                        RentalTime:
                        <input type="date" name="rentalTime" required={true} />
                        <div className="errorMessage">{this.state.rentalTimeError}</div>
                    </label>
                    <br/>

                    <input type="submit" value="Submit" required={true} />
                </form>
            </div>
        );
    }
}

export default Specimen;