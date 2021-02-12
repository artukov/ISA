// placeholder={address ? address.street : ''} readOnly={address ? true : false}
import React, { useEffect,useState } from 'react';
import { Col, Form, Row } from 'react-bootstrap';


const PharmacyAddressComponent = ({address}) => {

        return (  
            <div >
                <Form onSubmit={(e) => e.preventDefault()}>
                    <Row>
                        <Col>
                            <Form.Label>Street</Form.Label>
                            <Form.Control type="text" placeholder={address ? address.street : ''} readOnly={address ? true : false}></Form.Control>
                        </Col>
                        <Col>
                            <Form.Label>Street Number</Form.Label>
                            <Form.Control type="number"  placeholder={address ? address.streetNumber : ''} readOnly={address ? true : false}></Form.Control>
                        </Col>
                        <Col>
                            <Form.Label>City</Form.Label>
                            <Form.Control type="text"  placeholder={address ? address.city : ''} readOnly={address ? true : false}></Form.Control>
                        </Col>
                        <Col>
                        <Form.Label>Country</Form.Label>
                            <Form.Control type="text"  placeholder={address ? address.country : ''} readOnly={address ? true : false}></Form.Control>
                        </Col>
                    </Row>
               </Form>
            </div>
        );
    //}

    

    
}
 
export default PharmacyAddressComponent;