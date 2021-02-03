import React, { useState, useEffect } from 'react'

function useCurrentDermatologist(){

    const [dermatologist, setDermatologist] = useState({})

    useEffect(() => {
        axiosConfig.post('/dermatologist/current')
        .then(res => {
            setDermatologist(res.data);
        })
    }, []);

    return dermatologist;

}
 
export default useCurrentDermatologist;