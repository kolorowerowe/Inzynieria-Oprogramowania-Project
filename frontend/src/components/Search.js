import React, {Component} from 'react';
import {Container, Row, Col, Nav} from 'react-bootstrap';
import "./Search.css"
import {getCookie} from "../Functions/CookiesHandler";

const InitialState = {
    attributeValue: "book"
}

class Search extends Component {

    books = [];
    specimens = [];
    state = InitialState;
    form = document.forms['searchBookByTitle'];

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleAttributeChange = this.handleAttributeChange.bind(this);
    }

    resetOpenedSpecimen()
    {
        sessionStorage.setItem('bookId',"0");
        window.location.reload();
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        console.log(data);

        let post_data = {
            attribute: data.get("attribute"),
            value: data.get("value")
        };

        console.log(post_data);
        console.log(this.books);
        // searchAttributeFor = data.get("attribute");
        // searchValueFor = data.get("value");
        // console.log(searchAttributeFor);

        fetch('/api/books/search/title/regex', {
            method: 'POST',
            body: JSON.stringify(post_data),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(
            function () {
                console.log("Successfully send form data");
                window.location.reload();
            }
        ).catch(function () {
            console.log("Error while sending")
        });
    }

    handleAttributeChange(event){
        this.setState({attributeValue: event.target.attributeValue});
    }

    loadBookPage(id) {
        sessionStorage.setItem('bookId',id);
        // ActualSpecimenBookId=id;
        console.log("Print: "+id);
        let post_data = {bookId: id};
        console.log(post_data);
        fetch('/api/specimens/bookId/'+id, {
            method: 'POST',
            body: '',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(
                function () {
                    console.log("Successfully send XXX data");
                    window.location.reload();
                }
            ).catch(function () {
            console.log("Error while sending")
        });
    }

    componentDidMount() {
        fetch('/api/books/search/result')
            .then(response => response.json())
            .then(books_res => {
                //TODO books -> result?
                this.books = books_res;
                this.setState({update: 2});
            });
        fetch('/api/specimens/bookId/result')
            .then(response => response.json())
            .then(specimens_res => {
                this.specimens = specimens_res;
                this.setState({update: 3});
            });
        console.log("myMess");
        console.log(this.books);
        console.log(this.specimens);
    }

    render() {
        return (
            <div className="container h-100">
                <div className="row h-100">
                    <div className="col d-flex align-items-center justify-content-center flex-column">
                        <h2>Przeszukiwanie:</h2>
                        <form id="searchBookByTitle" onSubmit={this.handleSubmit}>
                            <label>
                                    <select id={"selectState"} name="attribute" value={this.state.attributeValue} onChange={this.handleAttributeChange}>
                                        <option value={"book"}>Book</option>
                                        <option value={"author"}>Author</option>
                                    </select>
                            </label>
                            <input type={"text"} name={"value"} className="form-marcin" id={"mainInput"} required={true}/>
                            <input type={"submit"} value={"Szukaj"} className="btn btn-success-marcin"/>
                            <input type={"button"} value={"Reset"} className="btn btn-success-marcin-reset" onClick={() =>{this.resetSearch()}}/>
                            <br/>
                        </form>
                        <div>
                            {/*<p>Search result for attribute:{searchAttributeFor} and value:{searchValueFor}</p>*/}
                        </div>
                        <Container>
                            <Row className="bookRow">
                                {/*<Col className="searchColBookHeader" md={2}>ID</Col>*/}
                                <Col className={"searchColBookHeader"} md={2}>ZDJĘCIE</Col>
                                <Col className="searchColBookHeader" md={4}>TYTUŁ</Col>
                                <Col className="searchColBookHeader" md={4}>AUTOR</Col>
                                <Col className={"searchColBookHeader"} md={2}>AKCJA</Col>
                            </Row>
                            {this.books.map((book) => this.foo1(book))}
                            <Row className={"searchRowBookLast"}></Row>
                        </Container >
                    <br/>
                    </div>
                </div>
            </div>
        );
    }

    foo1(book)
    {
        if(book.book_id==sessionStorage.getItem('bookId')){
            return (
                <Container>
                    {/*<Row className={"searchColBookLast"}></Row>*/}
                    <Row className="bookRow">
                        {/*<Col className="choosenOne" md={2}>{book.book_id}</Col>*/}
                        <Col className="choosenOne1" md={2}><img className="img-fluid" src = {book.photo_url}/></Col>
                        <Col className="choosenOne" md={4}>{book.title}</Col>
                        <Col className="choosenOne" md={4}>{book.author}</Col>
                        <Col className="choosenOne1" md={2}>{<button className={"Button2"} onClick={()=>{this.resetOpenedSpecimen()}}>Zwiń</button>}</Col>
                    </Row>
                    <br/>
                    <Row className="bookRow">
                        {/*<Col className="searchColSpecimenHeader" md={2}>ID</Col>*/}
                        <Col className="searchColSpecimenHeader" md={3}>STAN</Col>
                        <Col className="searchColSpecimenHeader" md={3}>DATA WYDANIA</Col>
                        <Col className="searchColSpecimenHeader" md={3}>WYDAWNICTWO</Col>
                        <Col className="searchColSpecimenHeader" md={2}>CZAS WYPOZYCZENIA</Col>
                        <Col className={"searchColSpecimenHeader"} md={1}>AKCJA</Col>
                     </Row>
                    {this.specimens.map((specimen) => <Row className="bookRow">
                        {/*<Col className="searchColSpecimen" md={2}>{specimen.specimen_id}</Col>*/}
                        <Col className="searchColSpecimen" md={3}>{specimen.condition}</Col>
                        <Col className="searchColSpecimen" md={3}>{specimen.release_date}</Col>
                        <Col className="searchColSpecimen" md={3}>{specimen.publishing_house}</Col>
                        <Col className="searchColSpecimen" md={2}>{specimen.loan_period}</Col>
                        <Col className={"searchColSpecimen"}  md={1}><button className={"Button1"}  onClick={()=>this.loanTheSpecimen(specimen)}>{this.tekstGuzika(specimen)}</button>
                    </Col>
                    </Row>)}
                    <Row className={"searchColSpecimenLastRow"}>
                        <Col className="searchColSpecimenLast" md={11}></Col>
                    </Row>
                    {/*<Row className={"searchColBookFirst"}><Col c md={12}>123 </Col></Row>*/}

                    <br/>
                </Container>);
        }
        else return (
            <Container>
                <Row className="bookRow">
                    {/*<Col className="searchColBook" md={2}>{book.book_id}</Col>*/}
                    <Col className="searchColBook" md={2}><img className="img-fluid" src = {book.photo_url}/></Col>
                    <Col className="searchColBook" md={4}>{this.shortenTitleTo40(book.title)}</Col>
                    <Col className="searchColBook" md={4}>{book.author}</Col>
                    <Col className="searchColBook" md={2}>{<button className={"Button1"} onClick={()=>this.loadBookPage(book.book_id)}>Pokaż</button>}</Col>
                </Row>
            </Container>
        )
    }

    is_disabled(specimen) {
        if(specimen.status=='AVAILABLE') return true;
        return false;
    }


    loanTheSpecimen(specimen) {
        if(specimen.status=='AVAILABLE') {
            console.log(specimen);
            let post_data ={
                userId: getCookie(getCookie("userId")),
                specimenId: specimen.specimen_id
            }

            fetch('/api/books/loan', {
                method: 'POST',
                body: JSON.stringify(post_data),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(
                    function () {
                        console.log("Successfully send form data");
                        window.location.reload();
                    }
                ).catch(function () {
                console.log("Error while sending")
            });
       }

    }

    shortenTitleTo40(title) {
        if(title.length >=40)
        {
            let sub = title.substring(0,37);
            sub=sub+"...";
            return sub;
        }
        else return title;
    }


    resetSearch() {
        sessionStorage.setItem('bookId',"0");
        let post_data = {
            attribute: "book",
            value: " "
        };
        fetch('/api/books/search/title/regex', {
            method: 'POST',
            body: JSON.stringify(post_data),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(
                function () {
                    console.log("Successfully send form data");
                    window.location.reload();
                }
            ).catch(function () {
            console.log("Error while sending")
        });
    }

    tekstGuzika(specimen) {
        let stan = specimen.status
        if(stan=='AVAILABLE') return "Pożycz";
        return "Niedostępny";

    }
}
export default Search;