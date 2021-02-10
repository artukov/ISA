import React, { useState, useEffect } from 'react';
import { Col, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const ReservedDrugs = () => {
    const [drugs, setDrugs] = useState([]);
    const [sortNameAsc, setSortNameAsc] = useState({});
    const [sortManufacturerAsc, setSortManufacturerAsc] = useState({});
    const [sortTypeAsc, setSortTypeAsc] = useState({});
    const [sortShapeAsc, setSortShapeAsc] = useState({});

    useEffect(() => {
        const laodDrugs = async () => {
            try {
                const result = await axiosConfig.get('/patient/reservedDrugs');
                setDrugs(result.data);
            } catch (err) {
                console.log(err);
                alert(err.response.data);
           }
        }
        laodDrugs();
    }, [])

    const sortDrugName = () => {
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

    const sortDrugManufacturer = () => {
        let result = null
        if (sortManufacturerAsc) {
            result = drugs.sort((a, b) => (a.name > b.name) ? 1 : -1);
            setSortManufacturerAsc(false);
        }
        else { 
            result = drugs.sort((a, b) => (a.name < b.name) ? 1 : -1);
            setSortManufacturerAsc(true);
    }
        setDrugs([...result]);
    }

    const sortDrugType = () => {
        let result = null
        if (sortTypeAsc) {
            result = drugs.sort((a, b) => (a.name > b.name) ? 1 : -1);
            setSortTypeAsc(false);
        }
        else { 
            result = drugs.sort((a, b) => (a.name < b.name) ? 1 : -1);
            setSortTypeAsc(true);
    }
        setDrugs([...result]);
    }

    const sortDrugShape = () => {
        let result = null
        if (sortShapeAsc) {
            result = drugs.sort((a, b) => (a.name > b.name) ? 1 : -1);
            setSortShapeAsc(false);
        }
        else { 
            result = drugs.sort((a, b) => (a.name < b.name) ? 1 : -1);
            setSortShapeAsc(true);
    }
        setDrugs([...result]);
    }

    return (  
        <div>
            <ListGroup>
                <ListGroup.Item>
                    <Row>
                        <Col>Name</Col>
                        <Col>Manufacturer</Col>
                        <Col>Type</Col>
                        <Col>Shape</Col>
                        
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
                                    <Col onClick={() => {
                            sortDrugName();
                            } }>{drug.name}</Col>
                                    <Col onClick={() => {
                            sortDrugManufacturer();
                            } }>{drug.manufacturer}</Col>
                                    <Col onClick={() => {
                            sortDrugType();
                            } }>{drug.type}</Col>
                                    <Col onClick={() => {
                            sortDrugShape();
                            } }>{drug.shape}</Col>
                                    
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
 
export default ReservedDrugs;