import React, { useState, useEffect, createContext } from 'react';
import axiosConfig from '../../../config/AxiosConfig';

export const AbsenceRequestContext = createContext();

const AbsenceRequestContextProvider = (props) => {

    const [requests, setRequests] = useState([]);

    useEffect(() => {
        const loadAbsenceRequests = async () =>{
            try{
                const result = await axiosConfig.get('/absence/allDermatologistRequests/');
                setRequests(result.data);
            }
            catch(err){
                console.log(err.response);
            }
        }
   
        loadAbsenceRequests();
        
            
    }, []);

    const answerToTheRequest = async (request,answer) =>{
        console.log(request);
        request.status = answer;
        try{
            await axiosConfig.put('/absence/answerRequest/',request);
            setRequests(
                requests.filter(iter => iter.id !== request.id)
            )
        }
        catch(err){
            console.log(err.response);
        }
    }

    return ( 
        <AbsenceRequestContext.Provider value={{
            requests,
            answerToTheRequest
        }}>
            {props.children}
        </AbsenceRequestContext.Provider>
     );
}
 
export default AbsenceRequestContextProvider;