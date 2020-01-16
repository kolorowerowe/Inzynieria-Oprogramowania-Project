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
            if(data.isActive) {
                this.props.handleLogin(data);
            } else {
                this.props.handleLogin("incorrectdata");
            }
        })
        .catch(_ => {
            console.log("Error while sending");
            this.props.handleLogin("incorrectdata");
        });
    }

    render() {
        return (
            <div className="container h-100">
                 <div className="row h-100">
                    <div className="col d-flex align-items-center justify-content-center flex-column">
                        <h3 className="mb-5">Zaloguj się</h3>
                        <form onSubmit={this.handleSubmit}>
                            <div className="form-group">
                                <input type="email" name="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Login" required></input>
                            </div>
                            <div className="form-group">
                                <input type="password" name="password" className="form-control" id="exampleInputPassword1" placeholder="Hasło" required></input>
                            </div>
                            <button type="submit" className="btn btn-success mt-3">Zaloguj się!</button>
                        </form>
                        {/* <button class="btn btn-link btn-sm">Zapomniałem Hasła</button> */}
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;