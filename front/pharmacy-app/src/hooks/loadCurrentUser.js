import { useState, useEffect } from 'react'
import { axiosConfig } from '../config/AxiosConfig'


function useCurrentUser(){

    const [user, setUser] = useState({})

    useEffect(() => {
        const settingUser = (user) =>{
            setUser(user);
        }

       const loadCurrentUser = async () => {
           try{
                const result = await axiosConfig.get('/user/current');
                settingUser(result.data);
           }
           catch(err){
               console.log(err);
            }
       }

       loadCurrentUser();
    }, []);

    return user;

}

export default useCurrentUser;