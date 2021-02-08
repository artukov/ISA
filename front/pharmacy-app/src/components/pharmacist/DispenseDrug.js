import React, { useState, useEffect } from 'react'
import { Button, Col, Form, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const DispenseDrug = () => {

    const [reservationId, setReservationId] = useState({});

    const dispenseDrugs = async (id) => {


        try{
            await axiosConfig.put('/pharmacist/dispense/'+id);
            //dispatch({type : INIT});
            // setLatestPL(state);
            alert("Drugs dispensed");
        }
        catch(err){
            console.log(err);
            alert(err.response.data);
        }
    }

    return (  
        <div>
            <Form onSubmit={(e) => e.preventDefault()}>
                <Row>
                    <Col>
                     <div>Enter Reservation ID</div>
                    <Form.Control type="number" onChange = {(e) => setReservationId(parseInt(e.target.value))}></Form.Control>
                    </Col>
                
                    
                    
                </Row>
                <Button onClick={(e) => {
                    dispenseDrugs(reservationId);
                }}>Dispense</Button>
                
            </Form>

        </div>
    );
}
 
export default DispenseDrug;