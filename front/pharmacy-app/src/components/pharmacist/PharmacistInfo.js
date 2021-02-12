import React, { useState, useEffect } from 'react'
import { Button, Card } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import useCurrentUser from '../../hooks/loadCurrentUser';
import ResetPassword from '../reset-password/ResetPasswordComponent';
import UserProfileComponent from '../user/UserProfileComponent';

const PharmacistInfo = ({pharmacyID}) => {

    const [user, setUser] = useState({});
    

    const currentUser = useCurrentUser();

    useEffect(() => {
        // console.table(currentUser);
        
    }, [currentUser]);

    const saveChanges = async (user) =>{
        user.pharmacyID = pharmacist.pharmacyID;
        console.table(currentUser);
        if(user.id === undefined)
            user  = {
                ...user,
                ...currentUser
            }
        console.table(user);

        try{
            await axiosConfig.put('/pharmacist/modify/', user);
            alert("Success");

        }
        catch(err){
            console.log(err.response);
        }
    }

    const [pharmacist, setPharmacist] = useState([]);
    const [pharmacyName, setPharmacyName] = useState('');

    useEffect(() => {
        const loadPharmacist = async () => {
            try {
                const result = await axiosConfig.get('/pharmacist/current');
                setPharmacist(result.data);
              
            } catch (err) {
                console.log(err);
          }
        }
        loadPharmacist();
        if (pharmacist.pharmacyID) {
            pharamName(pharmacist.pharmacyID);
        }
        
       
    }, [pharmacist.pharmacyID])

    const avgRating = () => {
        const sum = pharmacist.ratings.reduce((a, b) => a + b, 0);
        const avg = sum / pharmacist.ratings.length;
        return avg;
    }
    const pharamName = async (id) => {
        const result = await axiosConfig.get('/pharmacy/find/'+ id);
        setPharmacyName(result.data.name);
    }

    return (  
        <div>
            {
                pharmacist ? (
                <Card>
                    <Card.Header>
                        <Card.Title>Pharmacist details</Card.Title>
                    </Card.Header>
                    <Card.Body>
                            <Card.Text>Email : {pharmacist.email}</Card.Text>
                        <Card.Text>Firstname : {pharmacist.firstname}</Card.Text>
                        <Card.Text>Lastname : {pharmacist.lastname}</Card.Text>
                            <Card.Text>Phone number : {pharmacist.phoneNumber}</Card.Text>
                            <Card.Text>Working hours : {pharmacist.hours}</Card.Text>
                            <Card.Text>Starting hour : {pharmacist.start_hour}</Card.Text>
                            <Card.Text>Pharmacy : {pharmacyName}</Card.Text>
                        <Card.Text>Ratings : {pharmacist.ratings  ? avgRating() : 0.0}</Card.Text>
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
 
export default PharmacistInfo;