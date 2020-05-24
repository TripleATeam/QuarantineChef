import React, {Component} from 'react';

class SignInComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {value: 0};
        window.handleSignIn = this.handleSignIn.bind(this);
    }

     async handleSignIn(event) {
        console.log("user: " + event);
         var profile = event.getBasicProfile();
         console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
         console.log('Name: ' + profile.getName());
         console.log('Image URL: ' + profile.getImageUrl());
         console.log('Email: ' + profile.getEmail());
         this.setCookie('userId', profile.getId(), 1);
     }

     setCookie(name,value,days) {
        var expires = "";
        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days*24*60*60*1000));
            expires = "; expires=" + date.toUTCString();
        }
        document.cookie = name + "=" + (value || "")  + expires + "; path=/";
    }

    render() {
        return (
        <div className="g-signin2" data-onsuccess="handleSignIn"></div>
        );
    }
}
export default SignInComponent;
