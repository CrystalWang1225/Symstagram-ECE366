import React, { Component } from 'react';
import React from 'react';
/*
function apiCall(endPoint, method, load, currThis, callback){
    var httpRequest = new XMLHttpRequest();
    httpRequest.open(method, 'http://localhost:4567/' + endPoint);
    httpRequest.withCredentials = true;
    httpRequest.send(JSON.stringify(load));
    httpRequest.onload = function(){
        console.log(httpRequest.getResponseHeader("error"));

        callback(httpRequest.status, httpRequest.response, httpRequest.getResponseHeader("error"), currThis);
    };
}


 */

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            email: "",
            password: "",
            phone: "",
            // nameError: false,
            // emailError: false,
            // passwordError: false,
            //phoneError: false,
            userError: false,
            // error: false,
            isLoad: false
        };
    }


    componentDidMount() {
        fetch("http://localhost:4567/register",{
            method: 'POST',

        })
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        name: result.name,
                        password: result.password,
                        phone: result.phone,

                    });
                },

                (error) =>{
                    this.setState({
                        isLoaded: true,
                        error
                    } );
                }
            )
    }

    render() {
        const{error, isLoaded, name, email, password, phone} = this.state;
        if(error){
            return <div>Error:{error.message}</div>;
        }
        else if (!isLoaded){
            return <div>Error:{error.message}</div>;
        }
        else{
            return (
                <ul>
                    {name.map(user => (
                        <li key={user.name}>
                            {user.name} {user.email} {user.password} {user.phone}
                        </li>
                    ))}
                </ul>
            );
        }
    }

}

    /*
    register(){
        var load = {
            name:this.state.name,
            email:this.state.email,
            phone:this.state.phone,
            password:this.state.password,
        };

        apiCall("register","POST", load, this, function(status, body, error, currThis){

                if (status != 200){
                    currThis.setState({error: true});
                    if (error == "Email format incorrect"){
                        currThis.setState({emailError:true})
                    }
                    else {
                        currThis.setState({emailError:false})
                    }
                    if (error == "Password not acceptable"){
                        currThis.setState({passwordError:true})
                    }
                    else {
                        currThis.setState({passwordError:false})
                    }
                    if (error == "name not acceptable"){
                        currThis.setState({nameError:true})
                    }
                    else {
                        currThis.setState({nameError:false})
                    }
                    if (error == "phone not acceptable"){
                        currThis.setState({phoneError:true})
                    }
                    else {
                        currThis.setState({phoneError:false})
                    }

                }
                else {
                    //currThis.setState({error: false});
                    window.location.href = "login.html";
                }
        }

        );

}

render(){
        return();
}



}


class Signup extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            isSignedUp:false,
            user:""
        };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(key, value){
        this.setState({[key]:value});
    }

    render() {
        return <GuestGreeting onChange={this.handleChange}/>;
    }

    ReactDOM.render(
<Signup />,
    document.getElementById('root');
);



     */





