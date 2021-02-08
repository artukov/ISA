import React, { useState, useEffect } from 'react';
import {Form, Button} from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import UserProfileComponent from '../user/UserProfileComponent';


const NewPharmacyAdminForm = () => {
    const [user, setUser] = useState();
    const [pharmacies, setPharmacies] = useState(undefined);
    const [selectedPharmacy, setSelectedPharmacy] = useState({})

    useEffect(() => {
        const loadAllPharmacies = async () =>{
            try{
                const result = await axiosConfig.get('/pharmacy/findAll');
                setPharmacies(result.data);
                setSelectedPharmacy(result.data[0].id);
            }
            catch(err){
                console.log(err.reponse);
            }
        }

        loadAllPharmacies();
    }, []);
    

    const saveChanges = async (e) => {
        e.preventDefault();
        // console.log(selectedPharmacy);
        user.pharmacy_id = selectedPharmacy;
        user.address_id = 200;
        console.table(user);
        try{
            await axiosConfig.post('/pharmacyadmin/new', user);
        }
        catch(err){
            console.log(err.responese);
        }
    }

    return (
        <div>
            <UserProfileComponent setUser={setUser} hideRole={true} user={undefined}></UserProfileComponent>
            <Form>
                <Form.Group>
                    <Form.Label>Choose pharmacy</Form.Label>
                    <Form.Control as="select" onClick={(e) => {setSelectedPharmacy(e.target.value)}}>
                        {
                            pharmacies ?  
                                pharmacies.map(pharmacy => 
                                        <option key={pharmacy.id} value={pharmacy.id}>{pharmacy.name}</option>
                                    )
                            : <option></option>
                        }
                    </Form.Control>
                </Form.Group>
                <Button onClick={(e) => saveChanges(e)}>Save changes</Button>
            </Form>
        </div>
     );
}
 
export default NewPharmacyAdminForm;