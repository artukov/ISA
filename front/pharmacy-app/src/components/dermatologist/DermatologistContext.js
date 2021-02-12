import React, { useState, useEffect, createContext,useReducer } from 'react';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlAddNewDermatologist, urlDeleteDermaPharmacy, urlGetDermaNotInPharmacy, urlGetPhramacyDermatologists } from '../../services/UrlService';
import dermatologistReducer, { ADD_DERMATOLOGIST, CLOSE_ADD_FORM, DELETE_DERMATOLOGIST, OPEN_ADD_FORM, SET_DERMATOLOGISTS, SET_DERMA_NOT_IN_PHARMACY, SET_PHARMACY } from './dermatologistReducer';

export const DermatologistContext = createContext();

const DermatologistContextProvider = (props) => {

    const [state, dispatch] = useReducer(dermatologistReducer, {
        pharmacyID : null,
        employed : [],
        unemployed : [],
        showAddForm : false
    });

    const [allEmployed, setAllEmployed] = useState([]);
    const [sortHoursOrder, setSortHoursOrder] = useState(false);
    const [sortRatingOrder, setSortRatingOrder] = useState(false);


    useEffect(() => {
        const loadDermatologists = async (id) =>{
            try{
                const result = await axiosConfig.get(urlGetPhramacyDermatologists + id);
                // console.log('dermatologists \n', resault.data);
                dispatch({type : SET_DERMATOLOGISTS, employed : result.data});
                setAllEmployed(result.data);
            }
            catch(err){
                console.log(err.response);
            }
        }


        if(props.pharmacyID !== undefined){
            dispatch({type : SET_PHARMACY, pharmacyID : props.pharmacyID});
            loadDermatologists(props.pharmacyID);
        }
       
    }, [props.pharmacyID]);

    const loadDermatologistNotInPharmacy = async (id) =>{

        try{
            const result = await axiosConfig.get(urlGetDermaNotInPharmacy + id);
            dispatch({type : SET_DERMA_NOT_IN_PHARMACY, unemployed : result.data});
        }
        catch(err){
            console.log(err);
        }

        dispatch({type : OPEN_ADD_FORM});
    }

    const addDermaToList = async (dermaID,workingHours) =>{
        try{
            // const resault =
             const result  = await axiosConfig.post(urlAddNewDermatologist + dermaID + "/" + state.pharmacyID, workingHours);
             dispatch({type : ADD_DERMATOLOGIST, dermatologist : result.data});
        }
        catch(err){
            console.log(err);
        }

        dispatch({type : CLOSE_ADD_FORM});
    }

    const deleteDermatologist = async (id) =>{
        try{
            await axiosConfig.delete(urlDeleteDermaPharmacy + id + "/" + state.pharmacyID);
            dispatch({type : DELETE_DERMATOLOGIST, id});
        }
        catch(err){
            console.log(err);
            if(err.response.status === 400)
                alert(err.response.data);
        }

    }
    const searchList = (firstname,lastname) => {
        console.table(firstname,lastname);
        if(firstname === '' && lastname === ''){
            dispatch({type : SET_DERMATOLOGISTS, employed : allEmployed});
            return;
        }
           
        if(firstname === '' || lastname === ''){
            alert('Both firstname and lastname must be inserted');
            return;
        }
        const filterEmployed = state.employed.filter(derma => derma.firstname.toUpperCase() === firstname.toUpperCase()
                                                            && derma.lastname.toUpperCase() === lastname.toUpperCase());
        dispatch({type : SET_DERMATOLOGISTS, employed : filterEmployed});
        return;
    }

    const sortByRatings = () => {
        let sortedByRatings = null;
        if(sortRatingOrder)
             sortedByRatings = state.employed.sort((a,b) => a.ratings - b.ratings);
        else
            sortedByRatings = state.employed.sort((a, b) => b.ratings - a.ratings);
        dispatch({type : SET_DERMATOLOGISTS, employed : sortedByRatings});
        setSortRatingOrder(!sortRatingOrder);
        
    }

    const sortByHours = () => {
        let sortedByHours = null;
        if(sortHoursOrder)
            sortedByHours = state.employed.sort((a, b) => a.hours - b.hours);
        else
            sortedByHours = state.employed.sort((a, b) => b.hours - a.hours);
        dispatch({type : SET_DERMATOLOGISTS, employed : sortedByHours});
        setSortHoursOrder(!sortHoursOrder);
       
    }


    return (  
        <DermatologistContext.Provider value ={{
            state,
            dispatch,
            loadDermatologistNotInPharmacy,
            addDermaToList,
            deleteDermatologist,
            searchList,
            sortByRatings,
            sortByHours
        }}>
            {props.children}
        </DermatologistContext.Provider>
    );
}
 
export default DermatologistContextProvider;