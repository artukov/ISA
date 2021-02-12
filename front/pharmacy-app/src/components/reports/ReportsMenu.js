import React, { useState, useEffect, useContext } from 'react';
import {Col, Form, Navbar, Button, Row } from 'react-bootstrap';
import formatDate from '../../config/DateFormatConfig';
import { PharmacyReportsContext } from './PharmacyReportsContext';
import { SET_TIMESPAM_APPOINTMENTS, SET_TIMESPAM_DRUGS } from './reportReducer';

const ReportsMenu = () => {
    const {dispatch, loadAppointmentStats,loadDrugConsumption, setLimits, limits, getFinances} = useContext(PharmacyReportsContext);




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
                   <Form.Control type="date" 
                        onChange = {(e)=>setLimits({...limits,lowerLimit : formatDate(e.target.value,"00:00")}) } />
                   <Form.Control type="date"
                         onChange = {(e)=>setLimits({...limits,upperLimit : formatDate(e.target.value,"00:00")}) }
                   />
                   <Button onClick={()=> getFinances()}>See pharmacy finances</Button>
               </Form.Group>
            </Form>
        </Navbar>
    );
}
 
export default ReportsMenu;