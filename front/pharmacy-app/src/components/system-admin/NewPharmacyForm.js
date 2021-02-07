import React, { useState, useEffect, useContext } from 'react';
import {Form, Button} from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import { CurrentUserContext } from '../context/CurrentUserContext';

const NewPharmacyForm = () => {
    const {currentUser} = useContext(CurrentUserContext);

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [address, setAddress] = useState({});

    const savePharmacy = async (e) => {
        e.preventDefault();
        const pharmacy = {
            name,
            description,
            address : {
                street : 'Njegoseva',
                country : 'Bosnia and Herzegovina',
                streetNumber : '15',
                city : 'Bijeljina '
            },
            ratings : [],
            calendarID : 0
        };
        try{
            await axiosConfig.post('/pharmacy/new', pharmacy);

        }
        catch(err){
            console.log(err.response);
        }
    }

    return ( 
    <div>
        <Form>
            <Form.Group>
                <Form.Label>Insert name of the new pharmacy</Form.Label>
                <Form.Control type="text" onChange={(e) => setName(e.target.value)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Insert description of the new pharmacy</Form.Label>
                <Form.Control type="textarea" onChange={(e) => setDescription(e.target.value)} ></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Insert address of the new pharmacy</Form.Label>
                <Form.Control type="text" readOnly onChange={(e) => setAddress(e.target.value)} ></Form.Control>
            </Form.Group>
            <Button onClick={(e)=> savePharmacy(e)}>Save changes</Button>
        </Form>
    </div>  );
}
 
export default NewPharmacyForm;