import React, { useState, useEffect } from 'react';
import { Form, Modal, Button } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const ResetPassword = ({userOldPassword}) => {
    const [password, setPassword] = useState({
        oldPassword : '',
        newPassword : '',
    })

    useEffect(() => {
        setPassword({
            ...password,
            oldPassword : userOldPassword
        })
      
    }, [userOldPassword]);

    const [showModal, setShowModal] = useState(false);

    const openModal = () => setShowModal(true);
    const closeModal = () => setShowModal(false);

    const resetPassword = async (e) =>{
        e.preventDefault();
        console.log(password);
        try{
            const result = await axiosConfig.post('/auth/change-password', password);
            alert(result.data.result);
        }
        catch(err){
            console.log(err.response);
        }

        closeModal();

    }

    return ( 
        <div>
            <Button variant="dark" onClick={()=>{openModal()}}>Change password</Button>
            <Modal show={showModal} onHide={()=>closeModal()}>
                    <Modal.Header closeButton><Modal.Title>Reset password</Modal.Title></Modal.Header>
                    <Modal.Body>
                        <Modal.Title style={{color : 'red'}}>Warning!!!</Modal.Title>
                        <p>If the password changes, You will have to login again</p>
                        <Form>
                            <Form.Group>
                                <Form.Label>Enter old password</Form.Label>
                                <Form.Control type="text" onChange = {(e)=> setPassword({
                                    ...password,
                                    oldPassword : e.target.value
                                })}></Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Entet new password</Form.Label>
                                <Form.Control type="text"onChange = {(e)=> setPassword({
                                    ...password,
                                    newPassword : e.target.value
                                })}></Form.Control>
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={(e) => resetPassword(e)}>Save new password</Button>
                        <Button variant="secondary" onClick={()=>closeModal()}>Cancel</Button>
                    </Modal.Footer>
            </Modal>
        </div>
     );
}
 
export default ResetPassword;