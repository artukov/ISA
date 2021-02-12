import React, { useState, useEffect } from 'react'
import { Button, Card, Form, ListGroup } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import useCurrentUser from '../../hooks/loadCurrentUser';
import ResetPassword from '../reset-password/ResetPasswordComponent';
import UserProfileComponent from '../user/UserProfileComponent';
import { SET_PHARMACY } from './dermatologistReducer';
const DermatologistInfo = () => {

    const [user, setUser] = useState({});
    

    const currentUser = useCurrentUser();

    useEffect(() => {
        // console.table(currentUser);
        
    }, [currentUser]);

    const saveChanges = async (user) =>{
       // user.pharmacyID = pharmacist.pharmacyID;
        console.table(currentUser);
        if(user.id === undefined)
            user  = {
                ...user,
                ...currentUser
            }
        console.table(user);

        try{
            await axiosConfig.put('/dermatologist/modify/',user);
            alert("Success");
        }
        catch(err){
            console.log(err.response);
        }
    }

    const [dermatologist, setDermatologist] = useState({});
    const [pharmacies, setPharmacies] = useState([]);
    const [startHour, setStartHour] = useState([]);
    const [hours, setHours] = useState([]);
    const [completePharmacies, setCompletePharmacies] = useState([]);

    

    useEffect(() => {
        const loadDermatologist = async () => {
            try {
                const result = await axiosConfig.get('/dermatologist/current');
                setDermatologist(result.data);
            }
            catch (err) {
                console.log(err);
            }
        }

        loadDermatologist();
       
    }, []);

    useEffect(() => {
        const loadDermatologistPharmacies = async (id) => {
            try {
                const result = await axiosConfig.get('/dermatologist/pharmacies/'+ id);
                setPharmacies(result.data);

            } catch (err) {
                console.log(err);
            }
            
        }
        const loadCompletePharmacies = async () => {
            try {
                 const result = await axiosConfig.get('/dermatologist/pharmaciesComplete');
                setPharmacies(result.data);
            } catch (err) {
                console.log(err);
            }
        }
        loadCompletePharmacies();
        if (dermatologist.id !== undefined)
            loadDermatologistPharmacies(dermatologist.id);
            
    }, [dermatologist.id])


    return (
         <div>
            {
                dermatologist ? (
                <Card>
                    <Card.Header>
                        <Card.Title>Dermatologist details</Card.Title>
                    </Card.Header>
                    <Card.Body>
                        <Card.Text>Email : dermatologist.email</Card.Text>
                        <Card.Text>Firstname : {dermatologist.firstname}</Card.Text>
                        <Card.Text>Lastname : {dermatologist.lastname}</Card.Text>
                        <Card.Text>Phone number : {dermatologist.phoneNumber}</Card.Text>
                        <Card.Text>Ratings : {dermatologist.ratings !== null ? dermatologist.ratings : 0.0}</Card.Text>
                    </Card.Body>
                    <ListGroup>
                        <ListGroup.Item action variant="info">Working hours : {hours}</ListGroup.Item>
                        <ListGroup.Item action variant="info">Starting hours : {startHour}</ListGroup.Item>
                    </ListGroup>
                    <Card.Footer>
                    </Card.Footer>
                </Card>

                ) : null
            }
            <Form>
                <div>Select pharmacy</div>
                <Form.Control as="select" onClick={(e) => {
                    setStartHour(JSON.parse(e.target.value).startHour);
                    setHours(JSON.parse(e.target.value).hours);
                }}>
                            {
                                pharmacies ? (
                                    pharmacies.map(pharmacy =>
                                        <option key={pharmacy.pharmacy_id} value={JSON.stringify({id : pharmacy.pharmacy_id, startHour : pharmacy.start_hour, hours: pharmacy.hours})}>
                                            {pharmacy.pharmaName}
                                        </option>
                                        )
                                ) : null
                            }
            </Form.Control>
            </Form>
            <div>
            <ResetPassword userOldPassword={currentUser.password}></ResetPassword>
            <UserProfileComponent user={currentUser ? currentUser : {}} setUser={setUser} hideRole={true}></UserProfileComponent>
            <Button onClick={()=> saveChanges(user) }>Save changes</Button>
        </div>
        </div>
        
      );
}
 
export default DermatologistInfo;