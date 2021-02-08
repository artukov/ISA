import React, { useState, useEffect } from 'react';
import UserProfileComponent from '../user/UserProfileComponent';
import {Button} from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const NewDermatologistForm = () => {

    const [user, setUser] = useState({});

    const saveChanges = async (e) => {
        e.preventDefault();
        console.table(user);
        user.address = {};
        try{
            await axiosConfig.post('/dermatologist/new', user);
        }
        catch(err){
            console.log(err);
        }
    }

    return (  
        <div>
            <UserProfileComponent user={undefined} hideRole={true} setUser={setUser}></UserProfileComponent>
            <Button onClick={(e) => saveChanges(e)}>Save changes</Button>
        </div>
    );
}
 
export default NewDermatologistForm;