import React, {Component} from 'react';
import "./User.css"

class User extends Component {
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
            <div class="container h-100">

            <div class="row p-5">
                <div class="col-12 col-lg-4">
                    <img src="img/profile.jpg" alt="User" class="img-fluid"/>
                </div>
                <div class="col-12 col-lg-8 d-flex justify-content-center flex-column">
                    <h3>edek_z_fabryki_kredek <small class="text-black-50">edzio@gmail.com</small></h3>

                    <p class="mt-4">
                        Napisanych recenzji [książek]: <strong class="ml-2">25</strong><br/>
                        Napisanych opini [o userach]: <strong class="ml-2">10</strong><br/>
                        Twoja Ocena: <strong class="ml-2">7,5/10</strong>
                    </p>

                    <p class="my-4">
                        Wypożyczonych książek: <strong class="ml-2">100</strong><br/>
                        Książki w biblioteczce: <strong class="ml-2">41</strong><br/>
                        Wypożyczone książki od innych: <strong class="ml-2">2</strong>
                    </p>

                    <div>
                        <a href="login.html" class="btn btn-success mt-5">Moje Recenzje i Opinie</a>
                        <a href="register.html" class="btn btn-border mt-5 ml-3">Recenzje i Opinie o Mnie</a>
                    </div>
                </div>
            </div>

        </div>
        );
    }
}

export default User;