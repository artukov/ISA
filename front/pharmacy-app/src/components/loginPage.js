import React, { Component } from 'react';
import { Button, Form, Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import axios from 'axios';

class loginPage extends Component {
    constructor(props) {
        super(props);
        this.state = {

        }
    }

    login = e => {
        e.preventDefault();

        const user = {
            email: this.state.email,
            password: this.state.password

        }

        axios.post(`http://localhost:8080/auth/login`, user)
            .then(res => {
                if (res.status == 200) {
                    window.location = "/home"
                }
            })

    }

    render() {
        return (
            <div className='login'>
                <Form onSubmit={this.login}>
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