import React, {Component} from 'react';
import {Container, Row, Col} from 'react-bootstrap';
import "./AddBook.css"


class Books extends Component {

    state = {}; // # po kiego to jest ?
    books = [];
    form = document.forms['addBookForm'];

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
            name : data.get("name"),
            author: data.get("author")
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
                <h2>Add Book</h2>
                Miejsce na dodanie książki<br/>
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
                <br/>
                <form id="addBookForm" onSubmit={this.handleSubmit}>

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

export default Books;