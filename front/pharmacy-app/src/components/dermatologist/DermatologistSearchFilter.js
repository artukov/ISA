import React, { useState, useEffect, useContext } from 'react';
import { Form, Navbar, Row, Col, Button } from 'react-bootstrap';
import { DermatologistContext } from './DermatologistContext';

const DermatologistSearchFilter = () => {

    const {searchList,sortByRatings,sortByHours} = useContext(DermatologistContext);
        
    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');

    return ( 
        <div>
            <Navbar >
                <Form >
                    <Row>
                        <Col>
                            <Form.Control placeholder="First name" onChange={(e)=>setFirstname(e.target.value)} />
                        </Col>
                        <Col>
                            <Form.Control placeholder="Last name"  onChange={(e)=>setLastname(e.target.value)}/>
                        </Col>
                        <Col>
                            <Button onClick={()=>{searchList(firstname,lastname)}}>Search</Button>
                        </Col>
                        <Col>
                            <Button onClick={()=>{sortByRatings()}} variant="secondary">Sort by ratings</Button>
                        </Col>
                        <Col>
                            <Button onClick={()=>{sortByHours()}} variant="info">Sort by hours</Button>
                        </Col>
                    </Row>
                    <Row>
                       
                    </Row>
                </Form>
            </Navbar>

        </div>
     );
}
 
export default DermatologistSearchFilter;