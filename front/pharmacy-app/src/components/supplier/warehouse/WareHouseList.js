import React, { useState, useEffect } from 'react';
import { axiosConfig } from '../../../config/AxiosConfig';
import { Navbar,Nav, CardDeck } from "react-bootstrap";
import DrugDetails from './DrugDetails';

const WareHouseList = ({supplierID}) => {

    const [warehouse, setWarehouse] = useState({});
    const [drugs, setDrugs] = useState([]);

    useEffect(() => {
        
        const loadWarehouse = async (id) => {
            try{
                const result = await axiosConfig.get('/supplier/warehouse/' + id);
                setWarehouse(result.data);
                const {drugsID, drugsNames, amount} = result.data;
                let loadedDrugs = [];
                for(let i = 0; i < drugsID.length; i++){
                    
                    loadedDrugs = [
                        ...loadedDrugs,
                        {
                            id : drugsID[i],
                            name : drugsNames[i],
                            amount : amount[i],
                        }
                    ]
                }
                setDrugs(loadedDrugs);
               
            }
            catch(err){
                console.log(err);
            }
        }
        if(supplierID !== undefined)
            loadWarehouse(supplierID);
     
    }, [supplierID]);


    return ( 
        <div>
            <Navbar>
                <Nav>
                   Size of the warehouse  : {warehouse.size}
                </Nav>
            </Navbar>
            <CardDeck>
            {
                drugs.length ? (
                    drugs.map(drug => 
                        <DrugDetails key={drug.id} drug={drug}></DrugDetails>
                        )
                ) : null
            }
            </CardDeck>
        </div>
     );
}
 
export default WareHouseList;