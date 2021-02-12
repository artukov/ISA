import React, { useState, useEffect } from 'react'
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const PharmacistVisits = () => {

    
    const [consultations, setConsultations] = useState([]);
    const [dateAsc, setDateAsc] = useState({});
    const [priceAsc, setPriceAsc] = useState({});
    const [durationAsc, setDurationAsc] = useState({});

    useEffect(() => {
        const loadConsultations = async () => {
            try {
                const result = await axiosConfig.get('/patient/consultations');
                setConsultations(result.data);              
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }
        loadConsultations();
    }, [])

    const sortConsDate = () => {
        let result = null
        if (dateAsc) {
            result = consultations.sort((a, b) => (a.date > b.date) ? 1 : -1);
            setDateAsc(false);
        }
        else { 
            result = consultations.sort((a, b) => (a.date < b.date) ? 1 : -1);
            setDateAsc(true);
    }
        setConsultations([...result]);
    }
    const sortConsPrice = () => {
        let result = null
        if (priceAsc) {
            result = consultations.sort((a, b) => (a.price > b.price) ? 1 : -1);
            setPriceAsc(false);
        }
        else { 
            result = consultations.sort((a, b) => (a.price < b.price) ? 1 : -1);
            setPriceAsc(true);
    }
        setConsultations([...result]);
    }

    const sortConsDuration = () => {
        let result = null
        if (durationAsc) {
            result = consultations.sort((a, b) => (a.duration > b.duration) ? 1 : -1);
            setDurationAsc(false);
        }
        else { 
            result = consultations.sort((a, b) => (a.duration < b.duration) ? 1 : -1);
            setDurationAsc(true);
    }
        setConsultations([...result]);
    }

    return ( 
        <div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col onClick={() => {
                            sortConsDate();
                            } }>Date</Col>
                        <Col onClick={() => {
                            sortConsPrice();
                            } }>Price</Col>
                        <Col onClick={() => {
                            sortConsDuration();
                            } }>Duration</Col>
                        
                    </Row>
                </ListGroup.Item>
                {
                    consultations ? (
                        consultations.map((consultation,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{consultation.beggingDateTime}</Col>
                                    <Col>{consultation.price}</Col>
                                    <Col>{consultation.duration}</Col>
                                    
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
 
export default PharmacistVisits;