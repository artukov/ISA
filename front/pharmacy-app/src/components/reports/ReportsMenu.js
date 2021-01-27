import React, { useState, useEffect, useContext } from 'react';
import {Col, Form, Navbar, Button, Row } from 'react-bootstrap';
import { PharmacyReportsContext } from './PharmacyReportsContext';
import { SET_TIMESPAM_APPOINTMENTS, SET_TIMESPAM_DRUGS } from './reportReducer';

const ReportsMenu = () => {
    const {dispatch, loadAppointmentStats,loadDrugConsumption} = useContext(PharmacyReportsContext);


    return ( 
        <Navbar className="bg-light justify-content-between">
            <Form inline>
               <Form.Group>
                        <Form.Control as="select" onClick={(e) =>
                             dispatch({type : SET_TIMESPAM_APPOINTMENTS, timespam : e.target.value})
                            }>
                            <option value = "MONTHLY">MONTHLY</option>
                            <option value = "QUARTERLY">QUARTERLY</option>
                            <option value = "YEARLY">YEARLY  </option>
                        </Form.Control>
                        <Button onClick={()=> loadAppointmentStats()}>See reports for appointments</Button>
                        <Form.Control as="select" onClick={(e) =>
                            dispatch({type : SET_TIMESPAM_DRUGS, timespam : e.target.value})
                        }>
                            <option value = "MONTHLY">MONTHLY</option>
                            <option value = "QUARTERLY">QUARTERLY</option>
                            <option value = "YEARLY">YEARLY  </option>
                        </Form.Control>
                        <Button onClick={()=> loadDrugConsumption()}>See reports for drug consumption</Button>
               </Form.Group>
               <Form.Group>
                   <Form.Control type="date"></Form.Control>
                   <Form.Control type="date"></Form.Control>
                   <Button>See pharmacy finances</Button>
               </Form.Group>
            </Form>
        </Navbar>
    );
}
 
export default ReportsMenu;