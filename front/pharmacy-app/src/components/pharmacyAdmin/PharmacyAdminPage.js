import React, { useState, useEffect } from 'react'
import { Tab, Tabs } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
// import PharmacyComponent from '../pharmacy/PharmacyComponent';
import PharmacyInfoComponent from './PharmacyInfoComponent';
import DermatologistList from '../pharmacy/DermatologistList';
import AddDermatologist from './AddDermatologist';
import DrugList from '../pharmacy/DrugList';
import PharmacistList from '../pharmacy/PharmacistList';
import SupplyOrderList from '../supply-order/SupplyOrderList';
import SupplyOrderContextProvider from '../context/SupplyOrderContext';
import SupplyOrderMenu from '../supply-order/SupplyOrderMenu';
import NewSupplyOrder from '../supply-order/NewSupplyOrder';
import PriceListContextProvider from '../pricelist/PriceListContext';
import AddPriceList from '../pricelist/AddPriceList';
import LatestPriceList from '../pricelist/LatestPriceList';
import PharmacyReportsContextProvider from '../reports/PharmacyReportsContext';
import ReportsMenu from '../reports/ReportsMenu';
import ReportsDetails from '../reports/ReportDetails';
import DermatologistContextProvider from '../dermatologist/DermatologistContext';
import AbsenceRequestContextProvider from '../absence-request/AbsenceRequestContext';
import AbsenceRequestsList from '../absence-request/AbsenceRequestList';
import PharmacyAdminInfoComponent from './PharmacyAdminInfo';



const PharmacyAdminPage = () => {
    
    const [pharmacy, setPharmacy] = useState({});
    const [reload, setReload] = useState(false);
    
    useEffect(() => {
        const loadPharmacy = async () => {
            try{
                const resault = await axiosConfig.get('/pharmacyadmin/findPharmacy');
                setPharmacy(resault.data);

            }
            catch(err){
                if(err.response.status === 401){
                    alert('Login is needed \n' + err.response.data.message);
                    console.log(err.response);
                    //refreshToken();
                }
                    
                else
                    alert(err.response.data.message);
            }
            
        };
        loadPharmacy();
    }, []);

    const addedDermatologist = () =>{ setReload(!reload)}

    return ( <div>
        { pharmacy ? (
            <div>
                <Tabs  defaultActiveKey="personal-info"   onSelect = {()=> { }}>
                    <Tab eventKey = "info" title="Pharmacy info">
                        <PharmacyInfoComponent pharmacy = {pharmacy}></PharmacyInfoComponent>
                    </Tab>
                    <Tab eventKey = "report" title="Pharmacy reports">
                        <PharmacyReportsContextProvider pharmacyID = {pharmacy.id}>
                            <ReportsMenu></ReportsMenu>
                            <ReportsDetails></ReportsDetails>
                        </PharmacyReportsContextProvider>
                      
                    </Tab>
                    <Tab eventKey="examinations" title = "Examinations">

                    </Tab>
                    <Tab eventKey="dermatologist" title = "Dermatologists">

                        <DermatologistContextProvider pharmacyID = {pharmacy.id}>
                            <AddDermatologist pharmacyID = {pharmacy.id} invokeChange = {addedDermatologist}></AddDermatologist>
                            <DermatologistList pharmacyID = {pharmacy.id} reload = {reload}></DermatologistList>
                        </DermatologistContextProvider>
                       
                    </Tab>
                    <Tab eventKey="pharmacists" title = "Pharmacists">
                        <PharmacistList pharmacyID = {pharmacy.id}></PharmacistList>
                    </Tab>
                    <Tab eventKey="drugs" title = "Pharmacy drugs">
                        <DrugList pharmacyID = {pharmacy.id}></DrugList>
                    </Tab>
                    <Tab eventKey="pricelist" title = "Pharmacy price list">
                        <PriceListContextProvider pharmacyID = {pharmacy.id}>
                            <AddPriceList></AddPriceList>
                            <LatestPriceList></LatestPriceList>
                        </PriceListContextProvider>
                    </Tab>
                    <Tab eventKey = "supply" title="Supply orders">
                        <SupplyOrderContextProvider pharmacyID = {pharmacy.id}>
                            <SupplyOrderMenu></SupplyOrderMenu>
                            <NewSupplyOrder></NewSupplyOrder>
                            <SupplyOrderList ></SupplyOrderList>
                        </SupplyOrderContextProvider>
                        
                    </Tab>
                    <Tab eventKey = "promotions" title="Pharmacy promotions"></Tab>
                    <Tab eventKey = "absence_requests" title="Absence requests">
                        <AbsenceRequestContextProvider pharmacyID = {pharmacy.id}>
                            <AbsenceRequestsList></AbsenceRequestsList>
                        </AbsenceRequestContextProvider>
                    </Tab>
                    <Tab eventKey = "personal-info" title="Personal info">
                            <PharmacyAdminInfoComponent pharmacyID={pharmacy.id}></PharmacyAdminInfoComponent>
                    </Tab>
                 </Tabs>
            </div>
        ) : (<p>Loading...</p>)}
    </div> );
}
 
export default PharmacyAdminPage;

