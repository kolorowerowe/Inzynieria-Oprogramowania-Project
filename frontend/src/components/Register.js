import React, {Component} from 'react';
import "./Register.css"
import {Col} from "react-bootstrap";

class Register extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);

        let post_data = {
            email: data.get("email"),
            password : data.get("password"),
            name: data.get("name"),
            address: data.get("address")
        };
        
        console.log(post_data);
        fetch('/api/users/put', {
            method: 'POST',
            body: JSON.stringify(post_data),
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.json())
        .then(data => {
            console.log(data);
            window.location.href = "/api/users/"+data["user_id"];
        })
        .catch(function () {
            console.log("Error while sending")
        });
    }

    render() {
        return (
            <div className="container h-100">

            <div className="row h-100">
                <div className="col d-flex align-items-center justify-content-center flex-column">
                    <h3 className="mb-5">Rejstracja</h3>

                    <form onSubmit={this.handleSubmit}>
                        <div className="form-group">
                            <input type="text" name="name" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Nazwa" required></input>
                        </div>
                        <div className="form-group">
                            <input type="email" name="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email" required></input>
                        </div>
                        <div className="form-group">
                            <input type="password" name="password" className="form-control" id="exampleInputPassword1" placeholder="Hasło" required></input>
                        </div>
                        <div className="form-group">
                            <input type="text" name="address" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Adres" required></input>
                        </div>
                        <div className="form-group">
                        <label>
                            {/*Tu byly problemy, pytaj marcina xDDDDD*/}
                            {/*<Col>*/}
                            {/*<input type="checkbox" name={"regulamin"}*/}
                            {/*       id={"ReadyForRentingCheckBox"} required={true}/>*/}
                            {/*<span className={"checkmark"}></span>XI    Czy akceptujesz regulamin</Col>*/}
                            <input type="checkbox" name="regulamin"  value="accepted" required/> Czy akceptujesz regulamin?
                        </label>
                        </div>
                        <button type="submit" className="btn btn-success mt-3">Zarejestruj się!</button>
                    </form>

                    <a href="login.html" className="btn btn-link btn-sm">Masz już konto? Przejdź do strony logowania</a>
                    
                </div>
            </div>
        </div>
        );
    }
}

export default Register;