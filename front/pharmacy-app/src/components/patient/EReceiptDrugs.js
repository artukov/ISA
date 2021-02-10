import React, { useState, useEffect } from 'react';
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const EReceiptDrugs = () => {
    const [drugs, setDrugs] = useState([]);

    useEffect(() => {
        const laodDrugs = async () => {
            try {
                const result = await axiosConfig.get('/patient/eReceiptDrugs');
                setDrugs(result.data);
            } catch (err) {
                console.log(err);
                alert(err.response.data);
           }
        }
        laodDrugs();
    }, [])

    return (
        <div>
                        <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col>Name</Col>
                        <Col>Manufacturer</Col>
                        <Col>Type</Col>
                        <Col>Shape</Col>
                        
                    </Row>
                </ListGroup.Item>
                {
                    drugs ? (
                        drugs.map((drug,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{drug.name}</Col>
                                    <Col>{drug.manufacturer}</Col>
                                    <Col>{drug.type}</Col>
                                    <Col>{drug.shape}</Col>
                                    
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
 
export default EReceiptDrugs;