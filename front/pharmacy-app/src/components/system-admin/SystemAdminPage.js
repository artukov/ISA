import React, { useState, useEffect } from 'react';
import {Tabs , Tab}  from 'react-bootstrap';
import SystemAdminInfo from './SystemAdminInfo';
import CurrentUserContextProvider from '../context/CurrentUserContext';
import NewPharmacyForm from './NewPharmacyForm';

const SystemAdminPage = () => {

    return ( 
        <CurrentUserContextProvider>
            <Tabs defaultActiveKey="pharmacy" onSelect={() => {}}>
                <Tab eventKey="pharmacy" title="Register pharmacy">
                    <NewPharmacyForm></NewPharmacyForm>
                </Tab>
                <Tab eventKey="pharmacyAdmin" title="Register pharmacy's admins"></Tab>
                <Tab eventKey="dermatologist" title="Register dermatologist"></Tab>
                <Tab eventKey="admin" title="Register system's admins"></Tab>
                <Tab eventKey="drug" title="Creat drug"></Tab>
                <Tab eventKey="complaints" title="Complaints"></Tab>
                <Tab eventKey="loyalty-program" title="Loyalty program"></Tab>
                <Tab eventKey="personal-info" title="Personal info">
                    <SystemAdminInfo></SystemAdminInfo>
                </Tab>
        </Tabs>
        </CurrentUserContextProvider>
     );
}
 
export default SystemAdminPage;