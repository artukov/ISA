import React, { useState, useEffect } from 'react';
import useCurrentUser from '../../../hooks/loadCurrentUser';
import UserProfileComponent from '../../user/UserProfileComponent';
import ResetPassword from '../../reset-password/ResetPasswordComponent';
import { axiosConfig } from '../../../config/AxiosConfig';
import { Button } from 'react-bootstrap';

const SupplierInfo = () => {

    const [user, setUser] = useState({});

    const currentUser = useCurrentUser();
    
    useEffect(() => {        
    }, [currentUser]);

    const saveChanges = async (user) =>{
        if(user.id === undefined)
            user  = {
                ...user,
                ...currentUser
            }
        console.table(user);

        try{
            await axiosConfig.put('/supplier/modify', user );

        }
        catch(err){
            console.log(err.response);
        }
    }


    return (  
        <div>
            <ResetPassword userOldPassword={currentUser.password}></ResetPassword>
            <UserProfileComponent user={currentUser ? currentUser : {}} setUser={setUser} hideRole={true}></UserProfileComponent>
            <Button onClick={()=> saveChanges(user) }>Save changes</Button>
        </div>
    );
}
 
export default SupplierInfo;