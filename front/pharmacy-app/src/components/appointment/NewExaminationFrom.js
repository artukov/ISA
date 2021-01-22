import React, { useState, useEffect } from 'react';
import { Button, Form } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import { urlNewExamination } from '../../services/UrlService';

const NewExamination = ({dermaID, closeModal}) => {

    const [begDate, setBegDate] = useState('');
    const [begTime, setBegTime] = useState('');
    const [duration, setDuration] = useState(0.0);
    const [price, setPrice] = useState(0.0);

    const createExamination = async (e) =>{
        e.preventDefault();

        console.log(begDate , begTime, duration, price, dermaID);

        const examination = {
            beggingDateTime : formatDate(begDate, begTime),
            duration,
            price,
            dermatologist_id : dermaID,
            diagnose : null,
            report : null,
            drugs : [],
            patient_id : 0
        }

        // console.log('examination: \n',examination);

        try{
            const resault = await axiosConfig.post(urlNewExamination, examination);
            // console.log(resault);
        }
        catch(err){
            console.log(err.response);
            if(err.response.status === 400)
                alert(err.response.data);
        }


        closeModal();
    }

    return ( 
        <Form>
            <Form.Group>
                <Form.Label>Date of the exam</Form.Label>
                <Form.Control type = "date" onChange= {(e) => setBegDate(e.target.value)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Time of the exam</Form.Label>
                <Form.Control type = "time" onChange = {(e) => setBegTime(e.target.value)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Duration of the exam in minutes</Form.Label>
                <Form.Control type = "number" onChange = {(e) => setDuration(e.target.value)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Price of the exam</Form.Label>
                <Form.Control type = "number" onChange = {(e) => setPrice(e.target.value)}></Form.Control>
            </Form.Group>
            <Button onClick={(e) => createExamination(e)}>Save changes</Button>
            <Button variant = 'secondary' onClick={() => closeModal()}>Cancel</Button>
        </Form>
     );
}
 
export default NewExamination;