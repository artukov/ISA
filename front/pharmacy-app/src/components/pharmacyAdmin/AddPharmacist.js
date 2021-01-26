import React, { useState, useEffect } from 'react';
import { Button, Form, Row, Col } from 'react-bootstrap';
import formatDate from '../../config/DateFormatConfig';
// import userProfile from '../../reducer/userProfileReducer';
import UserProfileComponent from '../user/UserProfileComponent';

const AddPharmacist = ({addPharmacist}) => {

    const [pharmacist, setPharmacist] = useState({});

    const [date, setDate] = useState('');
    const [time, setTime] = useState('');
    const [hours, setHours] = useState('');

    const handleSubmit = (e) =>{

        if(e === null){
            addPharmacist(null);
            return;
        }

        e.preventDefault();
        pharmacist.hours = hours;
        pharmacist.start_hour = formatDate(date,time);
        addPharmacist(pharmacist);

    }

    return ( 
    <div>
         <UserProfileComponent setUser = {setPharmacist} hideRole = {true}></UserProfileComponent>
         <Form onSubmit = {(e) =>handleSubmit(e)}>
             <Form.Group>
                 <Form.Label>Insert starting data and hour of the new pharmacist</Form.Label>
                 <Row>
                     <Col>
                        <Form.Control type ="date" onChange={(e) => setDate(e.target.value)}></Form.Control>
                     </Col>
                     <Col>
                        <Form.Control type = "time" onChange = {(e) => setTime(e.target.value)}></Form.Control>
                     </Col>
                 </Row>
            </Form.Group> 
            <Form.Group>
                <Form.Label>Insert number of hours he/she will be working</Form.Label>
                <Form.Control type ="number" onChange = {(e) => setHours(e.target.value)}></Form.Control>
            </Form.Group> 
        </Form>   
        <Button onClick = {(e) => handleSubmit(e)}>Add pharmacist</Button>
        <Button variant ='secondary' onClick = {() => handleSubmit(null)}>Cancel</Button>
    </div> );
}
 
export default AddPharmacist;