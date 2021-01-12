import React, { useState, useEffect } from 'react';
import { usePharmacy } from '../../hooks/usePharmacy';
import AddressComponent from '../address/AddressComponent';
import RatingsComponent from '../ratings/RatingsComponent';
import DermatologistList from './DermatologistList';
import PharmacistList from './PharmacistList';

const PharmacyComponent = () => {

    const [pharmacy, setPharmacy] = useState([]);

    const fetchPharmacy = usePharmacy(200);

    useEffect(() => {
        setPharmacy(fetchPharmacy);
        
    }, [fetchPharmacy]);

    return ( 
        <div>
            <p>{pharmacy.name}</p>
            <p>{pharmacy.description}</p>
            <AddressComponent address = {pharmacy.address} ></AddressComponent>
            <RatingsComponent ratings = {pharmacy.ratings} ></RatingsComponent>
            <PharmacistList pharmacyID = {pharmacy.id}></PharmacistList>
            <DermatologistList pharmacyID = {pharmacy.id}></DermatologistList>
        </div>
     );
}
 
export default PharmacyComponent;