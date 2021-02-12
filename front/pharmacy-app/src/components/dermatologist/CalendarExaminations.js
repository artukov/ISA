import React, { useState, useEffect } from 'react';
import { Button, ButtonGroup, Col, Form, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const CalendarExaminations = () => {

    const [choosenMonth, setChoosenMonth] = useState({});
    const [examinations, setExaminations] = useState([]);
    const [choosenYear, setChoosenYear] = useState({});

    const loadExaminations = async () => {
        try {
            // const result = await axiosConfig.get('/dermatologist/patients');
            const result = await axiosConfig.get('/dermatologist/allExaminations/'+choosenMonth+'/'+choosenYear);
            setExaminations(result.data);
        }
        catch (err) {
            console.log(err);
            alert(err.response.data);
        }
    }

    return (  
        <div>
            <Form onSubmit={(e) => e.preventDefault()}>
                <div>Choose month</div>
                <Form.Control as="select" onClick={(e) => setChoosenMonth(e.target.value)}>
                    <option value={1}>JANUARY</option>
                    <option value={2}>FEBRUARY</option>
                    <option value={3}>MARCH</option>
                    <option value={4}>APRIL</option>
                    <option value={5}>MAY</option>
                    <option value={6}>JUNE</option>
                    <option value={7}>JULY</option>
                    <option value={8}>AUGUST</option>
                    <option value={9}>SEPTEMBER</option>
                    <option value = {10}>OCTOBER</option>
                    <option value = {11}>NOVEMBER</option>
                    <option value = {12}>DECEMBER</option>
                    
                </Form.Control>
                <div>Enter year</div>
                <Form.Control type="text" onChange={(e) => setChoosenYear(e.target.value)}></Form.Control>
                <Button onClick={(e) => {
                    loadExaminations();
                }}>Find</Button>
            </Form>
             <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col>Examination Date</Col>
                        <Col>Patient Name</Col>
                        <Col>Patient Surname</Col>
                        <Col>Patient Email</Col>
                        
                    </Row>
                </ListGroup.Item>
                {
                    examinations ? (
                        examinations.map((examination,index)=>

                            <ListGroup.Item key={index} >
                                <Row>
                                    <Col>{examination.appointmentDate}</Col>
                                    <Col>{examination.firstName}</Col>
                                    <Col>{examination.lastName}</Col>
                                    <Col>{examination.email}</Col>
                                    
                            </Row>
                            </ListGroup.Item>
                            // <ListGroup.Item key={patient.name}>{patient.name}</ListGroup>
                        )
                        
                    ) : null
                }
            </ListGroup>
        </div>
    );
}
 
export default CalendarExaminations;