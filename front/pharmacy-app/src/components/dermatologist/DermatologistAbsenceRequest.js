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
        const loadDermatologist = async () => {
            try {
                const result = await axiosConfig.get('/dermatologist/current');
                setDermatologist(result.data);
            }
            catch (err) {
                console.log(err);
                alert(err.response.data);
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
                alert(err.response.data);
            }
            
        }
        if (dermatologist.id !== undefined)
                loadDermatologistPharmacies(dermatologist.id);
    }, [dermatologist.id])
    
    const addNewAbsenceRequest = async () =>{
        
        console.log(state);
        let newAbsence = {
            startDate : formatDate(state.startDate,state.startTime),
            endDate: formatDate(state.endDate,state.endTime),
            //ucitaj ova dva ispod i na button onda pozvati4
            pharmacyId: pharmacy.id,
            userId: dermatologist.id
            
        };


        try{
            await axiosConfig.post('/absence/new',newAbsence);
            //dispatch({type : INIT});
            // setLatestPL(state);
            setReload(!reload);
            alert("Absence request created");
        }
        catch(err){
            console.log(err);
            alert(err.response.data);
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
                <div>Select pharmacy</div>
                            <Form.Control as="select" onClick = {(e) => setPharmacy(JSON.parse(e.target.value))}>
                            {
                                pharmacies ? (
                                    pharmacies.map(pharmacy =>
                                        <option key={pharmacy.pharmacy_id} value={JSON.stringify({id : pharmacy.pharmacy_id, name : pharmacy.name})}>
                                            {pharmacy.pharmaName}
                                        </option>
                                        )
                                ) : null
                            }
                        </Form.Control>
            </Form>
            <Button onClick={() => {
                addNewAbsenceRequest();
            }}>Create absence request</Button>
        </div>

     );
}
 
export default DermatologistAbsenceRequest;