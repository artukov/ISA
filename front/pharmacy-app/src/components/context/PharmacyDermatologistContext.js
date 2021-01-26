import React, { useState, useEffect, createContext } from 'react';

const PharmacyDermatologistContext = createContext();

const PharmacyDermatologistContextProvider = (props) => {

    

    return ( 
        <PharmacyDermatologistContext>
            {props.children}
        </PharmacyDermatologistContext>
     );
}
 
export default PharmacyDermatologistContextProvider;