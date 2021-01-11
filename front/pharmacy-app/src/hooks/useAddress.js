import { useEffect, useState } from "react"
import axiosConfig from '../config/AxiosConfig'; 

export const useAddress = (id) =>{

    const [address, setAddress] = useState([]);

    useEffect( () =>
        axiosConfig.get('/pharmacy/address/' + id)
        .then(res => {
            console.log('address', res.data);
            setAddress(res.data);
        })
        .catch(err => console.log(err))

    ,[id]);

    return address;

}