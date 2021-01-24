import React, { useState, useEffect } from 'react';
import { Button, CardDeck } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
// import { usePharmacyDrugs } from '../../hooks/loadPharmacyDrugs';
import { urlAddNewDrugToPharmacy, urlDeleteDrugFromPharmacy, urlGetPharmacyDrugs, urlModfiyDrug } from '../../services/UrlService';
import DrugDetails from '../drug/DrugDetails';
import AddDrugToPharmacy from '../pharmacyAdmin/AddDrugToPharmacy';

const DrugList = ({pharmacyID}) => {

    const [drugs, setDrugs] = useState([]);

    const [showAddForm, setShowAddForm] = useState(false);

    const openAddForm = () => setShowAddForm(true);
    const closeAddForm = () => setShowAddForm(false);

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
        <Button variant = "dark" onClick = {() => openAddForm()}>Add new drug to the pharmacy</Button>
        { showAddForm ? ( <AddDrugToPharmacy addDrugToList = {addDrug} pharmacyID = {pharmacyID} ></AddDrugToPharmacy>) : null}
        <CardDeck>
        { 
            drugs.length ? drugs.map(drug => <DrugDetails drug = {drug} key={drug.id} 
                deleteDrug = {deleteDrug}
                modifyDrug = {modifyDrug}
                ></DrugDetails>) 
            : <p>no drugs</p>
        }
        </CardDeck>
        

    </div> );
}
 
export default DrugList;