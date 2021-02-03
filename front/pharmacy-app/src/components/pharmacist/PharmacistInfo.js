import React, { useState, useEffect } from 'react'
import { axiosConfig } from '../../config/AxiosConfig';

const PharmacistInfo = () => {

    const [pharmacist, setPharmacist] = useState({});

    useEffect(() => {
        const loadPharmacist = async () => {
            try {
                const result = await axiosConfig.get('');
                setPharmacist(result.data);
              
            } catch (err) {
                console.log(err);
          }
        }
        loadPharmacist();
    }, [])

    return (  
        <div>Pharmacist </div>
    );
}
 
export default PharmacistInfo;