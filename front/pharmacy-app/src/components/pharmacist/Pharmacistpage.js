import React, { useState, useEffect } from 'react'
import { Tab, Tabs } from 'react-bootstrap';

const Pharmacistpage = () => {
    return (  
        
        <Tabs  defaultActiveKey="info"   onSelect = {()=> { }}>
                    <Tab eventKey = "info" title="Pharmacist info">
                        
                </Tab>
                <Tab eventKey = "allClients" title="All clients">
                      
                </Tab>
                <Tab eventKey = "newConsultation" title="New Consultation">
                      
                </Tab>
                <Tab eventKey = "calendar" title="Calendar">
                      
                </Tab>
                <Tab eventKey = "dispenseDrugs" title="Dispense Drugs">
                      
                </Tab>
                <Tab eventKey = "newAbsenceRequest" title="New Absence Request">
                      
            </Tab>
                <Tab eventKey = "newAppointment" title="New Appointment">
                      
                </Tab>
                
                    
                 </Tabs>
     );
}
 
export default Pharmacistpage;