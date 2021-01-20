import React, { useState, useEffect } from 'react';
import { Col, Form,  Button} from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import AddressComponent from '../address/AddressComponent';

const PharmacyInfoComponent = ({pharmacy}) => {

    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
   

    const [country, setCountry] = useState('')
    const [city, setCity] = useState('');
    const [street, setStreet] = useState('')
    const [streetNumber, setStreetNumber] = useState('')

    const [address, setAddress] = useState({
        country,
        city,
        street,
        streetNumber
    });

    


    const [showChange, setShowChange] = useState(true);



    useEffect(() => {
        setName(pharmacy.name);
        setDescription(pharmacy.description);
        setAddress(pharmacy.address);
        if(pharmacy.address !== undefined){
            setCountry(pharmacy.address.country);
            setCity(pharmacy.address.city);
            setStreet(pharmacy.address.street);
            setStreetNumber(pharmacy.address.streetNumber);
        }
        return () => {
        }
    }, [pharmacy]);


    const savePharmacyChanges = async (e) =>{
        e.preventDefault();

        // console.log(country, city, street, streetNumber);

        let modifiedPharmacy = {
            name,
            description,
            ratings : pharmacy.ratings,
            calendarID : pharmacy.calendarID,
            address : {
                id : address.id,
                country,
                city,
                street,
                streetNumber
            }
        }

        try{
            const resault = await axiosConfig.put('/pharmacy/modify/'+pharmacy.id, modifiedPharmacy);
            const retPharmacy = resault.data;
            pharmacy = retPharmacy;
            /**
             * TODO:
             * Update the page with new data 
            */
        }
        catch(err){
            alert(err.response.data.message);
        }
        setShowChange(true);

   }
    
    return ( 
        <div>
            { (pharmacy && address) ?  (
                <div>
                    <Form>
                        <Form.Row className="align-items-center">
                            <Col sm="auto">
                                <Form.Label>Pharmacy name : {pharmacy.name}</Form.Label>
                            </Col>
                            <Col sm= "auto">
                                <Form.Control type="text" placeholder={name} hidden = {showChange}
                                    onChange = {(e) => setName(e.target.value)}
                                ></Form.Control>
                            </Col>
                        </Form.Row>
                        <Form.Row className="align-items-center">
                            <Col sm="auto">
                                <Form.Label>Pharmacy description : {pharmacy.description}</Form.Label>
                            </Col>
                            <Col sm= "auto">
                                <Form.Control type="text" placeholder={description} 
                                hidden = {showChange}
                                onChange = {(e) => setDescription(e.target.value)}
                                ></Form.Control>
                            </Col>
                        </Form.Row>
                        
                        <Form.Row >
                            <AddressComponent address = {pharmacy.address}></AddressComponent>
                        </Form.Row>

                        <Form.Row className="align-items-center" hidden={showChange}>
                                <Col sm="auto">
                                <Form.Label>Address country : {address.country}</Form.Label>
                            </Col>
                            <Col sm= "auto">
                                <Form.Control type="text" placeholder={country} 
                                
                                onChange = {(e) => setCountry(e.target.value)}
                                ></Form.Control>
                            </Col>
                            <Col sm="auto">
                                <Form.Label>Address city : {address.city}</Form.Label>
                            </Col>
                            <Col sm= "auto">
                                <Form.Control type="text" placeholder={city} 
                                
                                onChange = {(e) => setCity(e.target.value)}
                                ></Form.Control>
                            </Col>
                            <Col sm="auto">
                                <Form.Label>Address street : {address.street}</Form.Label>
                            </Col>
                            <Col sm= "auto">
                                <Form.Control type="text" placeholder={street} 
                                
                                onChange = {(e) => setStreet(e.target.value)}
                                ></Form.Control>
                            </Col>
                            <Col sm="auto">
                                <Form.Label>Address streetNumber : {address.streetNumber}</Form.Label>
                            </Col>
                            <Col sm= "auto">
                                <Form.Control type="number" placeholder={streetNumber} 
                                
                                onChange = {(e) => setStreetNumber(e.target.value)}
                                ></Form.Control>
                            </Col>
                        </Form.Row>


                        <Form.Group>
                        <Form.Check 
                                    type="checkbox"
                                    label = "Change pharmacy info"
                                    onClick = {() => setShowChange(false)}
                                ></Form.Check>
                        </Form.Group>
                        <Button variant = 'primary' onClick = {(e) => savePharmacyChanges(e)}>Save changes</Button>
                    </Form>
                </div>
            ) 
            : (<p>Loading pharmacy info...</p>) }
        </div>
     );
}
 
export default PharmacyInfoComponent;

