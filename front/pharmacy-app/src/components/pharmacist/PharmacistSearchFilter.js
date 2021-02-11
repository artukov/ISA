import React, { useState, useEffect } from 'react';
import { Form, Col,Button } from 'react-bootstrap';

const PharmacistSearchFilter = ({searchPharmacist,sortByRatings,sortByHours}) => {

    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');




    return ( 
        <div>
            <Form>
                <Form.Row>
                    <Col>
                        <Form.Control type="text" onChange={(e)=>{setFirstname(e.target.value)}} placeholder="First name"/>
                    </Col>
                    <Col>
                        <Form.Control type="text" onChange={(e)=>{setLastname(e.target.value)}} placeholder="Last name"/>
                    </Col>
                    <Col>
                        <Button onClick={()=>{searchPharmacist(firstname,lastname)}}>Search</Button>
                    </Col>
                    <Col>
                        <Button variant="secondary" onClick={()=>{sortByRatings()}}>Sort by ratings</Button>
                    </Col>
                    <Col>
                        <Button variant="info" onClick={()=>{sortByHours()}}>Sort by hours</Button>
                    </Col>
                </Form.Row>
            </Form>
        </div>
     );
}
 
export default PharmacistSearchFilter;