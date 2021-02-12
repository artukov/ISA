import React, { useState,useEffect, useReducer } from 'react';
import { Form, Button, Row, Col } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { axiosConfig } from '../config/AxiosConfig';
import registrationReducer, { SET_FIRSTNAME, SET_ADDRESS, SET_EMAIL, SET_LASTNAME,SET_PASSWORD,SET_PHONENUMBER } from '../reducer/registrationReducer';

const RegistrationPage = () =>{

        const [state, dispatch] = useReducer(registrationReducer, {role: "UNREGISTERED"});
        const [confirmPassword, setConfirmPassword] = useState('');

        const [showPassword, setShowPassword] = useState(false);

        useEffect(() => {
            // dispatch({type : ''});
           }, [])

        const registration = async (e) => {
            e.preventDefault();
            console.table(state);

            if(confirmPassword !== state.password){
                alert('Password and confirm password must be the same');
                return;
            }

            try{
                const result = await axiosConfig.post('/auth/signup', state);
                console.log(result);
            }
            catch(err){
                console.log(err.response);
            }
            

        }

  
        return (
            <div className='login'>
                <Form onSubmit={(e) => registration(e)}>
                    <Row>
                        <Col lg='6'>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Firstname</Form.Label>
                                <Form.Control type="text" placeholder="Enter your firstname"
                                    onChange={(e) => dispatch({type : SET_FIRSTNAME, firstname : e.target.value})}
                                />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Lastname</Form.Label>
                                <Form.Control type="text" placeholder="Enter your lastname" 
                                    onChange={(e) => dispatch({type : SET_LASTNAME, lastname : e.target.value})}
                                />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Adress</Form.Label>
                                <Form.Control type="text" placeholder="Adress" />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>City</Form.Label>
                                <Form.Control type="text" placeholder="City" />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>State</Form.Label>
                                <Form.Control type="text" placeholder="State" />
                            </Form.Group>
                        </Col>
                        <Col lg='6'>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Phone</Form.Label>
                                <Form.Control type="text" placeholder="Phone" 
                                    onChange={(e) => dispatch({type : SET_PHONENUMBER, phoneNumber : e.target.value})}
                                />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Email address</Form.Label>
                                <Form.Control type="email" placeholder="Enter email"
                                    onChange={(e) => dispatch({type : SET_EMAIL, email : e.target.value})}
                                />
                            </Form.Group>

                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type={showPassword ? "text" : "password"} placeholder="Password"
                                    onChange={(e) => dispatch({type : SET_PASSWORD, password : e.target.value})}
                                />
                            </Form.Group>

                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Confirm password</Form.Label>
                                <Form.Control type={showPassword ? "text" : "password"} placeholder="Confirm password" 
                                    onChange={(e) => setConfirmPassword(e.target.value)}
                                />
                            </Form.Group>
                        </Col>
                        <Col>
                            <Form.Group>
                                <Form.Check 
                                    type="checkbox"
                                    label = "See passwords"
                                    onClick = {() => setShowPassword(!showPassword)}
                                ></Form.Check>
                            </Form.Group>
                        </Col>
                    </Row>

                    <Button variant="primary" type="submit" onClick={(e)=> registration(e)} >
                        Submit
                    </Button>

                    <p className="forgot-password text-right">
                        Already registered <Link to='/login'>log in?</Link>
                    </p>
                </Form>
            </div>
        );
    
}

export default RegistrationPage;