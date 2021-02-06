
import React, { useState, useEffect } from 'react';
import useCurrentUser from '../../hooks/loadCurrentUser';
import ResetPassword from '../reset-password/ResetPasswordComponent';
import UserProfileComponent from '../user/UserProfileComponent';
import { Button } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const SystemAdminInfo = () => {

    const [user, setUser] = useState({});
    

    const currentUser = useCurrentUser();

    const saveChanges = async (user) => {
        console.table(user);
        try{
            await axiosConfig.put('/systemadmin/modify/' + user.id, user);
        }
        catch(err){
            console.log(err.response);
        }

    }

    useEffect(() => {
    }, [currentUser]);

    return ( 
        <div>
            <ResetPassword userOldPassword={currentUser.password}></ResetPassword>
            <UserProfileComponent user={currentUser ? currentUser : {}} setUser={setUser} hideRole={true}></UserProfileComponent>
            <Button onClick={()=> saveChanges(user) }>Save changes</Button>
        </div>
     );
}
 
export default SystemAdminInfo;