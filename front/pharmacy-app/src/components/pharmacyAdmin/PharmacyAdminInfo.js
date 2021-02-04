import { Button } from 'react-bootstrap';
import React, { useState, useEffect } from 'react';
import useCurrentUser from '../../hooks/loadCurrentUser';
import UserProfileComponent from '../user/UserProfileComponent';
import ResetPassword from '../reset-password/ResetPasswordComponent';
import { axiosConfig } from '../../config/AxiosConfig';

const PharmacyAdminInfoComponent = ({pharmacyID}) => {
    
    const [user, setUser] = useState({});
    

    const currentUser = useCurrentUser();

    useEffect(() => {
        // console.table(currentUser);
        
    }, [currentUser]);

    const saveChanges = async (user) =>{
        user.pharmacy_id = pharmacyID;
        console.table(user);

        try{
            await axiosConfig.put('/pharmacyadmin/modify/' + user.id, user );

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
 
export default PharmacyAdminInfoComponent;