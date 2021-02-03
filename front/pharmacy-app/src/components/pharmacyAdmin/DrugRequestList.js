import React, { useState, useEffect } from 'react';
import { CardDeck, Button } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import DrugRequestDetails from './DrugRequestDetails';

const DrugRequestList = ({pharmacyID, closeComponent}) => {

    const [drugRequests, setDrugRequests] = useState([]);

    

    useEffect(() => {
       
       const loadDrugRequests = async(id) => {
           try{
               const result = await axiosConfig.get('/pharmacyadmin/drugNotInStashRequest/'+ id);
               setDrugRequests(result.data);
           }
           catch(err){
               console.log(err.response);
           }

       }
       
       if(pharmacyID !== undefined){
            loadDrugRequests(pharmacyID);
        }
    }, [pharmacyID]);



    return ( 
        <div>
            <CardDeck>
                {
                    drugRequests.length ? (
                        drugRequests.map(request => 
                            <DrugRequestDetails key={request.id} request={request}></DrugRequestDetails>
                        )
                    ) : null
                }
            </CardDeck>
            <Button variant="secondary" onClick={() => closeComponent()}>Cancel</Button>
        </div>
     );
}
 
export default DrugRequestList;