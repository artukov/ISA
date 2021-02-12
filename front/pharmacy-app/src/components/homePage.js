import React, { Component } from 'react';
import { Tab, Tabs, Container, Row, Col } from 'react-bootstrap'
import {GoogleMap, withScriptjs, withGoogleMap, Marker} from 'react-google-maps';

import axiosConfig from '../config/AxiosConfig';
import UserProfile from '../components/view/userProfile';
import Drugstore from "../components/view/drugstore";
import Medicaments from '../components/view/medicaments';
import refreshToken from '../services/RefreshToken';
import AllPharmacyList from './pharmacy/AllPharmacyList';
import AllDrugsList from './drug/AllDrugsList';


const Map = (props) =>{
    return (
        <GoogleMap defaultZoom={10} defaultCenter={{ lat: -34.397, lng: 150.644 }}>
             {props.isMarkerShown && <Marker position={{ lat: -34.397, lng: 150.644 }} />}
        </GoogleMap>
    )
}

const WrappedMap = withScriptjs(withGoogleMap(Map))

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
                                {/* <Tab eventKey="user" title="User">
                                    <UserProfile />
                                </Tab> */}
                                <Tab eventKey="drugstores" title="Pharmacies" >
                                    <AllPharmacyList />
                                </Tab>
                                <Tab eventKey="Drugs" title="Drugs" >
                                    <AllDrugsList />
                                </Tab>
                            </Tabs>
                        </Col>

                    </Row>
                </Container>
               {/*  <div style={{width : '100%', height : '50%'}}>
                <WrappedMap  googleMapURL = {`https://maps.googleapis.com/maps/api/js?key=AIzaSyC_ibGOYZycWziIoRCMc3mk0OJVw2wnAjg`}
                    loadingElement={<div style={{ height: `100%` }} />}
                    containerElement={<div style={{ height: `400px` }} />}
                    mapElement={<div style={{ height: `100%` }} />}

                />
                </div> */}
            </div>
        );
    }
}

export default homePage;