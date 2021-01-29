import React, { useState, useEffect,useContext } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
// import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
// import dermatologistReducer from '../../reducer/dermatologistReducer';
// import { urlAddNewDermatologist, urlGetDermaNotInPharmacy } from '../../services/UrlService';
import { DermatologistContext } from '../dermatologist/DermatologistContext';

const AddDermatologist = () => {

    const {state,loadDermatologistNotInPharmacy,addDermaToList} = useContext(DermatologistContext);


    
    const [dermaID, setDermaID] = useState('');
    
    const [hours, setHours] = useState();
    const [startDate, setStartDate] = useState();
    const [startTime, setStartTime] = useState();

    

    useEffect(() => { 
       if(state.showAddForm)
        setDermaID(state.unemployed[0].id);
    }, [state.showAddForm,state.unemployed]);


    const addNewDermatologist =  (e) =>{
        e.preventDefault();
      
        console.log(dermaID, formatDate(startDate, startTime));

        const workingHours = {
            hours,
            startHour : formatDate(startDate, startTime)
        }

        addDermaToList(dermaID, workingHours);

    }

    return ( 
    <div>
        <Container>
            <Row m = {6}>
            <Button variant = 'dark' onClick = { () =>{
                loadDermatologistNotInPharmacy(state.pharmacyID);
            }}>Add new dermatologist</Button>
            </Row>
            <Row  m = {6}>
            {
                state.showAddForm ? (
                    <Form>
                        <Form.Group>
                        <Form.Label>Chose the dermatologist</Form.Label>
                        <Form.Control as="select" onClick = {(e) => setDermaID(e.target.value)}>
                            {
                                state.unemployed ? (
                                
                                    state.unemployed.map(dermatologist => (
                                        <option key = {dermatologist.id} value = {dermatologist.id} 
                                        >{dermatologist.email}</option>
                                    ))
                                    
                                ) : null
                            }
                        </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Enter starting hours and the date</Form.Label>
                            <Row>
                                <Col>
                                    <Form.Control type="date"  onChange = {(e)=> setStartDate(e.target.value)}></Form.Control>
                                </Col>
                                <Col>
                                    <Form.Control type = "time"   onChange = {(e)=> setStartTime(e.target.value)}></Form.Control>
                                </Col>
                            </Row>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Enter number of hours he will be working</Form.Label>
                            <Form.Control type = "number"   onChange = {(e)=> setHours(e.target.value)}></Form.Control>
                        </Form.Group>
                        <Button onClick = {(e) => addNewDermatologist(e)}>Add new dermatologist</Button>
                        
                    </Form>
                ) : null
            }
            </Row>
        </Container> 
    </div> );
}
 
export default AddDermatologist;