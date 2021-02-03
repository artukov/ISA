import React, { useState, useEffect } from 'react'
import { Card, ListGroup } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
const DermatologistInfo = () => {


    const [dermatologist, setDermatologist] = useState({});
    const [pharmacies, setPharmacies] = useState([]);
    const [startHour, setStartHour] = useState([]);
    const [hours, setHours] = useState([]);

    

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
            <ListGroup>
                {
                    pharmacies ? (
                        pharmacies.map(pharmacy =>
                            <ListGroup.Item onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } } key={pharmacy.pharamcy_id} >{ pharmacy.pharmacy_id}</ListGroup.Item>
                            )
                    ) : null
                }
            </ListGroup>
        </div>
      );
}
 
export default DermatologistInfo;