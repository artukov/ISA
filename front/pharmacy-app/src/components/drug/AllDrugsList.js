import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const AllDrugsList = () => {
    const [drugs, setDrugs] = useState([]);

    useEffect(() => {
        const loadAllDrugs = async () => {
            try{
                const result = await axiosConfig.get('/drug/findAll');
                setDrugs(result.data);
            }
            catch(err) {
                console.log(err.response);
            }
        }
       loadAllDrugs();
    }, [])

    return ( 
        <div>
            <Container>
                <Row>
                    <Col>Name</Col>
                    <Col>Code</Col>
                    <Col>Shape</Col>
                    <Col>Type</Col>
                </Row>
                {
                    drugs.length ? (
                        drugs.map(drug => 
                                <Row key={drug.id}>
                                    <Col>{drug.name}</Col>
                                    <Col>{drug.code}</Col>
                                    <Col>{drug.shape}</Col>
                                    <Col>{drug.type}</Col>
                                </Row>
                            )
                    ) : null
                }
            </Container>
        </div>
     );
}
 
export default AllDrugsList;