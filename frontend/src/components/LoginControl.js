import React, {Component} from 'react';
// import "./User.css"
import Login from './Login'
import User from './User'
import * as Cookie from '../Functions/CookiesHandler'

class LoginControl extends Component {
    constructor(props) {
      super(props);
      this.handleLogin = this.handleLogin.bind(this);
      this.handleLogoutClick = this.handleLogoutClick.bind(this);
      this.handleCookies = this.handleCookies.bind(this);
      this.state = {isLoggedIn: false, user: {}, error: null};
    }

    handleLogin(data) {
      if(data === "incorrectdata") {
        this.setState({isLoggedIn: false, user: {}, error: "Błędne dane"});
      } else {
        this.setState({isLoggedIn: true, user: data, error: null});
      }
      this.handleCookies();
    }
  
    handleLogoutClick() {
      this.setState({isLoggedIn: false, user: {}, error: null});
      this.handleCookies();
    }
  
    handleCookies() {
      Cookie.setCookie("userId", this.state.user.user_id, 1);
      Cookie.setCookie("isLoggedIn", this.state.isLoggedIn, 1);
    }

    render() {
      const isLoggedIn = this.state.isLoggedIn;
      let button;
  
      if (isLoggedIn) {
        button = <LogoutButton onClick={this.handleLogoutClick} />;
      }
  
      return (
        <div>
          <Panel isLoggedIn={isLoggedIn} handleLogin={this.handleLogin} user={this.state.user} />
          <small name="error">{this.state.error}</small>
          {button}
        </div>
      );
    }
  }

  function Panel(props) {
    const isLoggedIn = props.isLoggedIn;
    if (isLoggedIn) {
      return <User user={props.user} />;
    }
    return <Login handleLogin={props.handleLogin}/>;
  }

  function LogoutButton(props) {
    return (
      <button onClick={props.onClick}>
        Logout
      </button>
    );
  }
export default LoginControl;