import { Button } from 'react-bootstrap';
import React, { useState, useEffect } from 'react'
import { Tab, Tabs } from 'react-bootstrap';
import DermatologistInfo from './DermatologistInfo';
import DermatologistDetails from './DermatologistDetails';
import PatientList from './PatientList';
import DermatologistAbsenceRequest from './DermatologistAbsenceRequest';
import NewAppointment from './NewAppointment';

const DermatologistPage = () => {

    

    return (
        <div>
            <Tabs  defaultActiveKey="info"   onSelect = {()=> { }}>
                    <Tab eventKey = "info" title="Dermatologist info">
                        <DermatologistInfo></DermatologistInfo>
                </Tab>
                <Tab eventKey = "allClients" title="All clients">
                      <PatientList></PatientList>
                </Tab>
                <Tab eventKey = "newExamination" title="New Examination">
                      
                </Tab>
                <Tab eventKey = "calendar" title="Calendar">
                      
                </Tab>
                <Tab eventKey = "absenceRequest" title="New Absence Request">
                      <DermatologistAbsenceRequest></DermatologistAbsenceRequest>
                </Tab>
                <Tab eventKey = "newAppointment" title="New Appointment">
                      <NewAppointment></NewAppointment>
                </Tab>
                
                    
                 </Tabs>
    </div> );
}
 
export default DermatologistPage;
