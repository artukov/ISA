import React, { useState, useEffect } from 'react'
import { Card, Col, Container, ListGroup, Row } from 'react-bootstrap';
import axiosConfig from '../../config/AxiosConfig';
import {urlGetDermatologist} from '../../services/UrlService';

const ExaminationDetails = ({examination}) => {

    const [dermatologist, setDermatologist] = useState({});

    useEffect(() => {
        axiosConfig.get(urlGetDermatologist+examination.dermatologist_id)
        .then(res => {
            setDermatologist(res.data)})
        .catch(err => alert(err.response.data));
        return () => {
        }
    }, [examination])

    return ( 
    <div>
        <Card  style={{ width: '18rem' }}>
            <Card.Header>Examination</Card.Header>
            <Card.Body>
                <Container>
                    <Row>
                        <Col>{ dermatologist.firstname}</Col>
                        <Col>{dermatologist.lastname}</Col>
                    </Row>
                </Container>
            </Card.Body>
            <ListGroup>
                <ListGroup.Item>Date : {examination.beggingDateTime}</ListGroup.Item>
                <ListGroup.Item>Duration : {examination.duration}</ListGroup.Item>
                <ListGroup.Item>Price : {examination.price} $</ListGroup.Item>
            </ListGroup>
            <Card.Footer></Card.Footer>
        </Card>
    </div> );
}
 
export default ExaminationDetails;