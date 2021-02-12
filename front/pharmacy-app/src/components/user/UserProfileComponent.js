import React, { useState, useEffect, useReducer } from 'react';
import { Col, Form, Row } from 'react-bootstrap';
import userProfile from '../../reducer/userProfileReducer';
import AddressComponent from '../address/AddressComponent';

let initialState = {};

const UserProfileComponent = ({ setUser, hideRole, user }) => {
    const [address, setAddress] = useState({});

    useEffect(() => {
        const initState = () =>{
            if(user !== undefined)
                dispatch({type : 'init', payload :  user});
        }
        initState();
    }, [user]);

    const [state, dispatch] = useReducer(userProfile, initialState);
    return (
        <div  onBlur = {() => setUser(state)}>
            <Form>
                <Form.Group>
                    <Row>
                        <Col><Form.Label>Email of the user</Form.Label></Col>
                        <Col><Form.Control type="email" placeholder={state.email ? state.email : ''} readOnly ={user ? true : false}
                        onChange = {(e)=> dispatch({type : 'email', payload : e.target.value})} ></Form.Control></Col>
                    </Row>
                </Form.Group>
                <Form.Group>
                    <Row>
                        <Col><Form.Label>Firstname of the user</Form.Label></Col>
                        <Col><Form.Control type="text" placeholder={state.firstname ? state.firstname : ''}
                        onChange = {(e)=> dispatch({type : 'firstname', payload : e.target.value})} ></Form.Control></Col>
                    </Row>
                </Form.Group>
                <Form.Group>
                    <Row>
                        <Col><Form.Label>Lastname of the user</Form.Label></Col>
                        <Col><Form.Control type="text" placeholder={state.lastname ? state.lastname : ''} 
                        onChange = {(e)=> dispatch({type : 'lastname', payload : e.target.value})} ></Form.Control></Col>
                    </Row>
                </Form.Group>
                <Form.Group>
                    <Row>
                        <Col><Form.Label>Phone number of the user</Form.Label></Col>
                        <Col><Form.Control type="text" placeholder={state.phoneNumber ? state.phoneNumber : ''}
                         onChange = {(e)=> dispatch({type : 'phoneNumber', payload : e.target.value})} ></Form.Control></Col>
                
                    </Row>
                </Form.Group>
                <Form.Group hidden = {user ? true : false}>
                    <Row>
                        <Col><Form.Label>Password of the user</Form.Label></Col>
                        <Col><Form.Control type="password"
                        onChange = {(e)=> dispatch({type : 'password', payload : e.target.value})} ></Form.Control></Col>
                
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
                        {/* <Col><Form.Control type="text" onChange = {(e)=> dispatch({type : 'address', payload : e.target.value})} ></Form.Control></Col> */}
                        <AddressComponent dispatch={dispatch} setAddress={ setAddress} address={state.address}></AddressComponent>
                    </Row>
                </Form.Group>
          
            </Form> 
        </div> 
    );
}
 
export default UserProfileComponent;