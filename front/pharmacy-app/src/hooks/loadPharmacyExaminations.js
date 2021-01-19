import { useState, useEffect } from 'react'
import axiosConfig from '../config/AxiosConfig';
import {urlGetPharmacyFreeExaminations} from '../services/UrlService';

export function usePharmacyExaminations(pharmacyID){

    const [examinations, setExaminations] = useState([]);

    useEffect(() => {
        
        if(pharmacyID !== undefined){
            axiosConfig.get(urlGetPharmacyFreeExaminations + pharmacyID)
            .then(res => {
                //console.log(res.data);
                setExaminations(res.data);
            })
        }
        return () => {
        }
    }, [pharmacyID])

    return examinations;

}