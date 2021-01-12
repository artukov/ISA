
import React, { useState, useEffect } from 'react';
import { usePharmaPharmacists } from '../../hooks/loadPharmacyPharmacists';
import PharmacistDetails from '../pharmacist/PharmacistsDetails';


const PharmacistList = ({pharmacyID}) => {
    
    const [pharmacists, setPharmacists] = useState([]);

    const fetchPharmacists = usePharmaPharmacists(pharmacyID)

    useEffect(() => {
        setPharmacists(fetchPharmacists);
        return () => {
            
        }
    }, [fetchPharmacists]);

    return (  
        <div>
            <h4>PharmacistList component</h4>
            { pharmacists.length ? pharmacists.map(pharmacist => 
                                    <PharmacistDetails pharmacist = {pharmacist} key={pharmacist.id}></PharmacistDetails>) 
            : <p>no pharmacists</p>
            }
        </div>
    );
}
 
export default PharmacistList;