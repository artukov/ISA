import React, { useState, useEffect, createContext,useReducer } from 'react';
import { axiosConfig } from '../../config/AxiosConfig';
import { reportReducer, SET_APPOINTMENTS, SET_YEARS, SET_DRUGS } from './reportReducer';

export const PharmacyReportsContext = createContext();

const initState = {
    years : [],
    appointments : {
        stats : [],
        timespam : "MONTHLY",
    },
    drugs : {
        stats : [],
        timespam : "MONTHLY"
    }
};

const PharmacyReportsContextProvider = (props) => {

    const [state, dispatch] = useReducer(reportReducer, initState);

    const [limits, setLimits] = useState({
        lowerLimit : '',
        upperLimit : ''
    })
    const [finances, setFinances] = useState([]);

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

    const loadDrugConsumption = async () =>{
        try{
            const result = await axiosConfig.get('/drug/avgConsumption/'+state.drugs.timespam + "/" + props.pharmacyID);
            // console.log(result.data);
            dispatch({type : SET_DRUGS, drugs : result.data});
        }
        catch(err){
            console.log(err);
        }
    }

    const getFinances = async () => {
        console.log(limits);
        try{
            const result  = await axiosConfig.post('/pharmacy/finances/' + props.pharmacyID,limits);
            setFinances(result.data);
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
                loadAppointmentStats,
                loadDrugConsumption,
                setLimits,
                limits,
                getFinances,
                finances
            }}
        >
            {props.children}
        </PharmacyReportsContext.Provider>
     );
}
 
export default PharmacyReportsContextProvider;