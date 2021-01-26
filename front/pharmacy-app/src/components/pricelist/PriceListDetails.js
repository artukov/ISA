import React, { useState, useEffect, useContext } from 'react';
import { Button, Card, Col, ListGroup, Modal, Row } from 'react-bootstrap';
import { PriceListContext } from './PriceListContext';
import PriceListForm from './PriceListForm';
import { SET_STATE } from './priceListReducer';

const PriceListDetails = ({priceList}) => {

    const {state,dispatch,modifyPriceList} = useContext(PriceListContext);

    const [showModal, setShowModal] = useState(false);


    return ( 
        <div>
            <Card>
                <Card.Header><Card.Title>Price list  info</Card.Title></Card.Header>
                <Card.Body>
                    <ListGroup>
                        <ListGroup.Item>Start date  : {priceList.startDate}</ListGroup.Item>
                        <ListGroup.Item>End date  : {priceList.endDate}</ListGroup.Item>
                        <ListGroup.Item>
                            {
                                priceList.drugs ? (
                                    priceList.drugs.map(temp =>{
                                        return (
                                            <Row key={temp.drugID}>
                                                <Col>Drug : {temp.name}</Col>
                                                <Col>Price : {temp.price}</Col>
                                            </Row>
                                        )
                                    })
                                ) : null
                            }
                        </ListGroup.Item>
                    </ListGroup>
                </Card.Body>
                <Card.Footer>
                    <Button variant = "warning" onClick={() => {
                        dispatch({type : SET_STATE, payload : priceList});
                        setShowModal(true);
                    }}>Modify pricelist</Button>
                </Card.Footer>
            </Card>

            <Modal show = {showModal} onHide={() => setShowModal(false)}>
                <Modal.Header closeButton><Modal.Title>Insert price list informations</Modal.Title></Modal.Header>
                <Modal.Body>
                    <PriceListForm pricelist={state} drugs={state.drugs}></PriceListForm>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick = {() => {
                        modifyPriceList();
                        setShowModal(false)
                    }}>Save changes</Button>
                </Modal.Footer>
            </Modal>

        </div>
     );
}
 
export default PriceListDetails;