import React, { useState, useEffect, useContext } from 'react';
import { Button, Form, Navbar,Row,Col } from 'react-bootstrap';
import { SupplyOrderContext } from '../context/SupplyOrderContext';
// import NewSupplyOrder from './NewSupplyOrder';

const SupplyOrderMenu = () => {
    
    const {loadWithoutOffers,statuses,loadWithStatus,openAddForm} = useContext(SupplyOrderContext);

    const [status, setStatus] = useState('');

    

    useEffect(() => {
        setStatus(statuses[0]);
        return () => {
        }
    }, [statuses])
    
    return ( 
    <div>
        <Navbar className="bg-light justify-content-between">
            <Form inline>
                <Row  >
                    <Col><Button variant="primary" onClick={() => openAddForm()}>Create new order</Button></Col>
                    <Col>
                        <Form.Control as="select" onClick = {(e)=>setStatus(e.target.value)}>
                        {
                            statuses.map((status, index) => <option value = {status} key={index}>{status}</option>)
                        }
                        </Form.Control>  
                    </Col>
                    <Col><Button variant="dark" onClick = {() => loadWithStatus(status)}>Load with selected status</Button></Col>
                    <Col><Button variant="info" onClick = {() => loadWithoutOffers()}>Load orders without any offers</Button></Col>
                </Row>
            </Form>
        </Navbar>
    </div> );
}
 
export default SupplyOrderMenu;