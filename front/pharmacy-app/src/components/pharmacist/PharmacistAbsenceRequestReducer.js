import React, { useState, useEffect, useReducer } from 'react'
import { Button, Col, Form, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import absencerequestReducers, { INIT, SET_END_DATE, SET_END_TIME, SET_START_DATE, SET_START_TIME } from './AbsenceRequestPReducer';

const PharmacistAbsenceRequest = () => {
   
    const [pharmacist, setPharmacist] = useState({});
    const [reload, setReload] = useState(false);
    
    const [state, dispatch] = useReducer(absencerequestReducers, {
        startDate: '',
        endDate: '',
        startTime: '',
        endTime : ''
    });

    const setStartDate = (date) => {
        dispatch({ type: SET_START_DATE, startDate: date });

    }

    const setEndDate = (date) => {
        dispatch({ type: SET_END_DATE, endDate: date });
    }
    const setStartTime = (time) => {
        dispatch({ type: SET_START_TIME, startTime: time });
    }
    const setEndTime = (time) => {
        dispatch({ type: SET_END_TIME, endTime: time });
    }

    useEffect(() => {
        const loadPharmacist = async () => {
            try {
                const result = await axiosConfig.get('/pharmacist/current');
                setPharmacist(result.data);
            }
            catch (err) {
                console.log(err);
            }
        }

        loadPharmacist();
       
     }, []);
    
    const addNewAbsenceRequest = async () =>{
        
        console.log(state);
        let newAbsence = {
            startDate : formatDate(state.startDate,state.startTime),
            endDate: formatDate(state.endDate,state.endTime),
            //ucitaj ova dva ispod i na button onda pozvati4
            pharmacyId: pharmacist.pharmacyID,
            userId: pharmacist.id
            
        };


        try{
            await axiosConfig.post('/absence/new',newAbsence);
            dispatch({type : INIT});
            // setLatestPL(state);
            setReload(!reload);
        }
        catch(err){
            console.log(err);
        }
    }



    return (  
<div>
         <Form onSubmit = {(e) => e.preventDefault()}>
           <Form.Group>
                <Form.Label>Enter the start date and time</Form.Label>
                <Row>
                    <Col>
                        <Form.Control type = "date" 
                            onChange = {(e) => dispatch({ type: SET_START_DATE, startDate: e.target.value }) }>

                        </Form.Control>
                        </Col>
                        <Col>
                        <Form.Control type = "time" 
                             onChange = {(e) => dispatch({type : SET_START_TIME, startTime : e.target.value})}
                            ></Form.Control>
                    </Col>
                </Row>
            </Form.Group>
            <Form.Group>
                <Form.Label>Enter the end date and time</Form.Label>
                <Row>
                    <Col>
                        <Form.Control type = "date"
                            onChange = {(e) => dispatch({ type: SET_END_DATE, endDate: e.target.value })}    
                        ></Form.Control>
                        </Col>
                        <Col>
                        <Form.Control type = "time" 
                             onChange = {(e) => dispatch({type : SET_END_TIME, endTime : e.target.value})}
                            ></Form.Control>
                    </Col>
                </Row>
            </Form.Group>
            </Form>
            <Button onClick={() => {
                addNewAbsenceRequest();
            }}>Create absence request</Button>
        </div>
    );
}
 
export default PharmacistAbsenceRequest;