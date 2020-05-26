import React, {Component} from 'react';
import Button from "@material-ui/core/Button";

class SignInComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {isSignedIn: false, userName: "", googleUserId: ""};
        window.handleSignIn = this.handleSignIn.bind(this);
        this.handleSignOut = this.handleSignOut.bind(this);
    }

    async handleSignIn(googleUser) {
        const profile = googleUser.getBasicProfile();
        this.setState({
            isSignedIn: true,
            userName: profile.getName(),
            googleUserId: profile.getId()
        });
        this.setCookie('googleUserId', this.state.googleUserId, 30);
        this.props.handleUserUpdate();
    }

    setCookie(name, value, days) {
        var expires = "";
        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toUTCString();
        }
        document.cookie = name + "=" + (value || "") + expires + "; path=/";
    }

    handleSignOut() {
        const auth2 = window.gapi.auth2.getAuthInstance();
        auth2.signOut().then((function () {
            console.log('User signed out.');
            this.setCookie('googleUserId', '', -1);
            window.location.reload();
        }).bind(this));
    }

    render() {
        return (
            <div>
                {this.state.isSignedIn
                    ? <div>Welcome {this.state.userName} <Button color="inherit" onClick={this.handleSignOut}>Sign Out</Button></div>
                    : <div> <div className="g-signin2" data-onsuccess="handleSignIn"></div></div>
                }
            </div>
        );
    }
}

export default SignInComponent;
