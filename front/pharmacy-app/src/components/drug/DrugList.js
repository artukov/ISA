import React, { useState, useEffect } from 'react'
import { Button, Col, Form, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';


const DrugList = () => {

    const [drugs, setDrugs] = useState([]);
    const [sortNameAsc, setSortNameAsc] = useState(true);
    const [sortTypeAsc, setSortTypeAsc] = useState(true);
    const [sortManufacturerAsc, setSortManufacturerAsc] = useState(true);
    const [sortPharmaAsc, setSortPharmaAsc] = useState(true);
    const [sortPriceAsc, setSortPriceAsc] = useState(true);
    const [drugName, setDrugName] = useState({});
    const [pharmDrug, setPharmDrug] = useState([]);

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
    const sortPharmName = () => {
        let result = null
        if (sortPharmaAsc) {
            result = pharmDrug.sort((a, b) => (a.pharmacy_id > b.pharmacy_id) ? 1 : -1);
            setSortPharmaAsc(false);
        }
        else { 
            result = pharmDrug.sort((a, b) => (a.pharmacy_id < b.pharmacy_id) ? 1 : -1);
            setSortPharmaAsc(true);
    }
        setPharmDrug([...result]);
    }
    const sortDrugPrice = () => {
        let result = null
        if (sortPriceAsc) {
            result = pharmDrug.sort((a, b) => (a.price > b.price) ? 1 : -1);
            setSortPriceAsc(false);
        }
        else { 
            result = pharmDrug.sort((a, b) => (a.price < b.price) ? 1 : -1);
            setSortPriceAsc(true);
    }
        setPharmDrug([...result]);
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

    const findPharmDrug = async (name) => {
        
        try {
            const result = await axiosConfig.get('/drug/search/' + name);
            setPharmDrug(result.data);
        } catch (err) {
            console.log(err);
                alert(err.response.data);
        }
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
            <Form onSubmit={(e) => e.preventDefault()}>
                <Row>
                <Col>
                        <div>Drug Name</div>
                        <Form.Control type="text" placeholder = "Drug Name" onChange = { (e) => setDrugName(e.target.value) }></Form.Control>
                    </Col>
                </Row>
                <Button onClick={(e) => {
                    if (drugName !== '') {
                        findPharmDrug(drugName);
                    }
                }}>Find Drug</Button>
            </Form>
               <ListGroup>
             <ListGroup.Item>
                    <Row>
                        <Col>Name</Col>
                        <Col >Type</Col>
                        <Col onClick={() => {
                            sortPharmName();
                            } }>Pharmacy</Col>
                        <Col onClick={() => {
                            sortDrugPrice();
                            } }>Price</Col>
                    </Row>
                </ListGroup.Item>
                {
                    [pharmDrug] ? (
                        pharmDrug.map((drug, index) => {
                            
                            if (drug) {
                                return (
                                    <ListGroup.Item key={index} >
                                        <Row>
                                            <Col>{drug.name}</Col>
                                            <Col>{drug.type}</Col>
                                            <Col>{drug.pharmacy_id}</Col>
                                            <Col>{drug.price}</Col>
                                            <Col><Button>Show specification</Button></Col>
                                        </Row>
                                    </ListGroup.Item>
                                )
                            }
                            else return null
                        }

                            
                            // <ListGroup.Item key={patient.name}>{patient.name}</ListGroup>
                        )
                        
                    ) : null
                }
            </ListGroup>
         </div>
    );
}
 
export default DrugList;