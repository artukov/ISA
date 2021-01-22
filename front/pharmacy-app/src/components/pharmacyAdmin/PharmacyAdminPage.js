import React, { useState, useEffect } from 'react'
import { Tab, Tabs } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
// import PharmacyComponent from '../pharmacy/PharmacyComponent';
import PharmacyInfoComponent from './PharmacyInfoComponent';
import DermatologistList from '../pharmacy/DermatologistList';
import AddDermatologist from './AddDermatologist';


const PharmacyAdminPage = () => {
    
    const [pharmacy, setPharmacy] = useState({});

    
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


    return ( <div>
        { pharmacy ? (
            <div>
                <Tabs  defaultActiveKey="info"   onSelect = {()=> { }}>
                    <Tab eventKey = "info" title="Pharmacy info">
                        <PharmacyInfoComponent pharmacy = {pharmacy}></PharmacyInfoComponent>
                    </Tab>
                    <Tab eventKey = "report" title="Pharmacy reports"></Tab>
                    <Tab eventKey="examinations" title = "Examinations">
                    </Tab>
                    <Tab eventKey="dermatologist" title = "Dermatologists">
                        <DermatologistList pharmacyID = {pharmacy.id}></DermatologistList>
                        <AddDermatologist></AddDermatologist>
                    </Tab>
                    <Tab eventKey="pharmacists" title = "Pharmacists"></Tab>
                    <Tab eventKey="drugs" title = "Pharmacy drugs"></Tab>
                    <Tab eventKey="pricelist" title = "Pharmacy price list"></Tab>
                    <Tab eventKey = "supply" title="Supply orders"></Tab>
                    <Tab eventKey = "promotions" title="Pharmacy promotions"></Tab>
                 </Tabs>
            </div>
        ) : (<p>Loading...</p>)}
    </div> );
}
 
export default PharmacyAdminPage;

