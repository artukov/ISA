import React, { useState, useEffect, useReducer } from 'react';
import { Form, Col, Button } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import promotionsReducer, {SET_START_DATE, SET_START_TIME,SET_PHARMACY ,SET_END_TIME, SET_END_DATE, SET_CONTENT} from './promotionsReducer';

const NewPromotionsForm = ({pharmacyID}) => {

    const [state, dispatch] = useReducer(promotionsReducer, {});

    useEffect(() => {
        dispatch({type : ''});
        if(pharmacyID !== undefined)
            dispatch({type : SET_PHARMACY, id : pharmacyID});
     
    }, [pharmacyID]);

    const addNewPromotions = async (e) =>{
        e.preventDefault();
        try{
           
            state.startDate = formatDate(state.startDate,state.startTime);
            state.endDate = formatDate(state.endDate, state.endTime);
            console.table(state);
            await axiosConfig.post('/promotions/new', state);
            alert('Successfull');
            dispatch({type : ''});
        
        }
        catch(err){
            console.log(err.response);
        }

    }


    return ( 
        <Form onSubmit={(e) =>addNewPromotions(e) }>
            <Form.Group>
                <Form.Label>Insert start date and time</Form.Label>
                <Form.Row>
                    <Col>
                        <Form.Control type="date" 
                        onChange={(e) => dispatch({type : SET_START_DATE, startDate : e.target.value})}></Form.Control>
                    </Col>
                    <Col>
                        <Form.Control type="time" 
                        onChange={(e) => dispatch({type : SET_START_TIME, startTime : e.target.value})}></Form.Control>
                    </Col>
                </Form.Row>
            </Form.Group>
            <Form.Group>
                <Form.Label>Insert end date and time</Form.Label>
                <Form.Row>
                    <Col>
                        <Form.Control type="date" 
                        onChange={(e) => dispatch({type : SET_END_DATE, endDate : e.target.value})}></Form.Control>
                    </Col>
                    <Col>
                        <Form.Control type="time" 
                        onChange={(e) => dispatch({type : SET_END_TIME, endTime : e.target.value})}></Form.Control>
                    </Col>
                </Form.Row>
            </Form.Group>
            <Form.Group>
                <Form.Label>Insert content of the promotion</Form.Label>
                <Form.Control as="textarea" row={5}  
                   onChange={(e) => dispatch({type : SET_CONTENT, content : e.target.value})}
                />
            </Form.Group>
            <Button type="submit" onClick={(e) => addNewPromotions(e)}>Save changes</Button>
        </Form>
     );
}
 
export default NewPromotionsForm;