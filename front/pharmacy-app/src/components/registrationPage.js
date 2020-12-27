import React, { Component } from 'react';
import { Form, Button, Row, Col } from 'react-bootstrap'
import { Link } from 'react-router-dom'

class registrationPage extends Component {
    constructor(props) {
        super(props);

    }

    componentWillMount() {

    }

    componentDidMount() {

    }

    componentWillReceiveProps(nextProps) {

    }

    shouldComponentUpdate(nextProps, nextState) {

    }

    componentWillUpdate(nextProps, nextState) {

    }

    componentDidUpdate(prevProps, prevState) {

    }

    componentWillUnmount() {

    }

    render() {
        return (
            <div className='login'>
                <Form>
                    <Row>
                        <Col lg='6'>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Firstname</Form.Label>
                                <Form.Control type="text" placeholder="Enter your firstname" />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Lastname</Form.Label>
                                <Form.Control type="text" placeholder="Enter your lastname" />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Adress</Form.Label>
                                <Form.Control type="text" placeholder="Adress" />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>City</Form.Label>
                                <Form.Control type="text" placeholder="City" />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>State</Form.Label>
                                <Form.Control type="text" placeholder="State" />
                            </Form.Group>
                        </Col>
                        <Col lg='6'>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Phone</Form.Label>
                                <Form.Control type="text" placeholder="Phone" />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Email address</Form.Label>
                                <Form.Control type="email" placeholder="Enter email" />
                            </Form.Group>

                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" placeholder="Password" />
                            </Form.Group>

                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" placeholder="Password" />
                            </Form.Group>
                        </Col>
                    </Row>

                    <Button variant="primary" type="submit">
                        Submit
                    </Button>

                    <p className="forgot-password text-right">
                        Already registered <Link to='/login'>log in?</Link>
                    </p>
                </Form>
            </div>
        );
    }
}

export default registrationPage;