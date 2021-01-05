import React, { Component } from 'react';
import { Navbar, Nav, Form, Button, NavDropdown, FormControl } from 'react-bootstrap'

import axiosConfig from '../config/AxiosConfig';

class homePage extends Component {
    constructor(props) {
        super(props);
    }

    /* componentWillMount() {

    }

    

    componentWillReceiveProps(nextProps) {

    }

  

    componentWillUpdate(nextProps, nextState) {

    }

    componentDidUpdate(prevProps, prevState) {

    }

    componentWillUnmount() {

    } */

    shouldComponentUpdate(nextProps, nextState) {
        
    }

    componentDidMount() {
      
    }

    getAvg = () =>{
        
        axiosConfig.get('pharmacyadmin/avgPharmacyRating/200')
        .then(res => console.log(res))
        .catch(err => console.log(err));
    }

    render() {
        return (
            <div className='navbar'>
                <Navbar bg="light" expand="lg">
                    <Navbar.Brand href="/home">App name</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="mr-auto">
                            <Nav.Link href="#home">Profil</Nav.Link>
                            <Nav.Link href="#link">Apoteka</Nav.Link>
                            <NavDropdown title="Dropdown" id="basic-nav-dropdown">
                                <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                        <Form inline>
                            <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                            <Button variant="primary">Search</Button>
                        </Form>
                    </Navbar.Collapse>
                </Navbar>
                <Button onClick = {this.getAvg}>Get average pharmacy rating</Button>
            </div>
        );
    }
}

export default homePage;