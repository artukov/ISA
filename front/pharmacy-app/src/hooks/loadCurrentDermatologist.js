import React, { useState, useEffect } from 'react'

function useCurrentDermatologist(){

    const [dermatologist, setDermatologsit] = useState({})

    useEffect(() => {
        axiosConfig.post('/dermatologist/current')
        .then(res => {
            setDermatologsit(res.data);
        })
    }, []);

    return dermatologist;

}
 
export default useCurrentDermatologist;