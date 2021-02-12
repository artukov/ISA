import React, { useState, useEffect } from 'react';
import { Button, Card } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import useCurrentUser from '../../hooks/loadCurrentUser';
import ResetPassword from '../reset-password/ResetPasswordComponent';
import UserProfileComponent from '../user/UserProfileComponent';

const PatientInfo = () => {


    const [user, setUser] = useState({});
    

    const currentUser = useCurrentUser();

    useEffect(() => {
        // console.table(currentUser);
        
    }, [currentUser]);

    const saveChanges = async (user) =>{
        //user.pharmacyID = pharmacist.pharmacyID;
        console.table(currentUser);
        if(user.id === undefined)
            user  = {
                ...user,
                ...currentUser
            }
        console.table(user);

        try{
            await axiosConfig.put('/patient/modify/',user);
            alert("Success");
        }
        catch(err){
            console.log(err.response);
        }
    }

    const [patient, setPatient] = useState({});

    useEffect(() => {
        const loadPatient = async () => {
            try {
                const result = await axiosConfig.get('patient/current');
                setPatient(result.data);
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }
        loadPatient();
    }, [])

    return (  
        <div>
 {
                patient ? (
                <Card>
                    <Card.Header>
                        <Card.Title>Patient details</Card.Title>
                    </Card.Header>
                    <Card.Body>
                            <Card.Text>Firstname : {patient.firstname}</Card.Text>
                        <Card.Text>Lastname : {patient.lastname}</Card.Text>
                        <Card.Text>Email : {patient.email}</Card.Text>
                            <Card.Text>Phone number : {patient.phoneNumber}</Card.Text>
                            <Card.Text>Points : {patient.points}</Card.Text>
                            <Card.Text>Category : {patient.category}</Card.Text>
                            <Card.Text>Penalties: {patient.penalties}</Card.Text>
                    </Card.Body>
                    <Card.Footer>
                    </Card.Footer>
                </Card>

                ) : null
            }

            <div>
            <ResetPassword userOldPassword={currentUser.password}></ResetPassword>
            <UserProfileComponent user={currentUser ? currentUser : {}} setUser={setUser} hideRole={true}></UserProfileComponent>
            <Button onClick={()=> saveChanges(user) }>Save changes</Button>
        </div>
        </div>
    );
}
 
export default PatientInfo;