import React, { useState, useEffect, useReducer } from 'react'
import { Button, Col, Form, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import absencerequestReducers, { INIT, SET_END_DATE, SET_END_TIME, SET_START_DATE, SET_START_TIME } from './AbsenceRequestReducer';
const DermatologistAbsenceRequest = () => {

    const [dermatologist, setDermatologist] = useState({});
    const [reload, setReload] = useState(false)
    const [pharmacies, setPharmacies] = useState([]);
    const [pharmacy, setPharmacy] = useState({});

    const [state, dispatch] = useReducer(absencerequestReducers, {
        startDate: '',
        endDate: ''
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
        const loadDermatologistPharmacies = async (id) => {
            try {
                const result = await axiosConfig.get('/dermatologist/pharmacies/'+ id);
                setPharmacies(result.data);

            } catch (err) {
                console.log(err);
            }
            
        }
        if (dermatologist.id !== undefined)
                loadDermatologistPharmacies(dermatologist.id);
    }, [dermatologist.id])
    
    const addNewAbsenceRequest = async () =>{
        
        // console.log(state);
        let newAbsence = {
            startDate : formatDate(state.startDate,state.startTime),
            endDate: formatDate(state.endDate,state.endTime),
            //ucitaj ova dva ispod i na button onda pozvati4
            pharmacyId: pharmacy.pharmacy_id,
            userId: dermatologist.id
            
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
                             onChange = {(e) => dispatch({type : SET_START_TIME, time : e.target.value})}
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
                             onChange = {(e) => dispatch({type : SET_END_TIME, time : e.target.value})}
                            ></Form.Control>
                    </Col>
                </Row>
            </Form.Group>
            </Form>
            <ListGroup>
                {
                    pharmacies ? (
                        pharmacies.map((pharmacy,index) =>
                            <ListGroup.Item onClick={() => {
                                setPharmacy(pharmacy.pharmacy_id);
                            } } key={index} >{ pharmacy.pharmacy_id}</ListGroup.Item>
                            )
                    ) : null
                }
            </ListGroup>
            <Button onClick={() => {
                addNewAbsenceRequest();
            }}>Create absence request</Button>
        </div>

     );
}
 
export default DermatologistAbsenceRequest;