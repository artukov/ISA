import React, { useEffect,useState } from 'react';
import { Col, Form, Row } from 'react-bootstrap';
import { MapContainer } from '../map/MapContainer';

const AddressComponent = ({address,setAddress,dispatch, read}) => {

   
    const [street, setStreet] = useState({});
    const [streetNumber, setStreetNumber] = useState({});
    const [city, setCity] = useState({});
    const [country, setCountry] = useState({});

        return (  
            <div onBlur={() => {
                setAddress({
                street,streetNumber,city,country
            } )}}>
                <Form onSubmit={(e) => e.preventDefault()}>
                    <Row>
                        <Col>
                            <Form.Label>Street</Form.Label>
                            <Form.Control type="text" readOnly={read} onChange={(e) => {
                                setStreet(e.target.value); dispatch(
                                    {
                                        type: "address",
                                        address: {
                                            ...address,
                                            street : e.target.value
                                        }
                                }
                            )}}  placeholder={address ? address.street : ''} defaultValue={address ? address.street : ''}></Form.Control>
                        </Col>
                        <Col>
                            <Form.Label>Street Number</Form.Label>
                            <Form.Control type="number" readOnly={read} onChange={(e) => {
                                setStreet(e.target.value); dispatch(
                                    {
                                        type: "address",
                                        address: {
                                            ...address,
                                            streetNumber: e.target.value
                                        }
                                    }
                                )
                            }} placeholder={address ? address.streetNumber : ''} defaultValue={address ? address.streetNumber : ''}></Form.Control>
                        </Col>
                        <Col>
                            <Form.Label>City</Form.Label>
                            <Form.Control type="text" readOnly={read} onChange={(e) => {
                                setStreet(e.target.value); dispatch(
                                    {
                                        type: "address",
                                        address: {
                                            ...address,
                                            city: e.target.value
                                        }
                                    }
                                )
                            }}placeholder={address ? address.city : ''} defaultValue={address ? address.city : ''}></Form.Control>
                        </Col>
                        <Col>
                        <Form.Label>Country</Form.Label>
                            <Form.Control type="text" readOnly={read} onChange={(e) => {
                                setStreet(e.target.value); dispatch(
                                    {
                                        type: "address",
                                        address: {
                                            ...address,
                                            country: e.target.value
                                        }
                                    }
                                )
                            }}placeholder={address ? address.country : ''} defaultValue={address ? address.country : ''}></Form.Control>
                        </Col>
                    </Row>
               </Form>
            </div>
        );
    //}

    

    
}
 
export default AddressComponent;