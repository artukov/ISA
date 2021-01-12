import { useState, useEffect } from 'react'
import axiosConfig from '../config/AxiosConfig';
import {urlGetPharmacyDrugs} from '../services/UrlService';

export const usePharmacyDrugs = (pharmacyID) => {

    const [drugs, setDrugs] = useState([]);

    useEffect(() => {
        if(pharmacyID !== undefined){
            axiosConfig.get(urlGetPharmacyDrugs + pharmacyID)
            .then(res => {
                console.log(res.data);
                setDrugs(res.data);
            })
            .catch(err => alert(err.response.data));
        }
        return () => {
        }
    }, [pharmacyID]);

    return drugs;
    
}
