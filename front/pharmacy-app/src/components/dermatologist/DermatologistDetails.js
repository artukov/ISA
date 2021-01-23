
import React, { useState, useEffect } from 'react';
import { Button, Card, ListGroup, Modal } from 'react-bootstrap';
// import { axiosConfig } from '../../config/AxiosConfig';
// import { urlDeleteDermaPharmacy } from '../../services/UrlService';
import NewExamination from '../appointment/NewExaminationFrom';

const DermatologistDetails = ({dermatologist, deleteDermatologistFromPharmacy}) => {

    const [showExamModal, setShowExamModal] = useState(false);
    const closeExamModal = () => setShowExamModal(false);
    const openExamModal = () => setShowExamModal(true);

    // const deleteDermatologistFromPharmacy = async () =>{
    //     try{
    //         const resault = await axiosConfig.delete(urlDeleteDermaPharmacy + dermatologist.id + "/" + pharmacyID);
    //         console.log(resault);
    //     }
    //     catch(err){
    //         alert(err.response.data);
    //     }
    // }

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
                </Card.Body>
                <ListGroup>
                    <ListGroup.Item action variant="info">Working hours : {dermatologist.hours}</ListGroup.Item>
                    <ListGroup.Item action variant="info">Starting hours : {dermatologist.start_hour}</ListGroup.Item>
                </ListGroup>
                <Card.Footer>
                    <Button variant='primary' onClick={() => openExamModal()}>Create examination</Button>
                    <Button variant='danger' onClick={() => deleteDermatologistFromPharmacy(dermatologist.id)}>Delete dermatologist</Button>
                </Card.Footer>
            </Card>
            {/* <h6>Dermatologists details component</h6>
            <p>{dermatologist.email}</p>
            <p>{dermatologist.firstname}</p>
            <p>{dermatologist.lastname}</p>
            <p>{dermatologist.phoneNumber}</p>
            <p>{dermatologist.role}</p>
            <p>{dermatologist.hours}</p>
            <p>{dermatologist.start_hour}</p> */}

            <Modal show = {showExamModal} onHide={closeExamModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Creating new examination</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>
                        Dermatologist email :  {dermatologist.email}
                    </p>
                    <NewExamination dermaID = {dermatologist.id} closeModal = {closeExamModal}></NewExamination>
                </Modal.Body>
            </Modal>



        </div>
    );
}
 
export default DermatologistDetails;