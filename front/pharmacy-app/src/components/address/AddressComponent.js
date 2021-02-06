import React, { useEffect } from 'react';
import { MapContainer } from '../map/MapContainer';

const AddressComponent = ({address}) => {

    useEffect(() =>{},[address]);
    
    if(address === null || address === undefined){
        return '';
    }
    else{
        return (  
            <div>
               {/*  <h4>AddressComponent</h4>
                <p>{address.street}</p>
                <p>{address.streetNumber}</p>
                <p>{address.city}</p>
                <p>{address.country}</p> */}
                <MapContainer></MapContainer>
            </div>
        );
    }

    

    
}
 
export default AddressComponent;