import React, {  } from 'react';


const AddressComponent = ({address}) => {

    if(address === null || address === undefined){
        return '';
    }
    else{
        return (  
            <div>
                <p> {address.street}</p>
                <p>{address.streetNumber}</p>
                <p>{address.city}</p>
                <p> {address.country}</p>
            </div>
        );
    }

    

    
}
 
export default AddressComponent;