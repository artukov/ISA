import React, { useState, useEffect } from 'react';
import { CardDeck } from 'react-bootstrap';
import { axiosConfig } from '../../../config/AxiosConfig';
import { urlGetPhramacyDermatologists } from '../../../services/UrlService';
import PharmacyDermatologistDetails from '../details/DermatologistDetails';

const PharmacyDermatologistList = ({pharmacyID}) => {

    const [dermatologists, setDermatologists] = useState([]);
    
   useEffect(() => {
    const loadDermatologists = async (id) =>{
        try{
            const result = await axiosConfig.get(urlGetPhramacyDermatologists + id);
            setDermatologists(result.data);
        }
        catch(err){
            console.log(err.response);
        }
    }


    if(pharmacyID !== undefined){
        
        loadDermatologists(pharmacyID);
    }
   
}, [pharmacyID]);

    return ( 
        <div>
            <CardDeck>
                {
                    dermatologists.length ? (
                        dermatologists.map(dermatologist => 
                                <PharmacyDermatologistDetails key={dermatologist.id} dermatologist={dermatologist}></PharmacyDermatologistDetails>
                            )
                    ) : null
                }
            </CardDeck>
        </div>
     );
}
 
export default PharmacyDermatologistList;