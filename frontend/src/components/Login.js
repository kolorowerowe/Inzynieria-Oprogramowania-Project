import React, {Component} from 'react';
import "./Login.css"

class Login extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        console.log(data);

        let post_data = {
            email: data.get("email"),
            password : data.get("password")
        };
        
        console.log(post_data);
        fetch('/api/login', {
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
                 <div class="row h-100">
                    <div class="col d-flex align-items-center justify-content-center flex-column">
                        <h3 class="mb-5">Zaloguj się</h3>
                        <form onSubmit={this.handleSubmit}>
                            <div class="form-group">
                                <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Login"></input>
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Hasło"></input>
                            </div>
                            <button type="submit" class="btn btn-success mt-3">Zaloguj się!</button>
                        </form>
                        <button class="btn btn-link btn-sm">Zapomniałem Hasła</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;