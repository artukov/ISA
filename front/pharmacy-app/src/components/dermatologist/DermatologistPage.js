import { Button } from 'react-bootstrap';
import React, { useState, useEffect } from 'react'
import { Tab, Tabs } from 'react-bootstrap';

const DermatologistPage = () => {

    

    return (
        <div>
            <Tabs  defaultActiveKey="info"   onSelect = {()=> { }}>
                    <Tab eventKey = "info" title="Dermatologist info">
                        
                </Tab>
                <Tab eventKey = "allClients" title="All clients">
                      
                </Tab>
                <Tab eventKey = "newExamination" title="New Examination">
                      
                </Tab>
                <Tab eventKey = "calendar" title="Calendar">
                      
                </Tab>
                <Tab eventKey = "absenceRequest" title="New Absence Request">
                      
                </Tab>
                <Tab eventKey = "newAppointment" title="New Appointment">
                      
                </Tab>
                
                    
                 </Tabs>
    </div> );
}
 
export default DermatologistPage;