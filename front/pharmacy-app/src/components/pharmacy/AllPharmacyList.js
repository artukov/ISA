import React, { useState, useEffect } from 'react';
import { Container, Row,Col, Button } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const AllPharmacyList = () => {
    const [pharmacies, setPharmacies] = useState([]);

    useEffect(() => {
        const loadAllPharmacies = async () =>{
            try{
                const result = await axiosConfig.get('/pharmacy/findAll');
                setPharmacies(result.data);
            }
            catch(err) {
                console.log(err.response);
            }
        }
        loadAllPharmacies();
    }, []);

    const avgRatings = (ratings) => {
        let avg = 0;
        if(ratings.length)
            avg = ratings.reduce((a, b) => a + b) / ratings.length;
        return avg;
    }

    const redirectToPharmcyPage = (id) => {
        window.location.state = id;
        window.location.href = "/pharmacy/" +id;
        
    }


    return ( 
        <div>
            <Container>
                <Row>
                    <Col>Pharmacy name</Col>
                    <Col>Pharmacy ratings</Col>
                    <Col>Pharmacy description</Col>
                </Row>
                {
                    pharmacies.length ? (
                       pharmacies.map(pharmacy => 
                            <Row key={pharmacy.id}>
                                <Col>{/* <Button variant="link" onClick={() => redirectToPharmcyPage(pharmacy.id)}> */}{pharmacy.name}{/* </Button> */}</Col>
                                <Col>{avgRatings(pharmacy.ratings)}</Col>
                                <Col>{pharmacy.description}</Col>
                            </Row>
                        )
                    ) : null
                }
            </Container>
        </div>
     );
}
 
export default AllPharmacyList;