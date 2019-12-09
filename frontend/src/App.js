import React, {Component} from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import {Nav, Navbar} from "react-bootstrap";

import Users from './components/Users'
import AddBook from './components/AddBook'

import './App.css';


class App extends Component {


    render() {
        return (
            <Router>
                <div>

                    <Navbar bg="dark" variant="dark">
                        <Navbar.Brand href="/">SwapBook</Navbar.Brand>
                        <Nav className="mr-auto">
                            <Nav.Link href="/about">About</Nav.Link>
                            <Nav.Link href="/users">Użytkownicy</Nav.Link>
                            <Nav.Link href="/addbook">Dodaj książke</Nav.Link>

                        </Nav>
                    </Navbar>

                    <div id="container">
                        <Switch>
                            <Route path="/about">
                                <About/>
                            </Route>
                            <Route path="/users">
                                <Users/>
                            </Route>
                            <Route path="/addbook">
                                <AddBook/>
                            </Route>
                            <Route path="/">
                                <Home/>
                            </Route>
                        </Switch>
                    </div>
                </div>
            </Router>
        );
    }
}

function Home() {
    return <h2>Home</h2>;
}

//??? Czemu musi byc w Divie? To wymaganie FrameWorka?
function About() {
    return (
        <div className="asd">
            <h2>About</h2>
            <p>Jakis tekst</p>
        </div>
            );
}


export default App;
