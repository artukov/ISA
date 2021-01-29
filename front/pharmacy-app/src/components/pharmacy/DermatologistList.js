import React, {useState, useEffect, useContext} from 'react';
import { CardDeck } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
// import dermatologistReducer from '../../reducer/dermatologistReducer';
// import { usePharmaDerma } from '../../hooks/loadPharmacyDermatologists';
import { urlDeleteDermaPharmacy, urlGetPhramacyDermatologists } from '../../services/UrlService';
import DermatologistDetails from '../dermatologist/DermatologistDetails';
import { DermatologistContext } from '../dermatologist/DermatologistContext';


const DermatologistList = ({}) => {
    
   
    const {state} = useContext(DermatologistContext);

    
    return ( 
    <div>
        <CardDeck >
        {
            state.employed ? state.employed.map(dermatologist => {
                return (    
                    <DermatologistDetails key={dermatologist.email} dermatologist = {dermatologist} />
                )
            }) : <p>no dermatologists</p>
        }         
        </CardDeck>
        
    </div> 
    );
}
 
export default DermatologistList;