import React, { useState, useEffect, createContext, useReducer } from 'react';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
// import { urlGetPharmacyDrugs } from '../../services/UrlService';
import { priceListReducer, SET_PHARMACY, INIT } from './priceListReducer';


export const PriceListContext = createContext();

const PriceListContextProvider = (props) => {

    const [state, dispatch] = useReducer(priceListReducer, {
        startDate : null,
        startTime : null,
        endDate : null,
        endTime : null,
        pharmacyID : null,
        drugs : []
    });
    
    const [latestPL, setLatestPL] = useState({});

    useEffect(() => {
        const loadLatesPriceList = async (id) =>{
            try{
                const result = await axiosConfig.get('/pricelist/findLatestPriceList/' + id);
                setLatestPL(result.data);
                // console.log('latest',latestPL);
            }
            catch(err){
                console.log(err.response);
            }
        }
        if(props.pharmacyID !== undefined){
            loadLatesPriceList(props.pharmacyID);
            dispatch({type : SET_PHARMACY, id : props.pharmacyID});
        }
       

    }, [props.pharmacyID]);

    const addNewPriceList = async () =>{
        
        // console.log(state);
        let newPricelist = {
            startDate : formatDate(state.startDate, state.startTime),
            endDate : formatDate(state.endDate,state.endTime),
            pharmacyID : props.pharmacyID,
            drugs : []
        };

        newPricelist.drugs = state.drugs.map(drug => {
            return {
                drugID : drug.id,
                name : drug.name,
                price : drug.price
            }
        })
        // console.log(newPricelist);

        try{
            await axiosConfig.post('/pricelist/new',newPricelist);
            dispatch({type : INIT});
        }
        catch(err){
            console.log(err);
        }
    }

    const checkIfDateChanged = (date,time) =>{
        if(time === undefined){
            return date
        }
        else
            return formatDate(date,time);

    }

    const modifyPriceList = async () =>{
        let newPricelist = {
            id : state.id,
            startDate : checkIfDateChanged(state.startDate, state.startTime),
            endDate : checkIfDateChanged(state.endDate,state.endTime),
            pharmacyID : props.pharmacyID,
            drugs : []
        };

        newPricelist.drugs = state.drugs.map(drug => {
            return {
                drugID : drug.id,
                name : drug.name,
                price : drug.price
            }
        });

        try{
            await axiosConfig.put('/pricelist/modify/'+ state.id,newPricelist);
            dispatch({type : INIT});
        }
        catch(err){
            console.log(err);
        }
    }

    
    
    return ( 
        <PriceListContext.Provider 
            value = {{
              latestPL,
              addNewPriceList,
              state,
              dispatch,
              pharmacyID : props.pharmacyID,
              modifyPriceList
            }}
        >
            {props.children}
        </PriceListContext.Provider>
     );
}
 
export default PriceListContextProvider;