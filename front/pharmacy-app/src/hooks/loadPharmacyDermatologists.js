import { useEffect, useState } from "react"
import axiosConfig from '../config/AxiosConfig';
import { urlGetPhramacyDermatologists} from '../services/UrlService';


export const usePharmaDerma =  (pharmacyID) =>{

    const [dermatologists, setDermatologists] = useState([]);

    useEffect(() => {
        if(pharmacyID !== undefined)
            axiosConfig.get(urlGetPhramacyDermatologists + pharmacyID)
            .then(res => {
                
                setDermatologists(res.data);
            })
            .catch(err => alert(err.response.data));
            
        
    }, [pharmacyID]);

    return dermatologists;
}