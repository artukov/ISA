import React, { useState, useEffect } from 'react';
import UserProfileComponent from '../user/UserProfileComponent';
import {Button, Form} from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const NewAdminSupplierForm = () => {

    const [user, setUser] = useState({});
    const [selectedRole, setSelectedRole] = useState('');

    useEffect(() => {
        setSelectedRole("SYSTEM_ADMIN");
    },[]);

    const saveChanges = async (e) => {
        e.preventDefault();
        console.table(user);
        user.address = {};
        try{
            if(selectedRole === 'SYSTEM_ADMIN')
                await axiosConfig.post('/systemadmin/new', user);
            else 
                await axiosConfig.post('/supplier/new', user);
        }
        catch(err){
            console.log(err);
        }
    }
    return ( 
        <div>
            <UserProfileComponent user={undefined} hideRole={true} setUser={setUser}></UserProfileComponent>
            <Form>
                <Form.Group>
                    <Form.Label>Choose role for the new user</Form.Label>
                    <Form.Control as="select" onClick={(e) =>setSelectedRole(e.target.value)}>
                        <option value={'SYSTEM_ADMIN'}>SYSTEM ADMIN</option>
                        <option value={'SUPPLIER'}>SUPPLIER</option>
                    </Form.Control>
                </Form.Group>
            </Form>
            <Button onClick={(e) => saveChanges(e)}>Save changes</Button>
        </div>
     );
}
 
export default NewAdminSupplierForm;