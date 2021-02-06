import React, { useState, useEffect, useReducer } from 'react';
import { Form, Button } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import resetPasswordReducer , {SET_OLD_PASSWORD, SET_NEW_PASSWORD} from './resetPasswordReducer';

const ResetPasswordPage = () => {

    const [state, dispatch] = useReducer(resetPasswordReducer, {});

    const [confirmPassword, setConfirmPassword] = useState('');

    useEffect(() => {
        dispatch({type : ''});
      
    }, []);


    const handleSubmit = async (e) =>{
        e.preventDefault();
        console.table(state);
        console.log(confirmPassword);
        if(confirmPassword !== state.newPassword){
            alert("New password does not equal confirm");
            return;
        }

        try{
            await axiosConfig.post('/auth/change-password',state);
        }
        catch(err){
            console.log(err.response);
            if(err.response.status === 400)
                alert(err.response.data);
        }
           
    }

    return ( 
       <div className="login">
            <Form>
               <Form.Group>
                    <Form.Label>Enter Your old password</Form.Label>
                    <Form.Control type="password"
                        onChange={(e)=>dispatch({type : SET_OLD_PASSWORD, oldPassword : e.target.value})}
                    ></Form.Control>
               </Form.Group>
               <Form.Group>
                    <Form.Label>Enter Your new password</Form.Label>
                    <Form.Control type="password"
                        onChange={(e) => dispatch({type : SET_NEW_PASSWORD, newPassword : e.target.value})}
                    ></Form.Control>
               </Form.Group>
               <Form.Group>
                    <Form.Label>Confirm new password</Form.Label>
                    <Form.Control type="password"
                        onChange={(e) => setConfirmPassword(e.target.value)}
                    ></Form.Control>
               </Form.Group>
                <Button onClick={(e) => handleSubmit(e)}>Save changes</Button>
            </Form>
       </div>
     );
}
 
export default ResetPasswordPage;
