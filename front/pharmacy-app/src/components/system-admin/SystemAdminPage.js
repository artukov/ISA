import React, { useState, useEffect } from 'react';
import {Tabs , Tab}  from 'react-bootstrap';
import SystemAdminInfo from './SystemAdminInfo';
import CurrentUserContextProvider from '../context/CurrentUserContext';
import NewPharmacyForm from './NewPharmacyForm';
import NewPharmacyAdminForm from './NewPharmacyAdminForm';
import NewDermatologistForm from './NewDermatologistFrom';
// import NewSystemAdminForm from './NewSystemAdminForm';
import NewAdminSupplierForm from './NewAdminSupplierForm';
import NewDrugForm from './NewDrugForm';
import ComplaintsList from './ComplaintsList';
import AbsenceRequestContextProvider from './absence-requests/AbsenceRequestsContext';
import AbsenceRequestList from "./absence-requests/AbsenceRequestList"

const SystemAdminPage = () => {

    return ( 
        <CurrentUserContextProvider>
            <Tabs defaultActiveKey="absence-requests" onSelect={() => {}}>
                <Tab eventKey="pharmacy" title="Register pharmacy">
                    <NewPharmacyForm></NewPharmacyForm>
                </Tab>
                <Tab eventKey="pharmacyAdmin" title="Register pharmacy's admins">
                    <NewPharmacyAdminForm></NewPharmacyAdminForm>
                </Tab>
                <Tab eventKey="dermatologist" title="Register dermatologist">
                    <NewDermatologistForm></NewDermatologistForm>
                </Tab>
                <Tab eventKey="admin-supplier" title="Register system's admin or supplier">
                   <NewAdminSupplierForm></NewAdminSupplierForm>
                </Tab>
                <Tab eventKey="drug" title="Creat drug">
                    <NewDrugForm></NewDrugForm>
                </Tab>
                <Tab eventKey="complaints" title="Complaints">
                    <ComplaintsList></ComplaintsList>
                </Tab>
                <Tab eventKey="absence-requests" title="Absence requesets">
                    <AbsenceRequestContextProvider>
                        <AbsenceRequestList></AbsenceRequestList>
                    </AbsenceRequestContextProvider>
                </Tab>
                <Tab eventKey="loyalty-program" title="Loyalty program"></Tab>
                <Tab eventKey="personal-info" title="Personal info">
                    <SystemAdminInfo></SystemAdminInfo>
                </Tab>
        </Tabs>
        </CurrentUserContextProvider>
     );
}
 
export default SystemAdminPage;