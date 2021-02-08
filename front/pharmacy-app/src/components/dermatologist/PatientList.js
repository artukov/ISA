import React, { useState, useEffect } from 'react';
import { Button, Col, Form, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const PatientList = () => {
    
    const [patients, setPatients] = useState([]);
    const [examDates, setExamDates] = useState([]);
    const [sortNameAsc, setSortNameAsc] = useState(true);
    const [sortSurnameAsc, setSortSurnameAsc] = useState(true);
    const [sortEmailAsc, setSortEmailAsc] = useState(true);
    const [foundPatients, setFoundPatients] = useState([]);
    const [clientName, setClientName] = useState({});
    const [clientLastname, setClientLastname] = useState({});


    useEffect(() => {
        const loadPatients = async () => {
            try {
                const result = await axiosConfig.get('/dermatologist/patients');
                setPatients(result.data);
            }
            catch (err) {
                console.log(err);
                alert(err.response.data);
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
                alert(err.response.data);
            }
        }
        loadPatients();
       
       
    }, []);

    const findPatients = (firstName, lastName) => {
        // console.log(firstName.toUpperCase(), lastName);
        const foundPatient = patients.find(patient =>
            patient.lastname.toUpperCase() === lastName.toUpperCase()
            && patient.firstname.toUpperCase() === firstName.toUpperCase()
        );
        setFoundPatients([foundPatient]);
        // console.log('patient',foundPatient);
        // try {
        //     const result = await axiosConfig.get('/pharmacist/getAllPatients/'+firstName+'/'+lastName);
        //     setFoundPatients(result.data);
        // } catch (err) {
        //     console.log(err);
        //     alert(err.response.data);
        // }
    }

    const sortPatientsName = () => {
        let result = null
        if (sortNameAsc) {
            result = patients.sort((a, b) => (a.firstname > b.firstname) ? 1 : -1);
            setSortNameAsc(false);
        }
        else { 
            result = patients.sort((a, b) => (a.firstname < b.firstname) ? 1 : -1);
            setSortNameAsc(true);
    }
        setPatients([...result]);
    }

    const sortPatientsSurname = () => {
        let result = null
        if (sortSurnameAsc) {
            result = patients.sort((a, b) => (a.lastname > b.lastname) ? 1 : -1);
            setSortSurnameAsc(false);
        }
        else { 
            result = patients.sort((a, b) => (a.lastname < b.lastname) ? 1 : -1);
            setSortSurnameAsc(true);
    }
        setPatients([...result]);
    }

    const sortPatientsEmail = () => {
        let result = null
        if (sortEmailAsc) {
            result = patients.sort((a, b) => (a.email > b.email) ? 1 : -1);
            setSortEmailAsc(false);
        }
        else { 
            result = patients.sort((a, b) => (a.email < b.email) ? 1 : -1);
            setSortEmailAsc(true);
    }
        setPatients([...result]);
    }

    
    return ( 
        <div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col onClick={() => {
                            sortPatientsName();
                            } }>Name</Col>
                        <Col onClick={() => {
                            sortPatientsSurname();
                            } }>Surname</Col>
                        <Col onClick={() => {
                            sortPatientsEmail();
                            } }>Email</Col>
                        <Col>Examination Date</Col>
                    </Row>
                </ListGroup.Item>
                {
                    patients ? (
                        patients.map((patient,index)=>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
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
            <Form onSubmit={(e) => e.preventDefault()}>
                <Row>
                <Col>
                        <div>Client Name</div>
                        <Form.Control type="text" placeholder = "Client Name" onChange = { (e) => setClientName(e.target.value) }></Form.Control>
                    </Col>
                    <Col>
                        <div>Client Lastname</div>
                        <Form.Control type="text" placeholder = "Client Lastname" onChange = { (e) => setClientLastname(e.target.value) }></Form.Control>
                    </Col>
                    <Col>
                    
                    </Col>
                </Row>
                <Button onClick={(e) => {
                    if (clientName !== '' && clientLastname !== '') {

                        findPatients(clientName, clientLastname);
                    } else {
                        alert("Enter name and lastname");
                    }
                }
                }>Find Client</Button>
            </Form>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col>Name</Col>
                        <Col >Surname</Col>
                        <Col >Email</Col>
                    </Row>
                </ListGroup.Item>
            
                {
                    foundPatients ? (
                        foundPatients.map((patient,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{patient.firstname}</Col>
                                    <Col>{patient.lastname}</Col>
                                    <Col>{patient.email}</Col>
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