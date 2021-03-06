import React, {Component} from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import {Nav, Navbar} from "react-bootstrap";

import Users from './components/Users'
import Search from './components/Search'
// import AddBook from './components/AddBook'
import AddSpecimen from './components/AddSpecimen'


import './App.css';

import Register from './components/Register'
import LoginControl from './components/LoginControl'



class App extends Component {


    render() {
        return (
            <Router>
                <div>

                    <Navbar bg="dark" variant="dark">
                        <Navbar.Brand href="/">SwapBook</Navbar.Brand>
                        <Nav className="mr-auto">
                            <Nav.Link href="/about">O stronie</Nav.Link>
                            {/*<Nav.Link href="/users">Użytkownicy</Nav.Link>*/}
                            <Nav.Link className="registerNaviBar" href="/register">Rejestracja</Nav.Link>
                            <Nav.Link className="loginNaviBar" href="/user">Login</Nav.Link>
                            <Nav.Link href="/search">Wyszukaj</Nav.Link>
                            {/*<Nav.Link href="/addbook">Dodaj książke</Nav.Link>*/}
                            <Nav.Link href="/addspecimen">Dodaj egzemplarz & książkę</Nav.Link>
                        </Nav>
                    </Navbar>

                    <div id="container">
                    <Switch>
                            <Route path="/about">
                                <About/>
                            </Route>
                            <Route path="/register">
                                <Register/>
                            </Route>
                            {/*<Route path="/users">*/}
                            {/*    <Users/>*/}
                            {/*</Route>*/}
                            <Route path={"/search"}>
                                <Search/>
                            </Route>
                            {/*<Route path="/addbook">*/}
                            {/*    <AddBook/>*/}
                            {/*</Route>*/}
                            <Route path="/addspecimen">
                                <AddSpecimen/>
                            </Route>
                            <Route patch="/user">
                                <LoginControl/>
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

function About() {
    return (
        <div className="container h-100">
            <div className="row h-100">
                <div className="col d-flex align-items-center justify-content-center flex-column">
                    <h3 className="mb-5">O stronie</h3>
                    <p>SwapBook albo też BookSwap to super stronka do wypożyczania innym książek!</p>
                    <p>Chcesz przeczytać kolejne tomy przygód Harrego Pottera a w bibliotekach nie ma  kolejnych cześci?</p>
                    <p>Sprawdz u nas! Tu na pewno ktoś  będzie chciał Ci ją wypożyczyć!!</p>
                </div>
            </div>
        </div>
            );
}


export default App;
