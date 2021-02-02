import React, { Component } from 'react';
import { Button, Tab, Tabs, Container, Row, Col } from 'react-bootstrap'

import axiosConfig from '../config/AxiosConfig';
import UserProfile from '../components/view/userProfile';
import Drugstore from "../components/view/drugstore";
import Medicaments from '../components/view/medicaments';
import refreshToken from '../services/RefreshToken';

class homePage extends Component {

    getCurrent = () => {

        axiosConfig.get('/user/current')
            .then(res => console.log(res.data))
            .catch(err => console.log(err.response.data));
        refreshToken();
    }

    render() {
        return (
            <div>
                <Container size='xl'>
                    <Row>
                        <Col sm={12}>
                            <Tabs defaultActiveKey="drugstores"
                                id="controlled-tab-example">

                                <Tab eventKey="user" title="User">
                                    <UserProfile />
                                </Tab>
                                <Tab eventKey="drugstores" title="Drugstores" >
                                    <Drugstore />
                                </Tab>
                                <Tab eventKey="medicaments" title="Medicaments" >
                                    <Medicaments />
                                </Tab>
                            </Tabs>
                        </Col>

                    </Row>
                </Container>

                <Button onClick={() => this.getCurrent()}>Get current user</Button>
                <Button onClick={() => window.location = '/pharmacy'}>Pharmacy</Button>
                <Button onClick={() => window.location = '/pharmacyAdmin'}>PharmacyAdmin</Button>
                <Button onClick={() => window.location='/supplier'}>Supplier</Button>
            </div>
        );
    }
}

export default homePage;