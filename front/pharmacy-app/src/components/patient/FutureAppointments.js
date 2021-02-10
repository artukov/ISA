import React, { useState, useEffect } from 'react';
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const FutureAppointments = () => {
    
    const [appointments, setAppointments] = useState([]);
    const [consultations, setConsultations] = useState([]);
    const [dateAsc, setDateAsc] = useState({});
    const [priceAsc, setPriceAsc] = useState({});
    const [durationAsc, setDurationAsc] = useState({});

    useEffect(() => {
        const loadConsultations = async () => {
            try {
                const result = await axiosConfig.get('/patient/consultationsNotFinished');
                setConsultations(result.data);              
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }
        const loadAppointments = async (consult) => {
            try {
                setAppointments(consult);
                const result = await axiosConfig.get('/patient/examinationsNotFinished');
                setAppointments([...appointments,result.data]); 
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }
        loadConsultations();
        loadAppointments(consultations);
    }, [])

    const sortAppDate = () => {
        let result = null
        if (dateAsc) {
            result = appointments.sort((a, b) => (a.date > b.date) ? 1 : -1);
            setDateAsc(false);
        }
        else { 
            result = appointments.sort((a, b) => (a.date < b.date) ? 1 : -1);
            setDateAsc(true);
    }
        setAppointments([...result]);
    }
    const sortAppPrice = () => {
        let result = null
        if (priceAsc) {
            result = appointments.sort((a, b) => (a.price > b.price) ? 1 : -1);
            setPriceAsc(false);
        }
        else { 
            result = appointments.sort((a, b) => (a.price < b.price) ? 1 : -1);
            setPriceAsc(true);
    }
        setAppointments([...result]);
    }

    const sortAppDuration = () => {
        let result = null
        if (durationAsc) {
            result = appointments.sort((a, b) => (a.duration > b.duration) ? 1 : -1);
            setDurationAsc(false);
        }
        else { 
            result = appointments.sort((a, b) => (a.duration < b.duration) ? 1 : -1);
            setDurationAsc(true);
    }
        setAppointments([...result]);
    }
    return (

        <div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col onClick={() => {
                            sortAppDate();
                            } }>Date</Col>
                        <Col onClick={() => {
                            sortAppPrice();
                            } }>Price</Col>
                        <Col onClick={() => {
                            sortAppDuration();
                            } }>Duration</Col>
                        
                    </Row>
                </ListGroup.Item>
                {
                    appointments ? (
                        appointments.map((appointment,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{appointment.beggingDateTime}</Col>
                                    <Col>{appointment.price}</Col>
                                    <Col>{appointment.duration}</Col>
                                    
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
 
export default FutureAppointments;