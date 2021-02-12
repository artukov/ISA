import React, { useState, useEffect } from 'react';
import { Col, Container, Navbar, Row, Tab, Tabs } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlGetPharmacy } from '../../services/UrlService';
import AddressComponent from '../address/AddressComponent';
import RatingsComponent from '../ratings/RatingsComponent';
import ConsultationList from './ConsultationList';
import DermatologistList from './DermatologistList';
import DrugList from './DrugList';
import ExaminationList from './ExaminationList';
import PharmacistList from './PharmacistList';

const PharmacyComponent = (props) => {

    const [pharmacy, setPharmacy] = useState({});
    const [isLoaded, setIsLoaded] = useState(false);
    // const fetchPharmacy = usePharmacy(200);

    useEffect(() => {
        console.log(props);
        const search = props.match.params; // returns the URL query String
        const params = new URLSearchParams(search); 
        const IdFromURL = params.get('id'); 

        
        async function loadPharmacy(id){
            try{
                const resault = await axiosConfig.get(urlGetPharmacy + id);
                setPharmacy(resault.data);
                setIsLoaded(true);
            }
            catch(e){
                alert(e.response.data.message);
            }
        }
        if(props.pharmacyID !== undefined){
            
            
        }

        loadPharmacy(IdFromURL);

        // pharmacyID = 200;
        
        
    }, []);


    return ( 
        <div>
            {
                (isLoaded) ? 
            (<div>
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
            </div>) 
            : (<p>Loading pharmacy info...</p>)
            }
            
        </div>
     );
}
 
export default PharmacyComponent;