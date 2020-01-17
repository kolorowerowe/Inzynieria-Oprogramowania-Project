import React, {Component} from 'react';
import {Container, Row, Col, Nav} from 'react-bootstrap';
import "./Search.css"

const InitialState = {
    attributeValue: "book"
}
// let ActualSpecimenBookId =52;

class Search extends Component {

    books = [];
    specimens = [];
    // actualSpecimenBookId = 52;
    state = InitialState;
    form = document.forms['searchBookByTitle'];

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleAttributeChange = this.handleAttributeChange.bind(this);
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

                            <br/>
                        </form>
                        <div>
                            {/*<p>Search result for attribute:{searchAttributeFor} and value:{searchValueFor}</p>*/}
                        </div>
                        <Container>
                            <Row className="bookRow">
                                <Col className="searchCol" md={2}>ID</Col>
                                <Col className="searchCol" md={5}>NAME</Col>
                                <Col className="searchCol" md={4}>AUTHOR</Col>
                                <Col className={"searchCol"} md={1}>LINK</Col>
                            </Row>
                            {this.books.map((book) => this.foo1(book))}
                        </Container >
<br/>

                        <Container>
                            <Row className="bookRow">
                                <Col className="searchCol" md={2}>ID</Col>
                                <Col className="searchCol" md={5}>NAME</Col>
                                <Col className="searchCol" md={4}>STAN</Col>
                                <Col className={"searchCol"} md={1}>LINK</Col>
                            </Row>
                            {this.specimens.map((specimen) => <Row className="bookRow">
                                <Col className="searchCol" md={2}>{specimen.specimen_id}</Col>
                                <Col className="searchCol" md={5}>{specimen.title}</Col>
                                <Col className="searchCol" md={4}>{specimen.condition}</Col>
                                {/*<Col className={"searchCol"}  md={1}><button className={"Button1"} onClick={()=>{loadBookPage(specimen.book_id)}}>Wypożycz</button>*/}
                                {/*</Col>*/}
                            </Row>)}
                        </Container >
                    </div>
                </div>
            </div>
        );
    }




    foo1(book)
    {

        if(book.book_id==sessionStorage.getItem('bookId')){
            return (<Container>
                <Row className="bookRow">
                <Col className="bookCol" md={2}>{book.book_id}</Col>
                <Col className="bookCol" md={5}>{book.title}</Col>
                <Col className="bookCol" md={4}>{"MARIO"}</Col>
            </Row>
            {this.specimens.map((specimen) => <Row className="bookRow">
                <Col className="searchCol" md={2}>{specimen.specimen_id}</Col>
                <Col className="searchCol" md={5}>{specimen.title}</Col>
                <Col className="searchCol" md={4}>{specimen.condition}</Col>
                {/*<Col className={"searchCol"}  md={1}><button className={"Button1"} onClick={()=>{loadBookPage(specimen.book_id)}}>Wypożycz</button>*/}
                {/*</Col>*/}
            </Row>)}
            </Container>);
        }
        else return (
            <Row className="bookRow">
            <Col className="bookCol" md={2}>{book.book_id}</Col>
            <Col className="bookCol" md={5}>{book.title}</Col>
            <Col className="bookCol" md={4}>{book.author}</Col>
            <Col className="bookCol" md={1}>{<button onClick={()=>this.loadBookPage(book.book_id)}>Wypożycz</button>}</Col>
            </Row>
        )
    }


}
export default Search;