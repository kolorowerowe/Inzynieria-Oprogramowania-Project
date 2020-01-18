import React, {Component} from 'react';
import "./User.css"

class User extends Component {
    constructor(props) {
        super(props);
        this.user = props.user;
        this.state = {statistic: {}};
        console.log("ctor");
        this.getStatistic();
    }

    getStatistic() {
        fetch('/api/users/statistic/'+this.user.id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.json())
        .then(data => {
            console.log(data);
            this.setState({statistic: data});
        })
        .catch(_ => {
            console.log("Error while sending");
        });
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
                        Napisanych recenzji [książek]: <strong className="ml-2">{this.state.statistic.reviewCount}</strong><br/>
                        Napisanych opini [o userach]: <strong className="ml-2">{this.state.statistic.writtenOpinionCount}</strong><br/>
                        {/* Twoja Ocena: <strong className="ml-2">7,5/10</strong> */}
                    </p>

                    <p className="my-4">
                        Wypożyczonych książek: <strong className="ml-2">{this.state.statistic.ownerCount}</strong><br/>
                        Książki w biblioteczce: <strong className="ml-2">{this.state.statistic.specimenCount}</strong><br/>
                        Wypożyczone książki od innych: <strong className="ml-2">{this.state.statistic.loanerCount}</strong>
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