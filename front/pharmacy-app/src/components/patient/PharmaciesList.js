import React, { useState, useEffect } from 'react'
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const PharmaciesList = () => {

    const [pharmacies, setPharmacies] = useState([]);

    useEffect(() => {
        const loadPharmacies = async () => {
            try {
                const result = await axiosConfig.get('/pharmacy/findAll');
                setPharmacies(result.data);
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }

        loadPharmacies();
    }, [])

    return (  
        <div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col>Name</Col>
                        <Col>Rating</Col>
                        <Col >City</Col>
                        
                    </Row>
                </ListGroup.Item>
                {
                    pharmacies ? (
                        pharmacies.map((pharmacy,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{pharmacy.name}</Col>
                                    <Col>{pharmacy.ratings}</Col>
                                    <Col>{pharmacy.address.city}</Col>
                                    
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
 
export default PharmaciesList;