import React, { useState, useEffect, useContext } from 'react';
import { Card, Col, ListGroup, Row, Button, Accordion } from 'react-bootstrap';
import { SupplyOrderContext } from '../context/SupplyOrderContext';

const SupplyOrderDetails = ({order}) => {

    const {acceptOffer, deleteOrder} = useContext(SupplyOrderContext);

    const checkIfOfferExists = (deliveryDate, priceOffer) => {
        if(deliveryDate === null)
            return false;
        if(priceOffer === null)
            return false;
        
            return true;
    }

    const checkifStatusIsPending = (status) =>{
        if(status === "PENDING")
            return true;
        return false;
    }


    return ( 
        <div>
            <Card bg="success" text="dark">
                <Card.Header>
                    <Card.Title>Order info</Card.Title>
                </Card.Header>
                {/* <Card.Body>
                </Card.Body> */}
                <ListGroup>
                    <ListGroup.Item>Deadline date : {order.deadlineDate}</ListGroup.Item>
                    <ListGroup.Item>
                        <Row>
                            <Col>
                                <Card.Text>Drugs </Card.Text>
                            </Col>
                            {
                                order.drugs.length ? order.drugs.map(drug => 
                                        <Col key={drug}>
                                            <Card.Text>{drug}</Card.Text>
                                        </Col>
                                    ) : null //<Card.Text>No drugs</Card.Text>
                            }
                        </Row>
                        <Row>
                            <Col>
                                <Card.Text>Amount</Card.Text>
                            </Col>
                            {
                                order.amount.length ? order.amount.map((amount,index) =>
                                    <Col key={index}>
                                        <Card.Text>{amount}</Card.Text>
                                    </Col>
                                ) : null
                            }
                        </Row>
                    </ListGroup.Item>
                    {
                        order.supplierDTOS.length ? order.supplierDTOS.map(supplier => {
                            return (
                                <ListGroup.Item key={supplier.supplierID}> 
                                   <Accordion defaultActiveKey="0">
                                        <Row>
                                            <Col>
                                                Supplier : {supplier.supplierID}
                                            </Col>
                                            <Col>
                                                <Accordion.Toggle as={Button} variant="info" eventKey={supplier.supplierID}>
                                                    See offer
                                                </Accordion.Toggle>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Accordion.Collapse eventKey={supplier.supplierID}>
                                                <ListGroup>
                                                    <ListGroup.Item>Delivery date : {supplier.deliveryDate}</ListGroup.Item>
                                                    <ListGroup.Item>Price offer : {supplier.priceOffer}</ListGroup.Item> 
                                                    { 
                                                        (checkifStatusIsPending(supplier.status)) && (checkIfOfferExists(supplier.deliveryDate, supplier.priceOffer)) ? (
                                                        <Button onClick={() => acceptOffer(order, supplier.supplierID) }>
                                                            Accept offer</Button>
                                                        ) : null
                                                    }  
                                                    {
                                                        ((checkifStatusIsPending(supplier.status)) && (!checkIfOfferExists(supplier.deliveryDate, supplier.priceOffer))) ?
                                                        <Button variant="danger" onClick = {()=> deleteOrder(order.id)}>Delete supply order</Button>
                                                        : null
                                                        
                                                    }                               
                                                </ListGroup>
                                            </Accordion.Collapse>
                                        </Row>
                                   </Accordion>
                                </ListGroup.Item>
                            );
                        }) : null
                    }
                </ListGroup>


                <Card.Footer>
                {/*  */}
                </Card.Footer>
            </Card>
        </div>
     );
}
 
export default SupplyOrderDetails;

