
import React, { useState, useEffect, useContext } from 'react';
import { Button, Card, ListGroup, Modal } from 'react-bootstrap';

import NewExamination from '../appointment/NewExaminationFrom';
import { DermatologistContext } from './DermatologistContext';

const DermatologistDetails = ({dermatologist}) => {

    const {state,deleteDermatologist} = useContext(DermatologistContext);

    const [showExamModal, setShowExamModal] = useState(false);
    const closeExamModal = () => setShowExamModal(false);
    const openExamModal = () => setShowExamModal(true);


    return (  
        <div>
            
            <Card >
                <Card.Header>
                    <Card.Title>Dermatologist details</Card.Title>
                </Card.Header>
                <Card.Body>
                    <Card.Text>Email : {dermatologist.email}</Card.Text>
                    <Card.Text>Firstname : {dermatologist.firstname}</Card.Text>
                    <Card.Text>Lastname : {dermatologist.lastname}</Card.Text>
                    <Card.Text>Phone number : {dermatologist.phoneNumber}</Card.Text>
                    <Card.Text>Ratings : {dermatologist.ratings !== null ? dermatologist.ratings : 0.0}</Card.Text>
                </Card.Body>
                <ListGroup>
                    <ListGroup.Item action variant="info">Working hours : {dermatologist.hours}</ListGroup.Item>
                    <ListGroup.Item action variant="info">Starting hours : {dermatologist.start_hour}</ListGroup.Item>
                </ListGroup>
                <Card.Footer>
                    <Button variant='primary' onClick={() => openExamModal()}>Create examination</Button>
                    <Button variant='danger' onClick={() => deleteDermatologist(dermatologist.id)}>Delete dermatologist</Button>
                </Card.Footer>
            </Card>

            <Modal show = {showExamModal} onHide={closeExamModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Creating new examination</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>
                        Dermatologist email :  {dermatologist.email}
                    </p>
                    <NewExamination dermaID = {dermatologist.id} closeModal = {closeExamModal}
                        dermaWorkingHours = {{startHours :dermatologist.start_hour,hours :dermatologist.hours}}></NewExamination>
                </Modal.Body>
            </Modal>



        </div>
    );
}
 
export default DermatologistDetails;