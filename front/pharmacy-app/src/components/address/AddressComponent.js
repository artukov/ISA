import React, { useEffect,useState } from 'react';
import { Col, Form, Row } from 'react-bootstrap';
import { MapContainer } from '../map/MapContainer';

const AddressComponent = ({address,setAddress,dispatch}) => {

   
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
                            <Form.Control type="text" onChange={(e) => {
                                setStreet(e.target.value); dispatch(
                                    {
                                        type: "address",
                                        address: {
                                            ...address,
                                            street : e.target.value
                                        }
                                }
                            )}}></Form.Control>
                        </Col>
                        <Col>
                            <Form.Label>Street Number</Form.Label>
                            <Form.Control type="number" onChange={(e) => {
                                setStreet(e.target.value); dispatch(
                                    {
                                        type: "address",
                                        address: {
                                            ...address,
                                            streetNumber: e.target.value
                                        }
                                    }
                                )
                            }}></Form.Control>
                        </Col>
                        <Col>
                            <Form.Label>City</Form.Label>
                            <Form.Control type="text" onChange={(e) => {
                                setStreet(e.target.value); dispatch(
                                    {
                                        type: "address",
                                        address: {
                                            ...address,
                                            city: e.target.value
                                        }
                                    }
                                )
                            }}></Form.Control>
                        </Col>
                        <Col>
                        <Form.Label>Country</Form.Label>
                            <Form.Control type="text" onChange={(e) => {
                                setStreet(e.target.value); dispatch(
                                    {
                                        type: "address",
                                        address: {
                                            ...address,
                                            country: e.target.value
                                        }
                                    }
                                )
                            }}></Form.Control>
                        </Col>
                    </Row>
               </Form>
            </div>
        );
    //}

    

    
}
 
export default AddressComponent;