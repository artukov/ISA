import React, { useState, useEffect } from 'react';
import { Card, Form, ListGroup, Button, Modal } from 'react-bootstrap';


const ComplaintDetails = ({complaint,saveComplaint}) => {
    // const [showModal, setShowModal] = useState(false);
    // const openModal = () => setShowModal(true);
    // const closeModal = () => setShowModal(false);

    const referresTo = () => {
        if(complaint.pharmacyID !== null)
            return true;
        return false;
    }

    const saveAnswer = (e) => {
        e.preventDefault();
        saveComplaint(complaint);
        // closeModal();
    }



    return (  
        <div>
            <Card>
                <Card.Header><Card.Title>Complaint info</Card.Title></Card.Header>
                <ListGroup>
                    <ListGroup.Item>
                        Submitter email : {complaint.submitterEmail}
                    </ListGroup.Item>
                    <ListGroup.Item>
                        Referres to : &nbsp;
                        {
                            referresTo() ? (<span>{complaint.pharmacyName}</span>) : 
                            (<span>{complaint.referredEmail}</span>)
                        }
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <Form.Control as="textarea" rows={4} readOnly value={complaint.description}></Form.Control>
                    </ListGroup.Item>
                </ListGroup>
                <Card.Body>
                    <Form>
                        <Form.Label>Enter your answer</Form.Label>
                        <Form.Control as="textarea" rows={4} defaultValue={complaint.answer ? complaint.answer : ''}
                            onChange={(e)=> complaint.answer = e.target.value}>
                        </Form.Control>
                    </Form>
                </Card.Body>
                <Card.Footer>
                    <Button onClick={(e)=>saveAnswer(e)}>Save yor answer</Button>
                </Card.Footer>
            </Card>

            {/* <Modal show={showModal} onHide={()=>closeModal()}>
                <Modal.Header closeButton><Modal.Title>Giving answer to a complaint</Modal.Title></Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Label>Enter your answer</Form.Label>
                        <Form.Control as="textarea" rows={4} defaultValue={complaint.answer ? complaint.answer : ''}
                        onChange={(e)=> complaint.answer = e.target.value}>
                        </Form.Control>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={(e)=>saveAnswer(e)}>Save changes</Button>
                    <Button variant="secondary" onClick={()=>closeModal()}>Cancel</Button>
                </Modal.Footer>
            </Modal> */}

        </div>
    );
}
 
export default ComplaintDetails;