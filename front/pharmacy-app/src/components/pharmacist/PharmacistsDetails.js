
import React, { useState, useEffect } from 'react';
import { Button, Card, Form, ListGroup, Modal } from 'react-bootstrap';
import formatDate from '../../config/DateFormatConfig';

const PharmacistDetails = ({pharmacist}) => {

    const [rating, setRating] = useState(0.0);
    const [showModal, setShowModal] = useState(false);
    const [beggingDateTime, setBeggingDateTime] = useState(new Date());

    const closeModal = () => setShowModal(false);
    const openModal = () => setShowModal(true);

    const calculateRatings = () =>{
        if(pharmacist.ratings.length === 0)
            return 0.0;
        let sum = 0;
        for( let rating of pharmacist.ratings){
            sum += rating;
        }
        return sum / pharmacist.ratings.length;
    }
    
    useEffect(() => {
        setRating(calculateRatings()); 
    }, [pharmacist]);

    const bookConsultation = () =>{
        /**
         * TODO:
         * Create a new consultation with the pharmacist
         */
        let consultation = {
            report : null,
            beggingDateTime : formatDate(beggingDateTime),
            duration : 0,
            pharmacistID : pharmacist.id,
            drugs : []
        }

        closeModal();
        console.log(consultation);
        return consultation;
        
    }

   

    return ( 
    <div>
        {/* <h6>PharmacistDetails component</h6>
        <p>{pharmacist.email}</p>
        <p>{pharmacist.firstname}</p>
        <p>{pharmacist.lastname}</p>
        <p>{pharmacist.phoneNumber}</p>
        <p>{pharmacist.role}</p>
        <p>{pharmacist.hours}</p>
        <p>{pharmacist.start_hour}</p> */}
        <Card>
            <Card.Header>Pharmacist info</Card.Header>
            <ListGroup>
                <ListGroup.Item>Firstname : {pharmacist.firstname}</ListGroup.Item>
                <ListGroup.Item>Lastname : {pharmacist.lastname}</ListGroup.Item>
                <ListGroup.Item>Ratings : {rating}</ListGroup.Item>
            </ListGroup>
            <Card.Footer>
                <Button variant="primary" onClick= {() => openModal()}>Book a consultation</Button>
            </Card.Footer>
        </Card>

        <Modal show = {showModal} onHide={closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Book a consultation with pharmacist : {pharmacist.firstname}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group>
                        <Form.Label>Acceptance date</Form.Label>
                        <Form.Control type="date" onChange={(e)=> setBeggingDateTime(e.target.value)}></Form.Control>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="success" onClick={() => bookConsultation()}>Ok</Button>
                <Button variant="secondary" onClick = {closeModal}>Cancel</Button>
            </Modal.Footer>
        </Modal>
    </div> 
    );
}
 
export default PharmacistDetails;