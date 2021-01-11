import React, { useState, useEffect } from 'react';
import { usePharmacy } from '../../hooks/usePharmacy';
import AddressComponent from '../address/AddressComponent';
import RatingsComponent from '../ratings/RatingsComponent';

const PharmacyComponent = () => {

    const [pharmacy, setPharmacy] = useState([]);

    const fetchPharmacy = usePharmacy(200);

    useEffect(() => {
        setPharmacy(fetchPharmacy);
        
    }, [fetchPharmacy]);

    return ( 
        <div>
            {pharmacy.name}
            {pharmacy.description}
            <AddressComponent address = {pharmacy.address} ></AddressComponent>
            <RatingsComponent ratings = {pharmacy.ratings} ></RatingsComponent>
        </div>
     );
}
 
export default PharmacyComponent;