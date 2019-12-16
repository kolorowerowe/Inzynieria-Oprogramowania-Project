import React, {Component} from 'react';
import {Container, Row, Col} from 'react-bootstrap';
import "./AddSpecimen.css"

const InitialState = {
    numberOfPagesError: "",
    releaseDateError: "",
    rentalTimeError: "",
    stateValue: "Average",
    rentalTimeDisabled: true
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
        this.handleRentalTimeStateChange = this.handleRentalTimeStateChange.bind(this);
    }

    validateForm = (data) => {
        let numberOfPagesError = "";
        let releaseDateError = "";
        let rentalTimeError = "";
        let hasAnyErrors = false;

        if(!data.get("number_pages").match(/[1-9][0-9]*/)) {
            numberOfPagesError="Invalid number of pages";
            this.setState({numberOfPagesError});
            hasAnyErrors=true;
        }
        if(Date.parse(data.get("release_date")) > Date.now()) {
            releaseDateError = "Invalid Release Date - such day will already come";
            this.setState({releaseDateError});
            hasAnyErrors = true;
        }
        if(data.get("loan_period") < 0 ){
            rentalTimeError = "Invalid Rental Time- rental time cannot be less than 0!";
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
                //specimen_id: data.get("specimen_id"),
                //book_id: data.get("book_id"),
                //user_id: data.get("user_id"),
                // specimen_id: 11,
                book_id: 51,
                user_id: 11,
                title: data.get("title"),
                condition: data.get("condition"),
                number_pages: data.get("number_pages"),
                author: data.get("author"),
                release_date: data.get("release_date"),
                issue_number: data.get("issue_number"),
                isbn: data.get("isbn"),
                publishing_house:data.get("publishing_house"),
                loan_period: (this.state.rentalTimeDisabled?-1:data.get("loan_period")),
                photo_url: data.get("photo_url")
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

    handleRentalTimeStateChange(event){
        this.setState({rentalTimeDisabled: !this.state.rentalTimeDisabled});
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
                        <Col className="specimenCol" >Photo</Col>
                    </Row>
                    {this.specimens.map((specimen) => <Row className="specimenRow">
                        <Col className="specimenCol" >{specimen.specimen_id}</Col>
                        <Col className="specimenCol" >{specimen.title}</Col>
                        <Col className="specimenCol" >{specimen.condition}</Col>
                        <Col className="specimenCol" >{specimen.number_pages}</Col>
                        <Col className="specimenCol" >{specimen.author}</Col>
                        <Col className="specimenCol" >{specimen.release_date}</Col>
                        <Col className="specimenCol" >{specimen.issue_number}</Col>
                        <Col className="specimenCol" >{specimen.isbn}</Col>
                        <Col className="specimenCol" >{specimen.publishing_house}</Col>
                        <Col className="specimenCol" >{specimen.loan_period}</Col>
                        <Col className="specimenCol" ><img className="img-fluid" src = {specimen.photo_url}/></Col>
                    </Row>)}
                </Container>
                <br/>
                <h3>Tabela książek</h3>
                <Container>
                    <Row className="bookRow">
                        <Col className="bookCol" >ID</Col>
                        <Col className="bookCol" >NAME</Col>
                        <Col className="bookCol" >AUTHOR</Col>
                        <Col className="bookCol" >PHOTO</Col>
                    </Row>
                    {this.books.map((book) => <Row className="bookRow">
                        <Col className="bookCol" >{book.book_id}</Col>
                        <Col className="bookCol" >{book.title}</Col>
                        <Col className="bookCol" >{book.author}</Col>
                        <Col className="bookCol" ><img className="img-fluid" src = {book.photo_url}/></Col>
                    </Row>)}
                </Container>
                <br/>
                <form id="addSpecimenForm" onSubmit={this.handleSubmit}>
                    <label>
                        <Row className={"addSpecimenForm"}>
                            <Col className={"addSpecimenForm"}>Title:</Col>
                            <Col className={"addSpecimenForm"}><input type="text" name="title" required={true}/></Col>
                        </Row>
                    </label>
                    <br/>
                    <label>
                        <Row className={"addSpecimenForm"}>
                            <Col>State:</Col>
                            <Col>
                                <select name="condition" value={this.state.stateValue} onChange={this.handleStateChange}>
                                <option value={"Dog-eaten"}>Dog-eaten</option>
                                <option value={"Poor"}>Poor</option>
                                <option value={"Average"}>Average</option>
                                <option value={"Good"}>Good</option>
                                <option value={"Perfect"}>Perfect</option>
                                </select>
                            </Col>
                        </Row>
                    </label>
                    <br/>
                    <label>
                        <Row>
                            <Col>NumberOfPages:</Col>
                            <Col><input type="text" name="number_pages" required={true}/></Col>
                            <div className="errorMessage">{this.state.numberOfPagesError}</div>
                        </Row>
                    </label>
                    <br/>
                    <label>
                        Author:
                        <input type="text" name="author" required={true}/>
                    </label>
                    <br/>
                    <label>
                        ReleaseDate:
                        <input type="date" name="release_date" required={true} />
                        <div className="errorMessage">{this.state.releaseDateError}</div>
                    </label>
                    <br/>
                    <label>
                        ReleaseNumber:
                        <input type="text" name="issue_number" required={true}/>
                    </label>
                    <br/>
                    <label>
                        ISBN:
                        <input type="text" name="isbn" required={true}/>
                    </label>
                    <br/>
                    <label>
                        PublishingHouse:
                        <input type="text" name="publishing_house" required={true}/>
                    </label>
                    <br/>
                    <label>
                        Ready for renting:<br/>
                        <input type="checkbox" name={"readyForRenting"} onClick={this.handleRentalTimeStateChange}/>Yes<br/>
                    </label>
                    <br/>
                    <label>
                        RentalTime:
                        <input type="number" name="loan_period" required={!this.state.rentalTimeDisabled} disabled={(this.state.rentalTimeDisabled)?true:false}/>
                        <div className="errorMessage">{this.state.rentalTimeError}</div>
                    </label>
                    <br/>
                    <label>
                        Photo url:
                        <input type="text" name="photo_url" required={true}/>
                    </label>
                    <br/>
                    <input type="submit" value="Submit"/>
                </form>
            </div>
        );
    }
}

export default Specimen;