import React, { useState, useEffect, useReducer } from 'react';
import { Col, Form, Row } from 'react-bootstrap';
import userProfile from '../../reducer/userProfileReducer';

const initialState = {};

const UserProfileComponent = ({setUser,hideRole}) => {

    const [state, dispatch] = useReducer(userProfile, initialState);
    return (
        <div  onBlur = {() => setUser(state)}>
            <Form>
                <Form.Group>
                    <Row>
                        <Col><Form.Label>Email of the user</Form.Label></Col>
                        <Col><Form.Control type="email" onChange = {(e)=> dispatch({type : 'email', payload : e.target.value})} ></Form.Control></Col>
                    </Row>
                </Form.Group>
                <Form.Group>
                    <Row>
                        <Col><Form.Label>Firstname of the user</Form.Label></Col>
                        <Col><Form.Control type="text" onChange = {(e)=> dispatch({type : 'firstname', payload : e.target.value})} ></Form.Control></Col>
                    </Row>
                </Form.Group>
                <Form.Group>
                    <Row>
                        <Col><Form.Label>Lastname of the user</Form.Label></Col>
                        <Col><Form.Control type="text" onChange = {(e)=> dispatch({type : 'lastname', payload : e.target.value})} ></Form.Control></Col>
                    </Row>
                </Form.Group>
                <Form.Group>
                    <Row>
                        <Col><Form.Label>Password of the user</Form.Label></Col>
                        <Col><Form.Control type="text" onChange = {(e)=> dispatch({type : 'password', payload : e.target.value})} ></Form.Control></Col>
                
                    </Row>
            </Form.Group>
                <Form.Group>
                    <Row>
                        <Col><Form.Label>Phone number of the user</Form.Label></Col>
                        <Col><Form.Control type="text" onChange = {(e)=> dispatch({type : 'phoneNumber', payload : e.target.value})} ></Form.Control></Col>
                
                    </Row>
                </Form.Group>
                <Form.Group hidden = {hideRole}>
                    <Row>
                        <Col><Form.Label>Role of the user</Form.Label></Col>
                        <Col><Form.Control type="text" onChange = {(e)=> dispatch({type : 'role', payload : e.target.value})} ></Form.Control></Col>
                
                    </Row>
                </Form.Group>
                <Form.Group>
                    <Row>
                        <Col><Form.Label>Address of the user</Form.Label></Col>
                        <Col><Form.Control type="text" onChange = {(e)=> dispatch({type : 'address', payload : e.target.value})} ></Form.Control></Col>
                    </Row>
                </Form.Group>
                {/* <Form.Group>
                        <Col><Form.Label>Email of the user</Form.Label></Col>
                        <Col><Form.Control type="email" onChange = {(e)=> dispatch({type : 'email', payload : e.target.value})} ></Form.Control></Col>
                </Form.Group> */}
            </Form>
        </div> 
    );
}
 
export default UserProfileComponent;