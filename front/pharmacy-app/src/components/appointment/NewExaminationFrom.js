import React, { useState, useEffect } from 'react';
import { Button, Form } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import { urlNewExamination } from '../../services/UrlService';

const NewExamination = ({dermaID, closeModal, dermaWorkingHours}) => {

    const [begDate, setBegDate] = useState('');
    const [begTime, setBegTime] = useState('');
    const [duration, setDuration] = useState(0.0);
    const [price, setPrice] = useState(0.0);


    function settingUpTime(date,time){
        let tempDate = new Date(date);
        const [hours, minutes, ] = time.split(':');
        tempDate.setHours(hours);
        tempDate.setMinutes(minutes);

        return tempDate;
    }

    function reverseDate(date){
        const [days, months, year] = date.split('-');

        return year + "-" + months + "-" + days; 
    }

    function betweenWorkingHours(startDateTime, endDateTime, begDateTime){

        if(startDateTime.getHours() + dermaWorkingHours.hours >= 24 && dermaWorkingHours.hours <= 12){
            startDateTime.setHours(startDateTime.getHours() - 12);
            endDateTime.setHours(endDateTime.getHours() - 12);
            begDateTime.setHours(begDateTime.getHours() -12 );
        }

        let temp = new Date(begDateTime);
        temp.setHours(startDateTime.getHours());
        temp.setMinutes(startDateTime.getMinutes());
       

        if(Date.parse(begDateTime) < Date.parse(temp)){
            return false;
        }

        temp.setHours(endDateTime.getHours());
        temp.setMinutes(endDateTime.getMinutes());

        if(Date.parse(begDateTime) > Date.parse(temp))
            return false;


        begDateTime.setMinutes(begDateTime.getMinutes() + parseInt(duration));
        
        if(Date.parse(begDateTime) > Date.parse(temp))
            return false;

        return true;

    }

    const checkDateAndWorkingHours = () =>{
        let begDateTime = settingUpTime(begDate,begTime);
        

        let [startDate, startTime, ] = dermaWorkingHours.startHours.split(" ");
     

        startDate = reverseDate(startDate);
        let startDateTime = settingUpTime(startDate,startTime);

        let endDateTime = new Date(startDateTime);
        endDateTime.setHours(endDateTime.getHours() + dermaWorkingHours.hours);

    
        if(begDateTime.getTime() < startDateTime.getTime()){
            throw new Error("Dermatologist is not working at the pharmacy at that date");
        }
        
        if(!betweenWorkingHours(startDateTime,endDateTime,begDateTime)){
            throw new Error("Time of the examination is not in dermatologist's working hours");
        }


        
    }

    const createExamination = async (e) =>{
        e.preventDefault();

        console.log(begDate , begTime, duration, price, dermaID);

        try{
            checkDateAndWorkingHours();
        }
        catch(err){
            alert(err.message);
            closeModal();
            return;
        }

        const examination = {
            beggingDateTime : formatDate(begDate, begTime),
            duration,
            price,
            dermatologist_id : dermaID,
            diagnose : null,
            report : null,
            drugs : [],
            patient_id : 0,
            pharmacyID : 200
        }

        // console.log('examination: \n',examination);

        try{
             await axiosConfig.post(urlNewExamination, examination);
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
                <Form.Control type = "date" value={begDate} onChange= {(e) => setBegDate(e.target.value)}></Form.Control>
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