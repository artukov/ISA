import React, { Component } from 'react';
import { Button, Form } from 'react-bootstrap';
import { Link } from 'react-router-dom';
/* import axios from 'axios';


import {urlAuthLogin} from '../services/UrlService';
import token from '../services/TokenService';  */

import login from '../services/LoginService';

class loginPage extends Component {
    constructor(props) {
        super(props);
        this.state = {

        }
    }

    loginFunc = async e => {
        e.preventDefault();

        const user = {
            email: this.state.email,
            password: this.state.password

        }
        
        
        try{
            await login(user.email, user.password);
        }
        catch(err){
            alert('error');
        }
     
            
        
    }

    render() {
        return (
            <div className='login'>
                <Form onSubmit={ this.loginFunc}>
                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" name="email" placeholder="Enter email" onChange={(e) => this.setState({ email: e.target.value })} />
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" name="password" placeholder="Password" onChange={(e) => this.setState({ password: e.target.value })} />
                    </Form.Group>

                    <Button variant="primary" type='submit'>
                        Submit
                    </Button>

                    <p className="forgot-password text-right">
                        Do you need to <Link to='/registration'>registare?</Link>
                    </p>
                </Form>
            </div>
        );
    }
}
export default loginPage;