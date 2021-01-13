import { useEffect, useState } from "react";
import axiosConfig from '../config/AxiosConfig';
import {urlGetPharmacy} from '../services/UrlService';


export const usePharmacy =  (id) =>{

    const [pharmacy, setPharmacy] = useState([]);
    useEffect( () => {
       
    axiosConfig.get(urlGetPharmacy + id)
        .then(res =>{
                //console.log(res.data);
                setPharmacy(res.data);   
    })
    .catch(err => {
        if(err.response.status === 401)
            alert(err.response.data.message) 
        else{
            alert(err.response.data)  
            
        }
             
    });
    }, [id]);

    return pharmacy;

}