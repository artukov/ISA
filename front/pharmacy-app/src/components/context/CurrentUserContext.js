import React, { createContext,useState, useEffect } from 'react'
import useCurrentUser from '../../hooks/loadCurrentUser';


export const CurrentUserContext = createContext();

const CurrentUserContextProvider = (props) => {

    const currentUser = useCurrentUser();

    useEffect(() => {
       
    }, [currentUser]);

    return ( 
        <CurrentUserContext.Provider value = {{currentUser}}>
            {props.children}
        </CurrentUserContext.Provider>
     );
}
 
export default CurrentUserContextProvider;
