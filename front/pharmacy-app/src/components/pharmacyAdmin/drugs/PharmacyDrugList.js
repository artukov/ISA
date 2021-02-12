import React, { useState, useEffect } from 'react';
import { Button, CardDeck, Row,Col } from 'react-bootstrap';
import { axiosConfig } from '../../../config/AxiosConfig';
import { urlAddNewDrugToPharmacy, urlDeleteDrugFromPharmacy, urlGetPharmacyDrugs, urlModfiyDrug } from '../../../services/UrlService';
import PharmacyDrugDetails from './PharmacyDrugDetails';
import AddDrugToPharmacy from '../../pharmacyAdmin/AddDrugToPharmacy';
import DrugRequestList from '../../pharmacyAdmin/DrugRequestList';


const PharmacyDrugList = ({pharmacyID}) => {
    const [drugs, setDrugs] = useState([]);

    const [showAddForm, setShowAddForm] = useState(false);
    const [showDrugRequests, setShowDrugRequests] = useState(false);

    const openAddForm = () => setShowAddForm(true);
    const closeAddForm = () => setShowAddForm(false);

    const openDrugRequests = () => setShowDrugRequests(true);
    const closeDrugReqests = () => setShowDrugRequests(false);

    // const fetchDrugs = usePharmacyDrugs(pharmacyID);

    useEffect(() => {
        const loadDrugs = async (id) =>{
            try{
                const resault = await axiosConfig.get(urlGetPharmacyDrugs + id);
                setDrugs(resault.data);
            }
            catch(err){
                console.log(err.response);
            }
        }

        if(pharmacyID !==undefined){
            loadDrugs(pharmacyID);
        }
        
    }, [pharmacyID]);

    const addDrug = async (drug) =>{
        // console.log('drug',drug);
        try{
            await axiosConfig.post(urlAddNewDrugToPharmacy + pharmacyID,drug);
            setDrugs([
                ...drugs,
                drug
            ]);
        }
        catch(err){
            console.log(err.response);
        }
        
        closeAddForm();
    }

    const deleteDrug = async (id) =>{
        // console.log(id);
        try{
            await axiosConfig.delete(urlDeleteDrugFromPharmacy + id + "/" + pharmacyID);
            setDrugs(
                drugs.filter(drug => drug.id !== id)
            );
        }
        catch(err){
            console.log(err.response);
            if(err.response !== undefined)
                alert(err.response.data);
        }
        closeAddForm();
    }

    const modifyDrug = async (drug) =>{
        // console.log('new drug',drug);
        closeAddForm();
        try{
            await axiosConfig.put(urlModfiyDrug + drug.id, drug);
            setDrugs(
                drugs.map(iter =>{
                    if(iter.id === drug.id)
                        return drug;
                        
                    return iter;
                })
            );
        }
        catch(err){
            console.log(err.response);
            if(err.response !== undefined)
                alert(err.response.data);
        }

        

    }


    return ( 
    <div>
        <Row>
            <Col>
                <Button variant = "dark" onClick = {() => openAddForm()}>Add new drug to the pharmacy</Button>
                { showAddForm ? ( <AddDrugToPharmacy addDrugToList = {addDrug} pharmacyID = {pharmacyID} 
                closeComponent={closeAddForm} ></AddDrugToPharmacy>) : null}
            </Col>
            <Col>
                <Button variant="warning" onClick = {() => openDrugRequests()}>Requests made for drug not in stash</Button>
                { showDrugRequests ? (<DrugRequestList pharmacyID={pharmacyID}
                 closeComponent={closeDrugReqests}></DrugRequestList>) : null}
            </Col>
        </Row>
        
       
        <CardDeck>
        { 
            drugs.length ? drugs.map(drug => <PharmacyDrugDetails drug = {drug} key={drug.id} 
                deleteDrug = {deleteDrug}
                modifyDrug = {modifyDrug}
                ></PharmacyDrugDetails>) 
            : <p>no drugs</p>
        }
        </CardDeck>
        

    </div> );
}
 
export default PharmacyDrugList;