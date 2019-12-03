import React, {Component} from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";

import Users from './components/Users'
import Home from './components/Home'
import MyNavbar from './components/MyNavbar'

import './App.css';


class App extends Component {


    render() {
        return (
            <Router>
                <div>
                    <MyNavbar/>

                    <div id="container">
                        <Switch>
                            <Route path="/about">
                                <About/>
                            </Route>
                            <Route path="/users">
                                <Users/>
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

function About() {
    return <h2>About</h2>;
}


export default App;
