import React, { useState, useEffect } from 'react';
import { Card } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const PatientInfo = () => {

    const [patient, setPatient] = useState({});

    useEffect(() => {
        const loadPatient = async () => {
            try {
                const result = await axiosConfig.get('patient/current');
                setPatient(result.data);
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }
        loadPatient();
    }, [])

    return (  
        <div>
 {
                patient ? (
                <Card>
                    <Card.Header>
                        <Card.Title>Patient details</Card.Title>
                    </Card.Header>
                    <Card.Body>
                            <Card.Text>Firstname : {patient.firstname}</Card.Text>
                        <Card.Text>Lastname : {patient.lastname}</Card.Text>
                        <Card.Text>Email : {patient.email}</Card.Text>
                            <Card.Text>Phone number : {patient.phoneNumber}</Card.Text>
                            <Card.Text>Points : {patient.points}</Card.Text>
                            <Card.Text>Category : {patient.category}</Card.Text>
                    </Card.Body>
                    <Card.Footer>
                    </Card.Footer>
                </Card>

                ) : null
            }
        </div>
    );
}
 
export default PatientInfo;