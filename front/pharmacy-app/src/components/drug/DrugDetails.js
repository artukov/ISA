import React, { useState, useEffect } from 'react'
import { Button, Form, Modal } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import { urlNewReservation } from '../../services/UrlService';

const DrugDetails = ({drug}) => {

    const [showModal, setShowModal] = useState(false);
    const [acceptanceDate, setAcceptanceDate] = useState(new Date());
    
    const closeModal = () => setShowModal(false);
    const openModal = () => setShowModal(true);


    const reserveDrug = async (id) => {
        
        const reservation = {
            accepted : false,
            acceptanceDate : formatDate(acceptanceDate, null),
            drugs : [ drug.id]
        }

        await axiosConfig.post(urlNewReservation + 200, reservation)
        .then(res => {
            console.log(res);
        })
        .catch(err => console.log(err.response));

        closeModal();
    }



    return ( 
        <div>
            <h6>DrugDetails component</h6>
            <p>{drug.code}</p>
            <p>{drug.name}</p>
            <p>{drug.manufacturer}</p>
            <p>{drug.receipt}</p>
            <p>{drug.shape}</p>
            <p>{drug.type}</p>
            <p>amount : {drug.amount}</p>
            <Button variant="primary" onClick={() => openModal()} >Reserve</Button>
          

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
 
export default DrugDetails;