import React, { useState, useEffect } from 'react';
import { usePharmacy } from '../../hooks/usePharmacy';
import AddressComponent from '../AddressComponent';

const PharmacyComponent = () => {

    const [pharmacy, setPharmacy] = useState([]);

    const fetchPharmacy = usePharmacy(200);

    useEffect(() => {
        setPharmacy(fetchPharmacy);
        
    }, [fetchPharmacy]);

    return ( 
        <div>
            {pharmacy.name}
            <AddressComponent address = {pharmacy.address} ></AddressComponent>
        </div>
     );
}
 
export default PharmacyComponent;