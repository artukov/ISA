import React, { useState, useEffect,useContext } from 'react';
import { Button } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlGetPharmacyDrugs } from '../../services/UrlService';
import { INIT } from '../supply-order/newOrderReducer';
import { PriceListContext } from './PriceListContext';
import PriceListForm from './PriceListForm';


const AddPriceList = () => {

    const {state,dispatch,addNewPriceList ,pharmacyID} = useContext(PriceListContext);

    const [showForm, setShowForm] = useState(false);
    
    const [drugs, setDrugs] = useState();

    const loadPharmacyDrugs = async (id) =>{
        try{
            const result = await axiosConfig.get(urlGetPharmacyDrugs + id);
            setDrugs(result.data);
        }
        catch(err){
            console.log(err.response);
        }
    }

    const openForm  = () => {
        dispatch({type : INIT})
        loadPharmacyDrugs(pharmacyID);
        setShowForm(true);

    }

    return ( 
        <div>
            <Button variant="primary" onClick = {() =>{ openForm()}}> Create Add price list</Button>
            {
                showForm ?  (
                <div>
                    <PriceListForm pricelist = {state} drugs = {drugs} ></PriceListForm>  
                    <Button variant ="primary" onClick={()=>{
                        addNewPriceList();
                        setShowForm(false);
                    }}> Save changes</Button>
                </div>) 
                : null
            }
           
        </div>
     );
}
 
export default AddPriceList;