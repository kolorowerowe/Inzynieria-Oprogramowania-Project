import React, {Component} from 'react';
import {Container, Row, Col} from 'react-bootstrap';
import "./Search.css"


class Search extends Component {

    books=[];
    state = {};
    form = document.forms['searchBookByTitle'];

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        console.log(data);

        let post_data = {
            title: data.get("title")
        };

        console.log(post_data);
        fetch('/api/books/put', {
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
        fetch('/api/books/all')
            .then(response => response.json())
            .then(books_res => {
                this.books = books_res;
                this.setState({update: 2});

            });
    }


    render() {
        return (
            <div className="books">
                <h2>Search BOOKS by name:</h2>
                <form id="searchBookByTitle" onSubmit={this.handleSubmit}>
                    <input type={"text"} name={"title"} required={true}/><br/>
                    <input type={"submit"} value={"Submit"}/>
                </form>
                <Container>
                    <Row className="bookRow">
                        <Col className="bookCol" md={2}>ID</Col>
                        <Col className="bookCol" md={5}>NAME</Col>
                        <Col className="bookCol" md={5}>AUTHOR</Col>
                    </Row>
                    {this.books.map((book) => <Row className="bookRow">
                        <Col className="bookCol" md={2}>{book.id}</Col>
                        <Col className="bookCol" md={5}>{book.name}</Col>
                        <Col className="bookCol" md={5}>{book.author}</Col>
                    </Row>)}
                </Container>
            </div>
        );
    }
}

export default Search;