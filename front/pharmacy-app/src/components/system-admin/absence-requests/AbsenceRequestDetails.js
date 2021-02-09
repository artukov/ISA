import React, { useState, useEffect, useContext } from 'react';
import {Card, Form, Modal, Button, ListGroup} from 'react-bootstrap';
import {AbsenceRequestContext} from './AbsenceRequestsContext'

const AbsenceRequestDetails = ({request}) => {
    const {answerToTheRequest} = useContext(AbsenceRequestContext);
    const [showModal, setShowModal] = useState();
    const openModal = () => setShowModal(true);
    const closeModal = () => setShowModal(false);
    return ( 
        <div>
           <Card bg="dark" text="light">
                <Card.Header><Card.Title>Absence request info</Card.Title></Card.Header>
                <ListGroup>
                    <ListGroup.Item style={{backgroundColor : 'blue'}}>Dermatologist : {request.userEmail}</ListGroup.Item>
                    <ListGroup.Item style={{backgroundColor : 'blue'}}>Start date : {request.startDate}</ListGroup.Item>
                    <ListGroup.Item style={{backgroundColor : 'blue'}}>End date : {request.endDate}</ListGroup.Item>
                    <ListGroup.Item style={{backgroundColor : 'blue'}}>Pharmacy : {request.pharmacyName}</ListGroup.Item>
                </ListGroup>
                <Card.Footer>
                    <Button variant="success" onClick={()=> answerToTheRequest(request,true)}>Accept request</Button>
                    <Button variant="warning" onClick={()=> openModal()}>Denied request</Button>
                </Card.Footer>
            </Card>

            <Modal show={showModal} onHide={()=>closeModal()}>
                <Modal.Header closeButton><Modal.Title>Add description why the request has been denied</Modal.Title></Modal.Header>
                <Modal.Body>
                    <Form onSubmit={()=>{}}>
                        <Form.Control type="text" placeholder="Insert desciption" 
                            onChange = {(e) => request.description = e.target.value}
                        ></Form.Control>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick = {(e)=>{
                        e.preventDefault();
                        answerToTheRequest(request,false);
                        closeModal();
                    }}>Save changes</Button>
                    <Button variant="secondary" onClick = {()=>closeModal()}>Cancel</Button>
                </Modal.Footer>
            </Modal>
        </div>
     );
}
 
export default AbsenceRequestDetails;