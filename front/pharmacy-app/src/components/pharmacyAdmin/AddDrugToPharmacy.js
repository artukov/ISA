import React, { useState, useEffect } from 'react';
import { Button, Form } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlGetDrugsNotInPharmacy } from '../../services/UrlService';

const AddDrugToPharmacy = ({pharmacyID,addDrugToList,closeComponent}) => {

    const [drugs, setDrugs] = useState([]);
    const [drugID, setDrugID] = useState();
    const [amount, setAmount] = useState(0.0);

    useEffect(() => {
        const loadDrugs = async (id) =>{
            try{
                const resault = await axiosConfig.get(urlGetDrugsNotInPharmacy + id);
                if(resault.data.length)
                    setDrugID(resault.data[0].id);
                setDrugs(resault.data);
            }
            catch(err){
                console.log(err.response);
            }
        }

        if(pharmacyID !== undefined)
            loadDrugs(pharmacyID);
    }, [pharmacyID])


    const addNewDrug = () =>{
        //console.log(drugID);
        let drug = drugs.find(drug => drug.id === parseInt(drugID));
        drug.amount = parseInt(amount);
        addDrugToList(drug);
    }

    return ( 
    <div>
        <Form>
            <Form.Group>
                <Form.Label>Name of the drug</Form.Label>
                <Form.Control as="select" onClick = {(e) => setDrugID(e.target.value)}>
                    {
                        drugs.map(drug =>{
                            return (
                                <option value = {drug.id} key={drug.id}>{drug.name}</option> 
                            )
                        })
                    }
                </Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Amount of the new drug</Form.Label>
                <Form.Control type = "number" onChange = {(e) => setAmount(e.target.value)}></Form.Control>
            </Form.Group>
        </Form>    
        <Button onClick={() => addNewDrug(drugID)}>Add new drug</Button>
        <Button variant="secondary" onClick={()=> closeComponent()}>Cancel</Button>
    </div> );
}
 
export default AddDrugToPharmacy;