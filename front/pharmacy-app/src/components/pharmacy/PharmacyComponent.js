import React, { useState, useEffect } from 'react';
import { usePharmacy } from '../../hooks/usePharmacy';
//import usePharmacy from '../../hooks/usePharmacy';
//import loadPharmacy from '../../services/LoadPharmacy';


const PharmacyComponent = () => {

    const [pharmacy, setPharmacy] = useState([]);

    const fetchPharmacy = usePharmacy(200);

    useEffect(() => {
        setPharmacy(fetchPharmacy);
        
    }, [fetchPharmacy]);

    return ( 
        <div>
            {pharmacy.name}
        </div>
     );
}
 
export default PharmacyComponent;