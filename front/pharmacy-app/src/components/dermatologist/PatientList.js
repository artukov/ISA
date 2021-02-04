import React, { useState, useEffect } from 'react';
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const PatientList = () => {
    
    const [patients, setPatients] = useState([]);
    const [examDates, setExamDates] = useState([]);

    

    useEffect(() => {
        const loadPatients = async () => {
            try {
                const result = await axiosConfig.get('/dermatologist/patients');
                setPatients(result.data);
            }
            catch (err) {
                console.log(err);
            }
        }

        const loadExamDate = async (id) => {
            try {
                const result = await axiosConfig.get('/examination/getDate/' + id);
                setExamDates(
                    [
                        ...examDates,
                        result.data
                    ]
                )
            }
            catch (err) {
                console.log(err);
            }
        }

        loadPatients();
       
    }, []);

    return ( 
        <div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col>Name</Col>
                        <Col>Surname</Col>
                        <Col>Email</Col>
                        <Col>Examination Date</Col>
                    </Row>
                </ListGroup.Item>
                {
                    patients ? (
                        patients.map(patient =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={patient.id} >
                                <Row>
                                    <Col>{patient.firstname}</Col>
                                    <Col>{patient.lastname}</Col>
                                    <Col>{patient.email}</Col>
                                    <Col>{ patient.date}</Col>
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
 
export default PatientList;