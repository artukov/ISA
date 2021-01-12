import  { useState, useEffect } from 'react';
import axiosConfig from '../config/AxiosConfig';
import {urlGetPharmacyPharmacists} from '../services/UrlService';

export const usePharmaPharmacists = (pharmacyID) =>{
    
    const [pharmacists, setPharmacists] = useState([]);

    useEffect(() => {
        if(pharmacyID !== undefined)
            axiosConfig.get(urlGetPharmacyPharmacists + pharmacyID)
            .then(res =>{
                
                setPharmacists(res.data);
            })
            .catch(err => alert(err.response.data));
        
    }, [pharmacyID])

    return pharmacists;

}