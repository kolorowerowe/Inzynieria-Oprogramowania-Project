import React, {Component} from 'react';
import {Container, Row, Col} from 'react-bootstrap';
import "./Search.css"


const InitialState ={
    attributeValue: "book"
}

// let searchAttributeFor = "-";
// let searchValueFor = "-";

class Search extends Component {

    books = [];
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

    componentDidMount() {
        fetch('/api/books/search/result')
            .then(response => response.json())
            .then(books_res => {
                //TODO books -> result?
                this.books = books_res;
                this.setState({update: 2});
            });
        console.log("myMess");
        console.log(this.books);
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
                            <input type={"text"} name={"value"} class="form-marcin" id={"mainInput"} required={true}/>
                            <input type={"submit"} value={"Szukaj"} className="btn btn-success-marcin"/>

                            <br/>
                        </form>
                        <div>
                            {/*<p>Search result for attribute:{searchAttributeFor} and value:{searchValueFor}</p>*/}
                        </div>
                        <Container>
                            <Row className="bookRow">
                                <Col className="bookCol" md={2}>ID</Col>
                                <Col className="bookCol" md={5}>NAME</Col>
                                <Col className="bookCol" md={5}>AUTHOR</Col>
                            </Row>
                            {this.books.map((book) => <Row className="bookRow">
                                <Col className="bookCol" md={2}>{book.book_id}</Col>
                                <Col className="bookCol" md={5}>{book.title}</Col>
                                <Col className="bookCol" md={5}>{book.author}</Col>
                            </Row>)}
                        </Container >
                    </div>
                </div>
            </div>
        );
    }
}
export default Search;