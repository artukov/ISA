import React, { useState, useEffect } from 'react';
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const Ereceipts = () => {

    const [codeAsc, setCodeAsc] = useState({});
    const [dateAsc, setDateAsc] = useState({});
    const [eReceipts, setEReceipts] = useState([]);


    useEffect(() => {
        const loadEReceipts = async () => {
            try {
                const result = await axiosConfig.get('/patient/eReceipts');
                setEReceipts(result.data);
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }
        loadEReceipts();
    }, [])

    const sortDate = () => {
        let result = null
        if (dateAsc) {
            result = eReceipts.sort((a, b) => (a.issue_date > b.issue_date) ? 1 : -1);
            setDateAsc(false);
        }
        else { 
            result = eReceipts.sort((a, b) => (a.issue_date < b.issue_date) ? 1 : -1);
            setDateAsc(true);
    }
        setEReceipts([...result]);
    }

    const sortCode = () => {
        let result = null
        if (codeAsc) {
            result = eReceipts.sort((a, b) => (a.code > b.code) ? 1 : -1);
            setCodeAsc(false);
        }
        else { 
            result = eReceipts.sort((a, b) => (a.code < b.code) ? 1 : -1);
            setCodeAsc(true);
    }
        setEReceipts([...result]);
    }
    return (  
        <div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col onClick={() => {
                            sortCode();
                            } }>Code</Col>
                        <Col onClick={() => {
                            sortDate();
                            } }>Issue date</Col>
                        
                    </Row>
                </ListGroup.Item>
                {
                    eReceipts ? (
                        eReceipts.map((eReceipt,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{eReceipt.code}</Col>
                                    <Col>{eReceipt.issue_date}</Col>
                                    
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
 
export default Ereceipts;