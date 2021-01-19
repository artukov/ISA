import React, { useState, useEffect } from 'react';
import { Col, Container, Navbar, Row, Tab, Tabs } from 'react-bootstrap';
import { usePharmacy } from '../../hooks/usePharmacy';
import AddressComponent from '../address/AddressComponent';
import RatingsComponent from '../ratings/RatingsComponent';
import ConsultationList from './ConsultationList';
import DermatologistList from './DermatologistList';
import DrugList from './DrugList';
import ExaminationList from './ExaminationList';
import PharmacistList from './PharmacistList';

const PharmacyComponent = () => {

    const [pharmacy, setPharmacy] = useState([]);
    const fetchPharmacy = usePharmacy(200);

    useEffect(() => {
        setPharmacy(fetchPharmacy);
        
    }, [fetchPharmacy]);

    return ( 
        <div>
            <Navbar bg = "dark" variant = "dark">
                <Navbar.Brand>{pharmacy.name}</Navbar.Brand>
            </Navbar>
            <Tabs defaultActiveKey="home"   onSelect = {()=> null}>
                <Tab eventKey = "home" title="Pharmacy info">
                    <Container>
                        <Row> 
                            <Col sm={4}>
                                <PharmacistList pharmacyID = {pharmacy.id}></PharmacistList>
                            </Col>
                            <Col sm={4}>
                                <DermatologistList pharmacyID = {pharmacy.id}></DermatologistList>
                            </Col>
                            <Col sm={4}>
                                <DrugList pharmacyID = {pharmacy.id}></DrugList>
                            </Col>
                        </Row>
                    </Container>
                    <RatingsComponent ratings = {pharmacy.ratings} ></RatingsComponent>
                    <p>{pharmacy.description}</p>
                    <AddressComponent address = {pharmacy.address} ></AddressComponent>
                </Tab>
                <Tab eventKey="examination" title="Examinations">
                    <ExaminationList pharmacyID = {pharmacy.id} ></ExaminationList>
                </Tab>
                <Tab eventKey="consultation" title="Conslutations">
                    <ConsultationList pharmacyID = {pharmacy.id}></ConsultationList>
                </Tab>
                <Tab eventKey="drugReservation" title="Reserve drug" disabled = {false}
               >
                </Tab>
                <Tab eventKey="promotion" title="Pharmacy promotions">
                </Tab>
            </Tabs>
            
        </div>
     );
}
 
export default PharmacyComponent;