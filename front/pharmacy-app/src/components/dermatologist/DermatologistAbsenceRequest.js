import React, { useState, useEffect, useContext, useReducer } from 'react'
import absencerequestReducers, { SET_START_DATE } from './AbsenceRequestReducer';
const DermatologistAbsenceRequest = () => {

    const [state, dispatch] = useReducer(absencerequestReducers, {
        startDate: '',
        endDate: ''
    });

    const setDate = (date) => {
        dispatch({ type: SET_START_DATE, startDate: date });
    }
    
    
    return ( 
        <div>
           {state.startDate}
        </div>
     );
}
 
export default DermatologistAbsenceRequest;