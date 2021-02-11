import React, { useState, useEffect, useReducer } from 'react';
import { Button, Col, Form, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import absencerequestReducers, { SET_START_DATE, SET_START_TIME } from '../pharmacist/AbsenceRequestPReducer';

const BookConsultation = () => {

    const [pharmacies, setPharmacies] = useState([]);
    const [pharmacists, setPharmacists] = useState([]);
    const [sortNameAsc, setSortNameAsc] = useState(true);
    const [sortRatingAsc, setSortRatingAsc] = useState(true);
    const [sortCityAsc, setSortCityAsc] = useState(true);
    const [choosenPharmacy, setChoosenPharmacy] = useState({});
    const [choosenPharmacist, setChoosenPharmacist] = useState({});
    const [patient, setPatient] = useState({});

     const [state, dispatch] = useReducer(absencerequestReducers, {
        startDate: '',
        startTime: ''
     });
    
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

    const setStartDate = (date) => {
        dispatch({ type: SET_START_DATE, startDate: date });

    }

    const setStartTime = (time) => {
        dispatch({ type: SET_START_TIME, startTime: time });
    }

    const sortPharmacyName = () => {
        let result = null
        if (sortNameAsc) {
            result = pharmacies.sort((a, b) => (a.name > b.name) ? 1 : -1);
            setSortNameAsc(false);
        }
        else { 
            result = pharmacies.sort((a, b) => (a.name < b.name) ? 1 : -1);
            setSortNameAsc(true);
    }
        setPharmacies([...result]);
    }

    const sortPharmacyRating = () => {
        let result = null
        if (sortRatingAsc) {
            result = pharmacies.sort((a, b) => (a.ratings > b.ratings) ? 1 : -1);
            setSortRatingAsc(false);
        }
        else { 
            result = pharmacies.sort((a, b) => (a.ratings < b.ratings) ? 1 : -1);
            setSortRatingAsc(true);
    }
        setPharmacies([...result]);
    }

    const sortPharmacyCity = () => {
        let result = null
        if (sortCityAsc) {
            result = pharmacies.sort((a, b) => (a.address.city > b.address.city) ? 1 : -1);
            setSortCityAsc(false);
        }
        else { 
            result = pharmacies.sort((a, b) => (a.address.city < b.address.city) ? 1 : -1);
            setSortCityAsc(true);
    }
        setPharmacies([...result]);
    }

    const loadPharmacies = async (date,time) => {
        try {
                //const dateTime = formatDate(date,time)
                const result = await axiosConfig.get('/patient/getFreeConsultationPharmacies', {
                    params: { dateTime: formatDate(date, time) }
                });
                setPharmacies(result.data);
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
    }
    const loadPharmacists = async (id) => {
        try {
            const result = await axiosConfig.get('/pharmacist/findAllByPharmacy/'+id);
            setPharmacists(result.data);
            
        } catch (err) {
            console.log(err);
                alert(err.response.data);
        }
    }

    const newConsultation = async (pharmacistId) => {
        
        let newAppointment = {
            beggingDateTime : formatDate(state.startDate,state.startTime),
            pharmacyID: choosenPharmacy,
            patient_id: patient.id,
            pharmacistID: pharmacistId,
            drugs: []
        };

        try{
            await axiosConfig.post('pharmacist/appointment/new',newAppointment);
            // dispatch({type : INIT});
            // setLatestPL(state);
            //setReload(!reload);
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
                    <Row>
                        <Button onClick={() => {
                            loadPharmacies(state.startDate, state.startTime);
                            } }>Find pharmacies</Button>
                    </Row>
                </Form.Group>

            </Form>
            <ListGroup>
                Pharmacies
                <ListGroup.Item>
                    <Row>
                        <Col onClick={() => {
                            sortPharmacyName();
                            } }>Name</Col>
                        <Col onClick={() => {
                            sortPharmacyRating();
                            } }>Rating</Col>
                        <Col onClick={() => {
                            sortPharmacyCity();
                        }}>City</Col>
                        <Col></Col>
                        
                    </Row>
                </ListGroup.Item>
                {
                    pharmacies ? (
                        pharmacies.map((pharmacy,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{pharmacy.name}</Col>
                                    <Col>{pharmacy.ratings}</Col>
                                    <Col>{pharmacy.address.city}</Col>
                                    <Col><Button onClick={(e) => {
                                        loadPharmacists(pharmacy.id);
                                        setChoosenPharmacy(pharmacy.id);
                                    }}>Choose</Button></Col>
                                    
                            </Row>
                            </ListGroup.Item>
                            // <ListGroup.Item key={patient.name}>{patient.name}</ListGroup>
                        )
                        
                    ) : null
                }
            </ListGroup>
            
            <ListGroup>
                Pharmacists
                <ListGroup.Item>
                    <Row>
                        <Col>Name</Col>
                        <Col>Lastname</Col>
                        <Col>Rating</Col>
                        <Col></Col>
                        
                    </Row>
                </ListGroup.Item>
                {
                    pharmacists ? (
                        pharmacists.map((pharmacist,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{pharmacist.firstname}</Col>
                                    <Col>{pharmacist.lastname}</Col>
                                    <Col>{pharmacist.ratings}</Col>
                                    <Col><Button onClick={(e) => {
                                        newConsultation(pharmacist.id);
                                    }}>Book Consultation</Button></Col>
                                    
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
 
export default BookConsultation;