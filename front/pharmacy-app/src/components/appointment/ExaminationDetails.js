import React, { useState, useEffect } from 'react'
import { Button, Card, Col, Container, ListGroup, Row } from 'react-bootstrap';
import axiosConfig from '../../config/AxiosConfig';
import {urlGetDermatologist, urlModifyExamination} from '../../services/UrlService';

const ExaminationDetails = ({examination}) => {

    const [dermatologist, setDermatologist] = useState({});

    useEffect(() => {
        axiosConfig.get(urlGetDermatologist+examination.dermatologist_id)
        .then(res => {
            setDermatologist(res.data)})
        .catch(err => alert(err.response.data));
        return () => {
        }
    }, [examination]);

    const makeAppointment = async (id) =>{
        await axiosConfig.put(urlModifyExamination + id, examination)
        .then(res => {
            console.log(res);
            /**
             * @TODO
             * Reload examinations when appointment is made successful
             *  */
        })
        .catch(err => console.log(err.response));
    }

    return ( 
    <div>
        <Card  style={{ width: '18rem' }}>
            <Card.Header>Examination</Card.Header>
            <Card.Body>
                <Container>
                    <Row>
                        <Col>{dermatologist.firstname}</Col>
                        <Col>{dermatologist.lastname}</Col>
                    </Row>
                </Container>
            </Card.Body>
            <ListGroup>
                <ListGroup.Item>Date : {examination.beggingDateTime}</ListGroup.Item>
                <ListGroup.Item>Duration : {examination.duration}</ListGroup.Item>
                <ListGroup.Item>Price : {examination.price} $</ListGroup.Item>
            </ListGroup>
            <Card.Footer>
                <Button onClick={()=>makeAppointment(examination.id)}>Make an appointment</Button>
            </Card.Footer>
        </Card>
    </div> );
}
 
export default ExaminationDetails;