
import React, { useState, useEffect, useContext } from 'react';
import { Card, ListGroup, Row, Col, Button, Modal, Form } from 'react-bootstrap';
import formatDate from '../../../config/DateFormatConfig';
import { IncomingOrdersContext } from './IncomingOrdersContext';

const OrderDetails = ({order}) => {
    
    const {makeAnOffer} = useContext(IncomingOrdersContext);

    const [showModal, setShowModal] = useState(false);
    const openModal = () => setShowModal(true);
    const closeModal = () => setShowModal(false);

    const [date, setDate] = useState(new Date());
    const [time, setTime] = useState(null);
    const [priceOffer, setPriceOffer] = useState(0.0);


    return ( 
        <div>
            <Card>
                <Card.Header><Card.Title>Incoming order info</Card.Title></Card.Header>
                <ListGroup>
                    <ListGroup.Item>Deadline date : {order.deadlineDate}</ListGroup.Item>
                    <ListGroup.Item>
                        <Row>
                            <Col><Card.Text>Drugs :</Card.Text> </Col>
                            {
                                order.drugsNames ? (
                                    order.drugsNames.map((drug,index) =>
                                        <Col key={index}><Card.Text>{drug}</Card.Text></Col>
                                    )
                                ) : null
                            }
                        </Row>
                        <Row>
                            <Col><Card.Text>Amount :</Card.Text> </Col>
                            {
                                order.amount ? (
                                    order.amount.map((amount, index) =>
                                        <Col key={index}><Card.Text>{amount}</Card.Text></Col>
                                    )
                                ) : null
                            }
                        </Row>
                    </ListGroup.Item>
                </ListGroup>
                <Card.Footer>
                    <Button onClick={() => {openModal()}}>Make an offer</Button>
                </Card.Footer>
            </Card>

            <Modal show={showModal} onHide = {() => closeModal()}>
                <Modal.Header closeButton>Making an offer</Modal.Header>
                <Modal.Body>
                    <ListGroup>
                        <ListGroup.Item>Deadline date : {order.deadlineDate}</ListGroup.Item>
                        <ListGroup.Item>
                            <Row>
                                <Col><Card.Text>Drugs :</Card.Text> </Col>
                                {
                                    order.drugsNames ? (
                                        order.drugsNames.map((drug,index) =>
                                            <Col key={index}><Card.Text>{drug}</Card.Text></Col>
                                        )
                                    ) : null
                                }
                            </Row>
                            <Row>
                                <Col><Card.Text>Amount :</Card.Text> </Col>
                                {
                                    order.amount ? (
                                        order.amount.map((amount, index) =>
                                            <Col key={index}><Card.Text>{amount}</Card.Text></Col>
                                        )
                                    ) : null
                                }
                            </Row>
                        </ListGroup.Item>
                    </ListGroup>
                    <Form onSubmit = {(e) => {
                        e.preventDefault();
                        closeModal();
                        makeAnOffer({
                            deliveryDate : formatDate(date,time),
                            priceOffer : priceOffer
                        });
                    }}>
                        <Form.Group>
                            <Form.Label>Enter the delivery date and time</Form.Label>
                            <Form.Row>
                                <Col>
                                    <Form.Control type="date" onChange={(e)=> setDate(e.target.value)}></Form.Control>
                                </Col>
                                <Col>
                                    <Form.Control type="time" onChange={(e) => setTime(e.target.value)}></Form.Control>
                                </Col>
                            </Form.Row>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Enter the price offer</Form.Label>
                            <Form.Control type="number" onChange={(e) => setPriceOffer(parseFloat(e.target.value))}></Form.Control>
                        </Form.Group>
                        <Button  onClick = {(e) => {
                            e.preventDefault();
                            closeModal();
                            makeAnOffer({
                                deliveryDate : formatDate(date,time),
                                priceOffer : priceOffer,
                                orderID : order.id
                            });
                    }}>Save changes</Button>
                    </Form>
                </Modal.Body>
                
            </Modal>
        </div>
     );
}
 
export default OrderDetails;