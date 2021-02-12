import React, { useState, useEffect } from 'react';
import { Button, Card, Form } from 'react-bootstrap';
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
    const [allDrugs, setAllDrugs] = useState([]);
    const [selectedDrug, setSelectedDrug] = useState({});
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

        const loadDrugs = async () => {
            try {
                const result = await axiosConfig.get('drug/all');
                setAllDrugs(result.data);
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }
        loadPatient();
        loadDrugs();
    }, [])

    const addAllergy = async (drug_id) => {
        let allergy = {
            drugId: drug_id
        };
        try {
            
            await axiosConfig.put('/patient/addAllergy/'+ drug_id);
            alert("Succes");
            
        } catch (err) {
            console.log(err);
            alert(err.response.data);
        }
    }

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
            <Form>
                <Form.Label>Select drug allergies</Form.Label>
                    <Form.Control as="select" onClick = {(e) => setSelectedDrug(JSON.parse(e.target.value))}>
                            {
                                allDrugs ? (
                                    allDrugs.map(drug =>
                                        <option key={drug.id} value={JSON.stringify({ id: drug.id, name: drug.name })}>
                                            {drug.name} 
                                        </option>
                                    )
                                ) : null}
                        
                        </Form.Control>
                        
                        
                        <Button onClick={(e) => {
                            //setChoosenDrugs([...choosenDrugs, selectedDrug]);
                             addAllergy(selectedDrug.id);
                            setAllDrugs(allDrugs.filter(drug => drug.id !== selectedDrug.id));
            }}>Add drug allergy</Button>
            </Form>
            <div>
            <ResetPassword userOldPassword={currentUser.password}></ResetPassword>
            <UserProfileComponent user={currentUser ? currentUser : {}} setUser={setUser} hideRole={true}></UserProfileComponent>
            <Button onClick={()=> saveChanges(user) }>Save changes</Button>
        </div>
        </div>
    );
}
 
export default PatientInfo;