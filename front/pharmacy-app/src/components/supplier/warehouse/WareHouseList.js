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

    useEffect(() => {
        // const settingDrugs = () => {
        //     console.table(warehouse);
        //     console.log(warehouse.drugsID.length);
        //     for(let i = 0; i < warehouse.drugsID.length; i++){
        //         console.log(warehouse.drugsID[i],warehouse.drugsNames[i],warehouse.amount[i])
        //         setDrugs([
                    
        //             {
        //                 id : warehouse.drugsID[i],
        //                 name : warehouse.drugsNames[i],
        //                 amount : warehouse.amount[i]
        //             },
        //             ...drugs
        //         ]);
        //         console.table(drugs);
        //     }
        // }
        // settingDrugs();
   
    }, [warehouse])

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