import React, { useState, useEffect, createContext,useReducer } from 'react';
import { axiosConfig } from '../../config/AxiosConfig';
import { reportReducer, SET_APPOINTMENTS, SET_YEARS } from './reportReducer';

export const PharmacyReportsContext = createContext();

const initState = {
    years : [],
    appointments : {
        stats : [],
        timespam : null,
    },
    drugs : {
        stats : [],
        timespam : null
    }
};

const PharmacyReportsContextProvider = (props) => {

    const [state, dispatch] = useReducer(reportReducer, initState);

    useEffect(() => {
        const loadYears = async (id) => {
            try{
                const result = await axiosConfig.get('/calendar/allTheYears/' + id);
                dispatch({type : SET_YEARS, years : result.data});
            }
            catch(err){
                console.log(err);
            }
        }
        if(props.pharmacyID !== undefined){
            loadYears(props.pharmacyID);
        }
       
    }, [props.pharmacyID]);

    const loadAppointmentStats = async () =>{
        try{
            const result = await axiosConfig.get('/pharmacyadmin/statistics/examination/' +
                                             state.appointments.timespam + "/" + props.pharmacyID);
            // console.log(result.data);
            dispatch({type : SET_APPOINTMENTS, appointments : result.data});
        }
        catch(err){
            console.log(err);
        }
    }


    return ( 
        <PharmacyReportsContext.Provider
            value ={{
                state,
                dispatch,
                loadAppointmentStats
            }}
        >
            {props.children}
        </PharmacyReportsContext.Provider>
     );
}
 
export default PharmacyReportsContextProvider;