import React, { useState, useEffect } from 'react'
import { Tab, Tabs } from 'react-bootstrap';
import PharmacistInfo from '../pharmacist/PharmacistInfo';
import NewAppointment from './NewAppointmentP';
import NewConsultation from './NewConsultation';
import PatientListP from './PatientListP';
import PharmacistAbsenceRequest from './PharmacistAbsenceRequest';

const Pharmacistpage = () => {
    return (  
        
        <Tabs  defaultActiveKey="info"   onSelect = {()=> { }}>
                    <Tab eventKey = "info" title="Pharmacist info">
                        <PharmacistInfo></PharmacistInfo>
                </Tab>
                <Tab eventKey = "allClients" title="All clients">
                      <PatientListP></PatientListP>
                </Tab>
                <Tab eventKey = "newConsultation" title="New Consultation">
                      <NewConsultation></NewConsultation>
                </Tab>
                <Tab eventKey = "calendar" title="Calendar">
                      
                </Tab>
                <Tab eventKey = "dispenseDrugs" title="Dispense Drugs">
                      
                </Tab>
                <Tab eventKey = "newAbsenceRequest" title="New Absence Request">
                      <PharmacistAbsenceRequest></PharmacistAbsenceRequest>
            </Tab>
                <Tab eventKey = "newAppointment" title="New Appointment">
                      <NewAppointment></NewAppointment>
                </Tab>
                
                    
                 </Tabs>
     );
}
 
export default Pharmacistpage;