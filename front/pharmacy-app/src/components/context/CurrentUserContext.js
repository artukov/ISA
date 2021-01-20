import React, { createContext,useState, useEffect } from 'react'
import useCurrentUser from '../../hooks/loadCurrentUser';


export const CurrentUserContext = createContext();

const CurrentUserContextProvider = (props) => {

    const [currentUser, setCurrentUser] = useState({});

    const fetchUser = useCurrentUser();

    useEffect(() => {
        setCurrentUser(fetchUser);
        console.log('context', currentUser);
        return () => {
        }
    }, [fetchUser]);

    return ( 
        <CurrentUserContext.Provider value = {{currentUser}}>
            {props.children}
        </CurrentUserContext.Provider>
     );
}
 
export default CurrentUserContextProvider;
