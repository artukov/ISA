import React, { useState, useEffect } from 'react';
import { Card, Container,Row,Col,Button, Modal, Form } from 'react-bootstrap';
import { axiosConfig } from '../../../config/AxiosConfig';
import formatDate from '../../../config/DateFormatConfig';
import { urlNewReservation } from '../../../services/UrlService';

const PharmacyDrugDetails = ({drug,pharmacyID}) => {
    const [showModal, setShowModal] = useState(false);
    const [acceptanceDate, setAcceptanceDate] = useState(new Date());
    const closeModal = () => setShowModal(false);
    const openModal = () => setShowModal(true);

    const reserveDrug = async (id) => {
        
        const reservation = {
            accepted : false,
            acceptanceDate : formatDate(acceptanceDate, "00:00:00"),
            drugs : [ drug.id]
        }

        await axiosConfig.post(urlNewReservation + pharmacyID, reservation)
        .then(res => {
            console.log(res);
        })
        .catch(err => console.log(err.response));

        closeModal();
    };
    return ( 
        <div>
            <Card style={{ width: '20rem' }}>
                <Card.Header>
                    <Card.Title>Drug info</Card.Title>
                </Card.Header>
                <Card.Body>
                    <Container>
                        <Row >
                            <Col >Code : </Col>
                            <Col >{drug.code}</Col>
                        </Row>
                        <Row>
                            <Col>Name : </Col>
                            <Col>{drug.name}</Col>
                        </Row>
                        <Row>
                            <Col>Manifacturer : </Col>
                            <Col>{drug.manufacturer}</Col>
                        </Row>
                        <Row>
                            <Col>Shape :</Col>
                            <Col>{drug.shape}</Col>
                        </Row>
                        <Row>
                            <Col>Type : </Col>
                            <Col>{drug.type}</Col>
                        </Row>
                        <Row>
                            <Col>Amount : </Col>
                            <Col>{drug.amount}</Col>
                        </Row>
                    </Container>
                </Card.Body>
                <Card.Footer>
                    <Button variant="primary" onClick={() => openModal()} >Reserve</Button>
                  {/*   <Button variant="secondary" onClick = {() => openModifyModal()}>Modfiy</Button>
                    <Button variant="danger" onClick={()=>deleteDrug(drug.id)}>Delete</Button> */}
                </Card.Footer>
            </Card>
            <Modal show={showModal} onHide={closeModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Reserve drug : {drug.name}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group>
                            <Form.Label>Acceptance date</Form.Label>
                            <Form.Control type="date" onChange={(e)=> setAcceptanceDate(e.target.value)}></Form.Control>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="success" onClick={() => reserveDrug(drug.id)}>Ok</Button>
                    <Button variant="secondary" onClick = {closeModal}>Cancel</Button>
                </Modal.Footer>
            </Modal>
        </div>
     );
}
 
export default PharmacyDrugDetails;