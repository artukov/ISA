import React, { useState, useEffect, useContext } from 'react';
import { Button, Col, Form, ListGroup, Row } from 'react-bootstrap';
import { PriceListContext } from './PriceListContext';
import { SET_START_DATE, SET_START_TIME,SET_END_DATE ,SET_END_TIME, SET_DRUGS } from './priceListReducer';

const PriceListForm = ({pricelist, drugs}) => {
    
    const {state,dispatch} = useContext(PriceListContext);

    const [selectedDrug, setSelectedDrug] = useState({});
    const [price, setPrice] = useState();

    useEffect(() => {
        if(drugs !== undefined){
            setSelectedDrug({
                id : drugs[0].id,
                name : drugs[0].name
            });
        }

    },[drugs]);
    
    
    return ( 
        <Form onSubmit = {(e) => e.preventDefault()}>
            <Form.Group>
                <Form.Label>Enter the start date and time</Form.Label>
                <Row>
                    <Col>
                        <Form.Control type = "date" 
                            onChange = {(e) => dispatch({type : SET_START_DATE, date : e.target.value})}>

                        </Form.Control>
                    </Col>
                    <Col>
                        <Form.Control type = "time" 
                             onChange = {(e) => dispatch({type : SET_START_TIME, time : e.target.value})}
                            ></Form.Control>
                    </Col>
                </Row>
            </Form.Group>
            <Form.Group>
                <Form.Label>Enter the end date and time</Form.Label>
                <Row>
                    <Col>
                        <Form.Control type = "date"
                            onChange = {(e) => dispatch({type : SET_END_DATE, date : e.target.value})}    
                        ></Form.Control>
                    </Col>
                    <Col>
                        <Form.Control type = "time"
                            onChange = {(e) => dispatch({type : SET_END_TIME, time : e.target.value})}
                        ></Form.Control>
                    </Col>
                </Row>
            </Form.Group>
            <Form.Group>
                <Form.Label>Choose pharmacy drugs and set the price</Form.Label>
                <Row>
                    <Col>
                        <Form.Control as="select" onClick = {(e) => setSelectedDrug(JSON.parse(e.target.value))}>
                            {
                                drugs ? (
                                    drugs.map(drug =>
                                        <option key={drug.id} value={JSON.stringify({id : drug.id, name : drug.name})}>
                                            {drug.name}
                                        </option>
                                        )
                                ) : null
                            }
                        </Form.Control>
                    </Col>
                    <Col>
                        <Form.Control type="number" onChange = {(e) => setPrice(parseFloat(e.target.value))}></Form.Control>
                    </Col>
                    <Col>
                        <Button variant = "dark"
                            onClick = {() => {
                                dispatch({type : SET_DRUGS, payload : {id : selectedDrug.id, name : selectedDrug.name, price : price}
                                });
                                drugs.filter(drug => drug.id !== selectedDrug.id);
                            }}
                        >Save drug price</Button>
                    </Col>
                </Row>
            </Form.Group>
            <Form.Group>
                <Form.Label>Drug and the price</Form.Label>
                <ListGroup>
                    {
                        state.drugs ? (
                            state.drugs.map(drug =>
                                <ListGroup.Item key={drug.id}>
                                    <Row>
                                        <Col>Drug : {drug.name}</Col>
                                        <Col>Price : {drug.price}</Col>
                                    </Row>
                                </ListGroup.Item>
                            )
                        ) : null
                    }

                </ListGroup>
            </Form.Group>
           {/*  <Button variant ="primary">Save changes</Button> */}
        </Form>


     );
}
 
export default PriceListForm;