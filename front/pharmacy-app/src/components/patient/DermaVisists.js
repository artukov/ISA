import React, { useState, useEffect } from 'react'
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const DermaVisists = () => {
    const [examinations, setExaminations] = useState([]);
    const [dateAsc, setDateAsc] = useState({});
    const [priceAsc, setPriceAsc] = useState({});
    const [durationAsc, setDurationAsc] = useState({});

    useEffect(() => {
        const loadExaminations = async () => {
            try {
                const result = await axiosConfig.get('/patient/examinations');
                setExaminations(result.data);
                
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }
        loadExaminations();
    }, [])

    const sortExamDate = () => {
        let result = null
        if (dateAsc) {
            result = examinations.sort((a, b) => (a.date > b.date) ? 1 : -1);
            setDateAsc(false);
        }
        else { 
            result = examinations.sort((a, b) => (a.date < b.date) ? 1 : -1);
            setDateAsc(true);
    }
        setExaminations([...result]);
    }
    const sortExamPrice = () => {
        let result = null
        if (priceAsc) {
            result = examinations.sort((a, b) => (a.price > b.price) ? 1 : -1);
            setPriceAsc(false);
        }
        else { 
            result = examinations.sort((a, b) => (a.price < b.price) ? 1 : -1);
            setPriceAsc(true);
    }
        setExaminations([...result]);
    }

    const sortExamDuration = () => {
        let result = null
        if (durationAsc) {
            result = examinations.sort((a, b) => (a.duration > b.duration) ? 1 : -1);
            setDurationAsc(false);
        }
        else { 
            result = examinations.sort((a, b) => (a.duration < b.duration) ? 1 : -1);
            setDurationAsc(true);
    }
        setExaminations([...result]);
    }

    return ( 
        <div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col onClick={() => {
                            sortExamDate();
                            } }>Date</Col>
                        <Col onClick={() => {
                            sortExamPrice();
                            } }>Price</Col>
                        <Col onClick={() => {
                            sortExamDuration();
                            } }>Duration</Col>
                        
                    </Row>
                </ListGroup.Item>
                {
                    examinations ? (
                        examinations.map((examination,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{examination.beggingDateTime}</Col>
                                    <Col>{examination.price}</Col>
                                    <Col>{examination.duration}</Col>
                                    
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
 
export default DermaVisists;