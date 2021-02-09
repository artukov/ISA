import React, { useState, useEffect } from 'react'
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const DrugList = () => {

    const [drugs, setDrugs] = useState([]);
    const [sortNameAsc, setSortNameAsc] = useState(true);
    const [sortTypeAsc, setSortTypeAsc] = useState(true);
    const [sortManufacturerAsc, setSortManufacturerAsc] = useState(true);

    const sortDrugsName = () => {
        let result = null
        if (sortNameAsc) {
            result = drugs.sort((a, b) => (a.name > b.name) ? 1 : -1);
            setSortNameAsc(false);
        }
        else { 
            result = drugs.sort((a, b) => (a.name < b.name) ? 1 : -1);
            setSortNameAsc(true);
    }
        setDrugs([...result]);
    }

    const sortDrugsType = () => {
        let result = null
        if (sortTypeAsc) {
            result = drugs.sort((a, b) => (a.type > b.type) ? 1 : -1);
            setSortTypeAsc(false);
        }
        else { 
            result = drugs.sort((a, b) => (a.type < b.type) ? 1 : -1);
            setSortTypeAsc(true);
    }
        setDrugs([...result]);
    }

    const sortDrugsManufacturer = () => {
        let result = null
        if (sortManufacturerAsc) {
            result = drugs.sort((a, b) => (a.manufacturer > b.manufacturer) ? 1 : -1);
            setSortManufacturerAsc(false);
        }
        else { 
            result = drugs.sort((a, b) => (a.manufacturer < b.manufacturer) ? 1 : -1);
            setSortManufacturerAsc(true);
    }
        setDrugs([...result]);
    }

    useEffect(() => {
         const loadDrugs = async () => {
            try {
                const result = await axiosConfig.get('/drug/findAll');
                setDrugs(result.data);
            }
            catch (err) {
                console.log(err);
                alert(err.response.data);
            }
        }

        loadDrugs();
    }, [])
    return (  
        <div>
            <div>List of Drugs</div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col onClick={() => {
                            sortDrugsName();
                            } }>Name</Col>
                        <Col onClick={() => {
                            sortDrugsType();
                            } }>Type</Col>
                        <Col onClick={() => {
                            sortDrugsManufacturer();
                            } }>Manufacturer</Col>
                    </Row>
                </ListGroup.Item>
                {
                    drugs ? (
                        drugs.map((drug,index) =>

                            <ListGroup.Item /*onClick={() => {
                                setStartHour(pharmacy.start_hour);
                                setHours(pharmacy.hours);
                            } }*/ key={index} >
                                <Row>
                                    <Col>{drug.name}</Col>
                                    <Col>{drug.type}</Col>
                                    <Col>{ drug.manufacturer}</Col>
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
 
export default DrugList;