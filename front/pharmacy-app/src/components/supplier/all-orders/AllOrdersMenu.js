import React, { useState, useEffect, useContext } from 'react';
import { Navbar, Form, Col , Button} from 'react-bootstrap';
import { AllOrdersContext } from './AllOrdersContext';

const AllOrdersMenu = () => {

    const {statuses, filterOrders} = useContext(AllOrdersContext);

    const [status, setStatus] = useState('no-status');

    return (  
        <Navbar>
            <Form>
                <Form.Row>
                    <Col>
                        <Form.Control as="select"  onClick={(e)=>setStatus(e.target.value)}>
                            <option value={'no-status'}>No status</option>
                            {
                                statuses ? (
                                    statuses.map(status =>
                                            <option key={status} value={status}>{status}</option>
                                        )
                                ) : null
                            }
                        </Form.Control>
                    </Col>
                    <Col>
                        <Button variant="info" onClick = {() => filterOrders(status) }> Filter orders with the choosen status</Button>
                    </Col>
                </Form.Row>
            </Form>
        </Navbar>
    );
}
 
export default AllOrdersMenu;