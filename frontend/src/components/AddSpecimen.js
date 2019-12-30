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
                        <Col className="specimenCol" md={1}>ID</Col>
                        <Col className="specimenCol" md={2}>Title</Col>
                        <Col className="specimenCol" md={1}>State</Col>
                        <Col className="specimenCol" md={1}>Num OfPages</Col>
                        <Col className="specimenCol" md={1}>Author</Col>
                        <Col className="specimenCol" md={1}>Release Date</Col>
                        <Col className="specimenCol" md={1}>release Number</Col>
                        <Col className="specimenCol" md={1}>ISBN</Col>
                        <Col className="specimenCol" md={1}>Publishing House</Col>
                        <Col className="specimenCol" md={1}>Rental Time</Col>
                        <Col className="specimenCol" md={1}>Photo</Col>
                    </Row>
                    {this.specimens.map((specimen) => <Row className="specimenRow">
                        <Col className="specimenCol" md={1}>{specimen.specimen_id}</Col>
                        <Col className="specimenCol" md={2}>{specimen.title}</Col>
                        <Col className="specimenCol" md={1}>{specimen.condition}</Col>
                        <Col className="specimenCol" md={1}>{specimen.number_pages}</Col>
                        <Col className="specimenCol" md={1}>{specimen.author}</Col>
                        <Col className="specimenCol" md={1}>{specimen.release_date}</Col>
                        <Col className="specimenCol" md={1}>{specimen.issue_number}</Col>
                        <Col className="specimenCol" md={1}>{specimen.isbn}</Col>
                        <Col className="specimenCol" md={1}>{specimen.publishing_house}</Col>
                        <Col className="specimenCol" md={1}>{specimen.loan_period}</Col>
                        <Col className="specimenCol" md={1}><img className="img-fluid" src = {specimen.photo_url}/></Col>
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
                <div className="container h-100">
                    <div className="row h-100">
                        <div className="col d-flex align-items-center justify-content-center flex-column">
                            <form id="addSpecimenForm" onSubmit={this.handleSubmit}>
                                <label>
                                    <Row className={"addSpecimenForm"}>
                                        <Col className={"addSpecimenForm"}>Title:</Col>
                                        <Col className={"addSpecimenForm"}><input type="text" name="title" required={true} class="form-control"/></Col>
                                    </Row>
                                </label>
                                <br/>
                                <label>
                                    <Row className={"addSpecimenForm"}>
                                        <Col>State:</Col>
                                        <Col>
                                            <select className={"selectCondition"} name="condition" value={this.state.stateValue} onChange={this.handleStateChange}>
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
                                        <Col><input type="text" name="number_pages" required={true} class="form-control" id={"pages"}/></Col>
                                        <div className="errorMessage" >{this.state.numberOfPagesError}</div>
                                    </Row>
                                </label>
                                <br/>
                                <label>
                                    <Row>
                                        <Col>Author:</Col>
                                        <Col><input type="text" name="author" required={true} class="form-control"/></Col>
                                    </Row>
                                </label>
                                <br/>
                                <label>
                                    <Row>
                                        <Col>ReleaseDate:</Col>
                                        <Col><input type="date" name="release_date" required={true} class="form-control" id={"ReleaseDate"}/></Col>
                                        <div className="errorMessage">{this.state.releaseDateError}</div>
                                    </Row>
                                </label>
                                <br/>
                                <label>
                                    <Row>
                                        <Col>ReleaseNumber:</Col>
                                        <Col><input type="text" name="issue_number" required={true} class="form-control"/></Col>
                                    </Row>
                                </label>
                                <br/>
                                <label>
                                    <Row>
                                        <Col>ISBN:</Col>
                                        <Col><input type="text" name="isbn" required={true} class="form-control"/></Col>
                                    </Row>
                                </label>
                                <br/>
                                <label>
                                    <Row>
                                        <Col>PublishingHouse:</Col>
                                        <Col><input type="text" name="publishing_house" required={true} class="form-control"/></Col>
                                    </Row>
                                </label>
                                <br/>
                                <label>
                                    <Row>
                                        <Col>Ready_for_renting: </Col>
                                        <br/>
                                        <Col><input type="checkbox" name={"readyForRenting"} onClick={this.handleRentalTimeStateChange} id={"ReadyForRentingCheckBox"}/>
                                            <span className={"checkmark"}></span></Col>
                                        <br/>
                                    </Row>
                                </label>
                                <br/>
                                <label>
                                    <Row>
                                        <Col>RentalTime:</Col>
                                        <Col><input id={"RentalTime"} class="form-control" type="number" name="loan_period" required={!this.state.rentalTimeDisabled} disabled={(this.state.rentalTimeDisabled)?true:false }/></Col>
                                        <div className="errorMessage">{this.state.rentalTimeError}</div>
                                    </Row>
                                </label>
                                <br/>
                                <label>
                                    <Row>
                                        <Col>Photo url:</Col>
                                        <Col><input type="text" name="photo_url" required={true} class="form-control"/></Col>
                                    </Row>
                                </label>
                                <br/>
                                <input type="submit" value="Submit"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        );
    }
}

export default Specimen;