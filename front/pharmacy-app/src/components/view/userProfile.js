import React, { Component } from 'react';
import { Container, Row, Col, Tab, Tabs, Form, Table } from 'react-bootstrap'

class userProfile extends Component {

    render() {
        return (
            <Container>
                <Row>
                    <Col>
                        <Tabs defaultActiveKey="drugstore"
                            id="controlled-tab-example">

                            <Tab eventKey="profile" title="Profile">
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
                                </Form>
                            </Tab>
                            <Tab eventKey="allergies" title="Allergies" >
                                <Table striped bordered hover variant="dark">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Allergie Name</th>
                                            <th>Drug Name</th>
                                            <th>Item 2</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Name</td>
                                            <td>item item</td>
                                            <td>item item</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Name</td>
                                            <td>item item</td>
                                            <td>item item</td>
                                        </tr>

                                    </tbody>
                                </Table>
                            </Tab>
                            <Tab eventKey="score" title="My score" >
                                <Table striped bordered hover variant="dark">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Points</th>
                                            <th>Category</th>
                                            <th>Loyalty program</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Name</td>
                                            <td>item item</td>
                                            <td>item item</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Name</td>
                                            <td>item item</td>
                                            <td>item item</td>
                                        </tr>

                                    </tbody>
                                </Table>
                            </Tab>
                        </Tabs>
                    </Col>
                </Row>
            </Container>
        );
    }
}

export default userProfile;