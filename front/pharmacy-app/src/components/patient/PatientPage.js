import React, { useState, useEffect } from 'react'
import { Tab, Tabs } from 'react-bootstrap';
import DermaVisists from './DermaVisists';
import Ereceipts from './EReceipts';
import FutureAppointments from './FutureAppointments';
import PatientInfo from './PatientInfo';
import PharmaciesList from './PharmaciesList';
import PharmacistVisits from './PharmacistVisists';

const PatientPage = () => {
    return ( 
         <Tabs  defaultActiveKey="info"   onSelect = {()=> { }}>
                    <Tab eventKey = "info" title="Patient info">
                        <PatientInfo></PatientInfo>
            </Tab>
             <Tab eventKey = "pharmacies" title="Pharmacies">
                        <PharmaciesList></PharmaciesList>
            </Tab>
             <Tab eventKey = "dermaVisits" title="Dermatologist visits">
                        <DermaVisists></DermaVisists>
            </Tab>
            <Tab eventKey="pharmacistConsultations" title="Pharmacist visits">
                <PharmacistVisits></PharmacistVisits>
                        
                </Tab>
                 <Tab eventKey = "appointments" title="Appointments">
                        <FutureAppointments></FutureAppointments>
            </Tab>
             <Tab eventKey = "eReceipts" title="eReceipts">
                        <Ereceipts></Ereceipts>
            </Tab>
            <Tab eventKey = "reservedDrugs" title="reservedDrugs">
                        
            </Tab>
            <Tab eventKey = "penalties" title="Penalties">
                        
            </Tab>
            <Tab eventKey = "drugsFromEReceipt" title="History od drugs from eReceipt">
                        
            </Tab>
            <Tab eventKey = "promotions" title="List of pharamcies and promotions">
                        
            </Tab>
            <Tab eventKey = "complaint" title="New Complaint">
                        
            </Tab>
            <Tab eventKey = "acceptDrug" title="Take the drug">
                        
            </Tab>
            <Tab eventKey = "newConsultation" title="Book Consultation">
                        
            </Tab>
            <Tab eventKey = "newExamination" title="Book Examination">
                        
            </Tab>
                
                
                    
                 </Tabs>
     );
}
 
export default PatientPage;