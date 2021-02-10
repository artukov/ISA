import React, { useState, useEffect, useReducer } from 'react';
import { Col, Form, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import absencerequestReducers, { SET_START_DATE, SET_START_TIME } from '../pharmacist/AbsenceRequestPReducer';

const BookConsultation = () => {

    const [pharmacies, setPharmacies] = useState([]);
    const [sortNameAsc, setSortNameAsc] = useState(true);
    const [sortRatingAsc, setSortRatingAsc] = useState(true);
    const [sortCityAsc, setSortCityAsc] = useState(true);

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

    const loadPharmacies = async () => {
            try {
                const result = await axiosConfig.get('/pharmacy/findAll');
                setPharmacies(result.data);
            } catch (err) {
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
                            } }>City</Col>
                        
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