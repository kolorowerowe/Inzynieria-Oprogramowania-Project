import React, {Component} from 'react';
import "./MyNavbar.css"
import {Nav, Navbar} from "react-bootstrap";
import {Link} from "react-router-dom";


class MyNavbar extends Component {


    render() {
        return (
            <div className="my_navbar">
                <h2 className="my_navbar_title">
                    <Link to="/" > Swap-Book </Link>
                </h2>
                <Navbar bg="dark" variant="dark">
                    <Nav className="mr-auto">
                        <Nav.Link href="/about">About</Nav.Link>
                        <Nav.Link href="/users">Użytkownicy</Nav.Link>
                    </Nav>
                </Navbar>
            </div>
        );
    }
}

export default MyNavbar;