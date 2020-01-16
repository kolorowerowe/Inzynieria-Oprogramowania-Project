import React, {Component} from 'react';
import "./User.css"

class User extends Component {
    constructor(props) {
        super(props);
        this.user = props.user;
    }

    render() {
        return (
            <div className="container h-100">

            <div className="row p-5">
                <div className="col-12 col-lg-4">
                </div>
                <div className="col-12 col-lg-8 d-flex justify-content-center flex-column">
                    <h3>{this.user.name} <small className="text-black-50">{this.user.email}</small></h3>

                    <p className="mt-4">
                        Napisanych recenzji [książek]: <strong className="ml-2">25</strong><br/>
                        Napisanych opini [o userach]: <strong className="ml-2">10</strong><br/>
                        Twoja Ocena: <strong className="ml-2">7,5/10</strong>
                    </p>

                    <p className="my-4">
                        Wypożyczonych książek: <strong className="ml-2">100</strong><br/>
                        Książki w biblioteczce: <strong className="ml-2">41</strong><br/>
                        Wypożyczone książki od innych: <strong className="ml-2">2</strong>
                    </p>

                    <div>
                        <a href="login.html" className="btn btn-success mt-5">Moje Recenzje i Opinie</a>
                        <a href="register.html" className="btn btn-border mt-5 ml-3">Recenzje i Opinie o Mnie</a>
                    </div>
                </div>
            </div>
        </div>
        );
    }
}

export default User;