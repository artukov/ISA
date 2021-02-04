import React, { useState, useEffect } from 'react'
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
const PatientListP = () => {
    const [patients, setPatients] = useState([]);

    useEffect(() => {
        const loadPatients = async () => {
            try {
                const result = await axiosConfig.get('/pharmacist/patients');
                setPatients(result.data);
            }
            catch (err) {
                console.log(err);
            }
        }

        loadPatients();
    }, [])
    return ( 
         <div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col>Name</Col>
                        <Col>Surname</Col>
                        <Col>Email</Col>
                        <Col>Consultation Date</Col>
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
 
export default PatientListP;