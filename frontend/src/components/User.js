import React, {Component} from 'react';
import "./User.css"
import {Col, Container, Row} from "react-bootstrap";

let loans=[];
let specimens=[];

class User extends Component {
    constructor(props) {
        super(props);
        this.user = props.user;
        this.state = {statistic: {}};
        console.log("ctor");
        this.getSpecimens();
        this.getStatistic();
        this.getLoanedSpecimens();
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

    getLoanedSpecimens() {
        fetch('/api/users/loaned/'+this.user.id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.json())
            .then(data => {
                console.log(data);
                loans=data;
            })
            .catch(_ => {
                console.log("Error while sending");
            });
    }

    getSpecimens() {
        fetch('/api/specimens/all', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.json())
            .then(data => {
                console.log(data);
                specimens=data;
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
                    <Row className="bookRow">
                        {/*<Col className="searchColSpecimenHeader" md={2}>ID</Col>*/}
                        <Col className="searchColSpecimenHeader" md={5}>Nazwa</Col>
                        <Col className="searchColSpecimenHeader" md={3}>DATA ZWROTU</Col>
                        <Col className="searchColSpecimenHeader" md={3}>ZWROC</Col>

                        {/*<Col className="searchColSpecimenHeader" md={1}>WYDAWNICTWO</Col>*/}
                        {/*<Col className="searchColSpecimenHeader" md={1}>CZAS WYPOZYCZENIA</Col>*/}
                        {/*<Col className={"searchColSpecimenHeader"} md={1}>AKCJA</Col>*/}
                    </Row>
                    {loans.map((loan) => <Row className="bookRow">
                        {/*<Col className="searchColSpecimen" md={2}>{specimen.specimen_id}</Col>*/}
                        <Col className="searchColSpecimen" md={5}>{this.selectInJS(loan)}</Col>
                        <Col className="searchColSpecimen" md={3}>{loan.date_return}</Col>
                        <Col className="searchColSpecimen" md={3}>{this.myButton(loan)}</Col>

                        {/*<Col className="searchColSpecimen" md={3}>{loan.publishing_house}</Col>*/}
                        {/*<Col className="searchColSpecimen" md={2}>{loan.loan_period}</Col>*/}
                        {/*<Col className={"searchColSpecimen"}  md={1}><button className={"Button1"} onClick={()=>{console.log(specimen.specimen_id)}}>Pożycz</button>*/}
                        {/*</Col>*/}
                    </Row>)}

                    <div>
                        <a href="login.html" className="btn btn-success mt-5">Moje Recenzje i Opinie</a>
                        <a href="register.html" className="btn btn-border mt-5 ml-3">Recenzje i Opinie o Mnie</a>
                    </div>
                </div>
            </div>
        </div>
        );
    }

    myButton(loan) {
        return <button className={"Button1"} onClick={()=>{this.sendReturnChec(loan)}}>Zwroć</button>
    }

    sendReturnChec(loan) {
        fetch('/api/loans/return/'+loan.loan_id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.json())
            .then(data => {
                console.log(data);
                specimens=data;
            })
            .catch(_ => {
                console.log("Error while sending");
            });
    }

    compare(specimen,loan){
        return (specimen.specimen_id === loan.loan_id);
    }

    selectInJS(loan) {
        specimens.filter((specimen)=>{this.compare(specimen,loan)});
        return specimens[0].title;
    }



}

export default User;