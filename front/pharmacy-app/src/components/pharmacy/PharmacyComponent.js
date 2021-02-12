import React, { useState, useEffect } from 'react';
import { Col, Container, Navbar, Row, Tab, Tabs } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlGetPharmacy } from '../../services/UrlService';
import AddressComponent from '../address/AddressComponent';
import DermatologistContextProvider, { DermatologistContext } from '../dermatologist/DermatologistContext';
import RatingsComponent from '../ratings/RatingsComponent';
import ConsultationList from './ConsultationList';
import DermatologistList from './DermatologistList';
import DrugList from './DrugList';
import ExaminationList from './ExaminationList';
import PharmacyDermatologistList from './list/DermatologistList';
import PharmacyDrugList from './list/DrugList';
import PharmacyPharmacistList from './list/PharmacistList';
import PharmacistList from './PharmacistList';
import PharmacyAddressComponents from './address/PharmacyAddress'

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
                alert(e.response);
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
                                {/* <PharmacistList pharmacyID = {pharmacy.id}></PharmacistList> */}
                                <PharmacyPharmacistList pharmacyID={pharmacy.id}></PharmacyPharmacistList>
                            </Col>
                            <Col sm={4}>
                               {/* <DermatologistContextProvider>
                                    <DermatologistList pharmacyID = {pharmacy.id}></DermatologistList>
                               </DermatologistContextProvider> */}
                               <PharmacyDermatologistList pharmacyID = {pharmacy.id}></PharmacyDermatologistList>
                            </Col>
                            <Col sm={4}>
                               {/*  <DrugList pharmacyID = {pharmacy.id}></DrugList> */}
                               <PharmacyDrugList pharmacyID={pharmacy.id}></PharmacyDrugList>
                            </Col>
                        </Row>
                    </Container>
                        <RatingsComponent ratings = {pharmacy.ratings} ></RatingsComponent>
                        <p>Description  : {pharmacy.description}</p>
                        <PharmacyAddressComponents address={pharmacy.address}></PharmacyAddressComponents>
                </Tab>
                <Tab eventKey="examination" title="Examinations">
                    <ExaminationList pharmacyID = {pharmacy.id} ></ExaminationList>
                </Tab>
            </Tabs>
            </div>) 
            : (<p>Loading pharmacy info...</p>)
            }
            
        </div>
     );
}
 
export default PharmacyComponent;