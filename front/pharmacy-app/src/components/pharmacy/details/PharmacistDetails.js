import React, { useState, useEffect } from 'react';
import { Card, ListGroup, Button,Modal,Form } from 'react-bootstrap';
import formatDate from '../../../config/DateFormatConfig';

const PharmacyPharmacistDetails = ({pharmacist}) => {
    const [rating, setRating] = useState(0.0);
    const [beggingDateTime, setBeggingDateTime] = useState(new Date());
    const [showModal, setShowModal] = useState(false);
    const closeModal = () => setShowModal(false);
    const openModal = () => setShowModal(true);
    useEffect(() => {
        const calculateRatings = () =>{
            if(pharmacist.ratings.length === 0)
                return 0.0;
            let sum = 0;
            for( let rating of pharmacist.ratings){
                sum += rating;
            }
            return sum / pharmacist.ratings.length;
        }
        setRating(calculateRatings()); 
    }, [pharmacist]);

    const bookConsultation = () =>{
        
        let consultation = {
            report : null,
            beggingDateTime : formatDate(beggingDateTime,"00:00:00"),
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
            <Card>
            <Card.Header>Pharmacist info</Card.Header>
            <ListGroup>
                <ListGroup.Item>Email : {pharmacist.email}</ListGroup.Item>
                <ListGroup.Item>Firstname : {pharmacist.firstname}</ListGroup.Item>
                <ListGroup.Item>Lastname : {pharmacist.lastname}</ListGroup.Item>
                <ListGroup.Item>Ratings : {rating}</ListGroup.Item>
                <ListGroup.Item>Phone number : {pharmacist.phoneNumber}</ListGroup.Item>
                <ListGroup.Item>Start hours : {pharmacist.start_hour}</ListGroup.Item>
                <ListGroup.Item>Hours : {pharmacist.hours}</ListGroup.Item>
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
 
export default PharmacyPharmacistDetails;