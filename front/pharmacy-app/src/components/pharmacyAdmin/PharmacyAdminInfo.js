import { Button } from 'react-bootstrap';
import React, { useState, useEffect } from 'react';
import useCurrentUser from '../../hooks/loadCurrentUser';
import UserProfileComponent from '../user/UserProfileComponent';
import ResetPassword from '../reset-password/ResetPasswordComponent';

const PharmacyAdminInfoComponent = () => {
    
    const [user, setUser] = useState({});
    

    const currentUser = useCurrentUser();

    useEffect(() => {
        // console.table(currentUser);
        
    }, [currentUser]);
    
    return ( 
        <div>
            <ResetPassword userOldPassword={currentUser.password}></ResetPassword>
            <UserProfileComponent user={currentUser ? currentUser : {}} setUser={setUser} hideRole={true}></UserProfileComponent>
            <Button onClick={()=> console.table(user)}>Save changes</Button>
        </div>
     );
}
 
export default PharmacyAdminInfoComponent;