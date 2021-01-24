import React, { useState, useEffect } from 'react'
import { Button, Card, Col, Container, Form, Modal, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import { urlNewReservation } from '../../services/UrlService';

const initialState = '';

const DrugDetails = ({drug,deleteDrug, modifyDrug}) => {

    const [showModal, setShowModal] = useState(false);
    const [showModifyModal, setShowModifyModal] = useState(false);

    const [acceptanceDate, setAcceptanceDate] = useState(new Date());

    /**{/* <h6>DrugDetails component</h6>
            <p>{drug.code}</p>
            <p>{drug.name}</p>
            <p>{drug.manufacturer}</p>
            <p>{drug.receipt}</p>
            <p>{drug.shape}</p>
            <p>{drug.type}</p>
            <p>amount : {drug.amount}</p> \*\/}
     */

    /**
     * TODO
     * Substitude drugs
     * DrugSpecification
     */

    const [code, setCode] = useState(initialState);
    // const [name, setName] = useState(initialState);
    const [manufacturer, setManufacturer] = useState(initialState);
    const [receipt, setReceipt] = useState(false);
    const [shape, setShape] = useState(initialState);
    const [type, setType] = useState(initialState);
    const [amount, setAmount] = useState(0.0);

    
    
    const closeModal = () => setShowModal(false);
    const openModal = () => setShowModal(true);

    const openModifyModal = () => setShowModifyModal(true);
    const closeModifyModal = () => setShowModifyModal(false)


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
    };

    const submitChanges = (e) =>{

        e.preventDefault();
        const modifiedDrug = {
            id : drug.id,
            amount,
            type,
            shape,
            receipt,
            name : drug.name,
            code,
            manufacturer,
            drugSpecification : drug.drugSpecification,
            substituteDrugs : drug.substituteDrugs
        };

        modifyDrug(modifiedDrug);
        
        closeModifyModal();
    }

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
                    <Button variant="secondary" onClick = {() => openModifyModal()}>Modfiy</Button>
                    <Button variant="danger" onClick={()=>deleteDrug(drug.id)}>Delete</Button>
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

            <Modal show ={showModifyModal} onHide={closeModifyModal}>
            <Modal.Header closeButton>
                    <Modal.Title>Modifing drug : {drug.name}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit = {(e) => submitChanges(e)}>
                        <Form.Group>
                            <Form.Label >Code : </Form.Label>
                            <Form.Control  type="text" placeholder={drug.code}
                                onChange = {(e)=>setCode(e.target.value)}
                            ></Form.Control>
                        </Form.Group>
                        {/* <Form.Group>
                            <Form.Label>Name : </Form.Label>
                            <Form.Control type="text" placeholder={drug.name}
                                onChange = {(e)=>setName(e.target.value)}
                            ></Form.Control>
                        </Form.Group> */}
                        <Form.Group>
                            <Form.Label>Manufacturer : </Form.Label>
                            <Form.Control type="text" placeholder={drug.manufacturer}
                                onChange = {(e)=>setManufacturer(e.target.value)}
                            ></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Shape :</Form.Label>
                            <Form.Control type="text" placeholder={drug.shape}
                                onChange = {(e)=>setShape(e.target.value)}
                            ></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Type : </Form.Label>
                            <Form.Control type="text" placeholder={drug.type}
                                onChange = {(e)=>setType(e.target.value)}
                            ></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Check type="checkbox" label="Requires receipt?" checked={receipt}
                                onChange={(e)=> setReceipt(e.target.checked)}
                            ></Form.Check>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Amount : </Form.Label>
                            <Form.Control type="amount" placeholder={drug.amount}
                                onChange = {(e)=>setAmount(parseInt(e.target.value))}
                            ></Form.Control>
                        </Form.Group>
                        <Button type = "primary">Save changes</Button>
                </Form>

                </Modal.Body>
            </Modal>

        </div>
     );
}
 
export default DrugDetails;