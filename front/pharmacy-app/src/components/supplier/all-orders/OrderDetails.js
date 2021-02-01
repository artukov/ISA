import React, { useState, useEffect, useContext } from 'react';
import { Card, ListGroup, Button, Row, Col, Form , Modal} from 'react-bootstrap';
import formatDate from '../../../config/DateFormatConfig';
import { AllOrdersContext } from './AllOrdersContext';

const OrderDetails = ({order}) => {

    const {makeAnOffer} = useContext(AllOrdersContext);

    const [showModal, setShowModal] = useState(false);
    const openModal = () => {
        if(isModifyPossible()){
            const [deliveryDate, deliveryTime,] = order.supplierDTOS[0].deliveryDate.split(" ");
            const [day,month,year] = deliveryDate.split("-");
            setDate(year + "-" + month + "-" + day);
            const [hours, minutes ,] = deliveryTime.split(":");
            setTime(hours + ":" + minutes);
            setPriceOffer(order.supplierDTOS[0].priceOffer)
        }
        else{
            // const dateTime = new Date();
            setDate(initDate());
            setTime(initTime());
            setPriceOffer(0.0);
        }

        setShowModal(true);

        
    }
    const closeModal = () => setShowModal(false);

    // const dateTime = new Date();

    const initDate = () =>{
        const dateTime = new Date();
        const year = dateTime.getFullYear();
        const month =  dateTime.getMonth(); 
        const day = dateTime.getDay();
        // console.log(year,month,day);

        return year + "-" + month + "-" + day;
    }
    const initTime = () => {
        const dateTime = new Date();
        const hours = dateTime.getHours();
        const minutes = dateTime.getMinutes();
        return hours + ":" + minutes;
    }

    const [date, setDate] = useState(initDate());
    const [time, setTime] = useState(initTime());
    const [priceOffer, setPriceOffer] = useState(0.0);

    const calculateBgColour = () =>{
        if(order.buttonsVisibilty){
            return "light";
        }
        if(order.supplierDTOS[0].status === "ACCEPTED")
            return "success";
        if(order.supplierDTOS[0].status === "DENIED")
            return "danger";
        return "info";
    }

    const isModifyPossible = () =>{
        if(order.buttonsVisibilty)
            return false;
        if(order.supplierDTOS[0].status !== "PENDING")
            return false;
        return true;
    }

    const handleSubmit = (e) =>{
        e.preventDefault();
        closeModal();
        makeAnOffer({
            deliveryDate : formatDate(date,time),
            priceOffer : priceOffer,
            orderID : order.id
        });
     
    }

    return ( 
        <div>
            {
                order ? (<Card bg = {calculateBgColour()}>
                    <Card.Header><Card.Title>Order info</Card.Title></Card.Header>
                    <ListGroup>
                        <ListGroup.Item>Deadline date  {order.deadlineDate}</ListGroup.Item>
                            <ListGroup.Item>
                                <Row>
                                    <Col><Card.Text>Drugs </Card.Text> </Col>
                                    {
                                        order.drugsNames ? (
                                            order.drugsNames.map((drug,index) =>
                                                <Col key={index}><Card.Text>{drug}</Card.Text></Col>
                                            )
                                        ) : null
                                    }
                                </Row>
                                <Row>
                                    <Col><Card.Text>Amount </Card.Text> </Col>
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
                    {
                                !order.buttonsVisibilty ? (
                                    <ListGroup>
                                        <ListGroup.Item>Delivery date  {order.supplierDTOS[0].deliveryDate}</ListGroup.Item>
                                        <ListGroup.Item>Price offer  {order.supplierDTOS[0].priceOffer}</ListGroup.Item>
                                    </ListGroup>
                                ) : null
                    }
                    <Card.Footer>
                        {
                            order.buttonsVisibilty ? <Button onClick = {() => openModal()}>Make an offer</Button> : null
                        }
                        {
                            isModifyPossible() ? <Button variant="dark" onClick = {() => openModal()} >Modify offer</Button> : null
                        }
                    </Card.Footer>
                </Card>) : null
            }
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
                    <Form >
                        <Form.Group>
                            <Form.Label>Enter the delivery date and time</Form.Label>
                            <Form.Row>
                                <Col>
                                    <Form.Control type="date" value = {date } 
                                    onChange={(e)=> setDate(e.target.value)}></Form.Control>
                                </Col>
                                <Col>
                                    <Form.Control type="time" value = {time}
                                     onChange={(e) => setTime(e.target.value)}></Form.Control>
                                </Col>
                            </Form.Row>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Enter the price offer</Form.Label>
                            <Form.Control type="number" value = {priceOffer}
                            onChange={(e) => setPriceOffer(parseFloat(e.target.value))}></Form.Control>
                        </Form.Group>
                        <Button  onClick = {(e) => {
                            // e.preventDefault();
                            // closeModal();
                            // makeAnOffer({
                            //     deliveryDate : formatDate(date,time),
                            //     priceOffer : priceOffer,
                            //     orderID : order.id
                            // });
                            handleSubmit(e);
                    }}>Save changes</Button>
                    </Form>
                </Modal.Body>
                
            </Modal>
        </div>
     );
}
 
export default OrderDetails;

