import React, { useState, useEffect, useReducer } from 'react'
import { Button, Col, Form, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import absencerequestReducers, { SET_START_DATE, SET_START_TIME } from './AbsenceRequestReducer';

const NewExamination = () => {

    const [selectedPatient, setSelectedPatient] = useState({});
    const [patients, setPatients] = useState([]);
    const [dermatologist, setDermatologist] = useState({});
    const [report, setReport] = useState({});
    const [description, setDescription] = useState({});
    const [allDrugs, setAllDrugs] = useState([]);
    const [selectedAppointment, setSelectedAppointment] = useState({});
    const [showForm, setShowForm] = useState(false);

    const openForm  = () => {
        setShowForm(true);

    }
    
    useEffect(() => {
        const loadDermatologist = async () => {
            try {
                const result = await axiosConfig.get('/dermatologist/current');
                setDermatologist(result.data);
            }
            catch (err) {
                console.log(err);
            }
        }

        loadDermatologist();
       
    }, []);

    useEffect(() => {
        const loadDermatologistPatients = async () => {
            try {
                const result = await axiosConfig.get('/dermatologist/patientsDistinct/');
                setPatients(result.data);

            } catch (err) {
                console.log(err);
            }
            
        }
        if (dermatologist.id !== undefined)
            loadDermatologistPatients(dermatologist.id);
            
    }, [dermatologist.id])

    const loadExamination = async (id) => {
        const [year, month, day] = state.startDate.split("-");
        const [hours, minutes] = state.startTime.split(":");
        let dateTime = new Date(year, month, day, hours, minutes, 0);
        dateTime.setMonth(dateTime.getMonth() - 1);
            try {
                const result = await axiosConfig.get('/dermatologist/findExamination/' + id, {
                    params: { dateTime: dateTime.getTime() }
                })
                setSelectedAppointment(result.data);

            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
            
    }
    
    const addPenalty = async (id) => {


        try{
            await axiosConfig.put('/dermatologist/addPenalty/'+id);
            //dispatch({type : INIT});
            // setLatestPL(state);
            alert("Penalty added");
        }
        catch(err){
            console.log(err);
            alert(err.response.data);
        }
    }

    const [state, dispatch] = useReducer(absencerequestReducers, {
        startDate: '',
        startTime: ''
    });

    const setStartDate = (date) => {
        dispatch({ type: SET_START_DATE, startDate: date });

    }

    const setStartTime = (time) => {
        dispatch({ type: SET_START_TIME, startTime: time });
    }


    return (  
        <div>
            <Form onSubmit={(e) => e.preventDefault()}>
                <Row>
                    <Col>
                        <div>Select patient</div>
                        <Form.Control as = "select" placeholder = "Patient" onClick = {(e) => setSelectedPatient(JSON.parse(e.target.value))}>
                            {
                                patients ? (
                                    patients.map(patient =>
                                        <option key={patient.id} value={JSON.stringify({ id: patient.id, name: patient.firstname })}>
                                            {patient.firstname} {patient.lastname}
                                        </option>
                                    )
                                ) : null}
                        </Form.Control>
                    </Col>
                    <Col>
                        <div>Select date</div>
                    <Form.Control type = "date"  onChange = {(e) => dispatch({ type: SET_START_DATE, startDate: e.target.value })}></Form.Control>
                    </Col>
                    <Col>
                        <div>Select time</div>
                    <Form.Control type = "time" onChange = {(e) => dispatch({type : SET_START_TIME, startTime : e.target.value})}></Form.Control>
                    </Col>

                </Row>
                <Row>
                    <Col>
                        <Button onClick={(e) => {
                            openForm();
                            loadExamination(selectedPatient.id);
                    }}>Start examination</Button>
                    </Col>
                    <Col>
                        <Button onClick={(e) => {
                            addPenalty(selectedPatient.id);
                    }}>Did not show up</Button>
                    </Col>
                </Row>
            </Form>
            { showForm ? 
                <Form onSubmit={(e) => e.preventDefault()}>
                <Row>

                    <Col>
                        <div>Description</div>
                        <Form.Control type="text" placeholder = "Description" onChange = { (e) => setDescription(e.target.value)}></Form.Control>
                    </Col>
                    <Col>
                        <div>Report</div>
                        <Form.Control type="text" placeholder = "Report" onChange = { (e) => setReport(e.target.value) }></Form.Control>
                    </Col>
                    
                    
                </Row>
                <Row>
                    <Col>
                        <Form.Label>Select drugs</Form.Label>
                    <Form.Control as="select">
                        
                        </Form.Control>
                        </Col>
                    <Button>Add drug to the list</Button>
                </Row>
                <Row>
                    <Col>
                    <div>Selected drugs</div>
                    </Col>
                    </Row>
           
                
                </Form> : null
             } 
            
        </div>
    );
}
 
export default NewExamination;