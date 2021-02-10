import React, { useState, useEffect } from 'react'
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const PharmaciesList = () => {

    const [pharmacies, setPharmacies] = useState([]);
    const [sortNameAsc, setSortNameAsc] = useState(true);
    const [sortRatingAsc, setSortRatingAsc] = useState(true);
    const [sortCityAsc, setSortCityAsc] = useState(true);

    useEffect(() => {
        const loadPharmacies = async () => {
            try {
                const result = await axiosConfig.get('/pharmacy/findAll');
                setPharmacies(result.data);
            } catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }

        loadPharmacies();
    }, [])

    const sortPharmacyName = () => {
        let result = null
        if (sortNameAsc) {
            result = pharmacies.sort((a, b) => (a.name > b.name) ? 1 : -1);
            setSortNameAsc(false);
        }
        else { 
            result = pharmacies.sort((a, b) => (a.name < b.name) ? 1 : -1);
            setSortNameAsc(true);
    }
        setPharmacies([...result]);
    }

    const sortPharmacyRating = () => {
        let result = null
        if (sortRatingAsc) {
            result = pharmacies.sort((a, b) => (a.ratings > b.ratings) ? 1 : -1);
            setSortRatingAsc(false);
        }
        else { 
            result = pharmacies.sort((a, b) => (a.ratings < b.ratings) ? 1 : -1);
            setSortRatingAsc(true);
    }
        setPharmacies([...result]);
    }

    const sortPharmacyCity = () => {
        let result = null
        if (sortCityAsc) {
            result = pharmacies.sort((a, b) => (a.address.city > b.address.city) ? 1 : -1);
            setSortCityAsc(false);
        }
        else { 
            result = pharmacies.sort((a, b) => (a.address.city < b.address.city) ? 1 : -1);
            setSortCityAsc(true);
    }
        setPharmacies([...result]);
    }

    return (  
        <div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col onClick={() => {
                            sortPharmacyName();
                            } }>Name</Col>
                        <Col onClick={() => {
                            sortPharmacyRating();
                            } }>Rating</Col>
                        <Col onClick={() => {
                            sortPharmacyCity();
                            } }>City</Col>
                        
                    </Row>
                </ListGroup.Item>
                {
                    pharmacies ? (
                        pharmacies.map((pharmacy,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{pharmacy.name}</Col>
                                    <Col>{pharmacy.ratings}</Col>
                                    <Col>{pharmacy.address.city}</Col>
                                    
                            </Row>
                            </ListGroup.Item>
                            // <ListGroup.Item key={patient.name}>{patient.name}</ListGroup>
                        )
                        
                    ) : null
                }
                </ListGroup>
        </div>
    );
}
 
export default PharmaciesList;