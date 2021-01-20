import { useState, useEffect } from 'react'
import { axiosConfig } from '../config/AxiosConfig'


function useCurrentUser(){

    const [user, setUser] = useState({})

    useEffect(() => {
        axiosConfig.post('/user/current')
        .then(res => {
            setUser(res.data);
        })
    }, []);

    return user;

}

export default useCurrentUser;