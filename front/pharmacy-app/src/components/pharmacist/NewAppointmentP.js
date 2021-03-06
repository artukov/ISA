import React, { useState, useEffect,useReducer } from 'react'
import { Button, Col, Form, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import absencerequestReducers, { SET_START_DATE, SET_START_TIME } from './AbsenceRequestPReducer';

const NewAppointment = () => {

    const [selectedPatient, setSelectedPatient] = useState({});
    const [patients, setPatients] = useState([]);
    const [pharmacist, setPharmacist] = useState({});
    const [pharmacy, setPharmacy] = useState({});
    const [duration, setDuration] = useState({});
    const [reload, setReload] = useState(false)


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

    useEffect(() => {
        const loadPharmacist = async () => {
            try {
                const result = await axiosConfig.get('/pharmacist/current');
                setPharmacist(result.data);
            }
            catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }

        loadPharmacist();
       
    }, []);

    useEffect(() => {
        const loadPharmacistPatients = async () => {
            try {
                const result = await axiosConfig.get('/pharmacist/patientsDistinct/');
                setPatients(result.data);

            } catch (err) {
                console.log(err);
            }
            
        }
        if (pharmacist.id !== undefined)
            loadPharmacistPatients(pharmacist.id);
            
    }, [pharmacist.id])


    const createAppointment = async () =>{
        
        console.log(state);
        let newAppointment = {
            beggingDateTime : formatDate(state.startDate,state.startTime),
            duration: duration,
            pharmacyID: pharmacist.pharmacyID,
            patient_id: selectedPatient.id,
            drugs: []
        };
        console.table(newAppointment);


        try{
            await axiosConfig.post('pharmacist/appointment/new',newAppointment);
            // dispatch({type : INIT});
            // setLatestPL(state);
            setReload(!reload);
            alert("Appointment created");
        }
        catch(err){
            console.log(err);
            alert(err.response.data);
        }
    }

    return ( 
<div>
            <Form onSubmit={(e) => e.preventDefault()}>
                <Form.Group>
                    <Form.Label>Enter the start date and time</Form.Label>
                <Row>
                    <Col>
                        <Form.Control type = "date"
                                onChange = {(e) => dispatch({ type: SET_START_DATE, startDate: e.target.value })}
                             ></Form.Control>
                        </Col>
                        <Col>
                            <Form.Control type="time" onChange = {(e) => dispatch({type : SET_START_TIME, startTime : e.target.value})}></Form.Control>
                            
                    </Col>
                </Row>
                </Form.Group>
                <Form.Group>
                    <Row>
                        <Col>
                            <div>Set duration</div>
                    <Form.Control type="number" onChange = {(e) => setDuration(parseInt(e.target.value))}>
                        
                            </Form.Control>
                        </Col>
                        <Col><div>Select patient</div>
                            <Form.Control as="select" onClick = {(e) => setSelectedPatient(JSON.parse(e.target.value))}>
                            {
                                patients ? (
                                    patients.map(patient =>
                                        <option key={patient.id} value={JSON.stringify({id : patient.id, name : patient.firstname})}>
                                            {patient.firstname} {patient.lastname}
                                        </option>
                                        )
                                ) : null
                            }
                        </Form.Control>
                        </Col>
                    </Row>
                    
                </Form.Group>
            </Form>
            <Button onClick={() => {
                createAppointment();
            }}>Create new Appointment</Button>
        </div>
     );
}
 
export default NewAppointment;