import React, {Component} from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import {Button, Form, FormControl, Nav, Navbar} from "react-bootstrap";

import Users from './components/Users'

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
                  </Nav>
              </Navbar>

              {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
              <Switch>
                  <Route path="/about">
                      <About />
                  </Route>
                  <Route path="/users">
                      <Users />
                  </Route>
                  <Route path="/">
                      <Home />
                  </Route>
              </Switch>
          </div>
      </Router>
    );
  }
}

function Home() {
    return <h2>Home</h2>;
}

function About() {
    return <h2>About</h2>;
}


export default App;
