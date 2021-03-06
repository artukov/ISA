import React, { useState, useEffect } from 'react'
import { Button, ListGroup, Form } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';

const SubstituteDrugComponent = ({ patientId, drugId }) => {
    
    const [substitute, setSubstitute] = useState([]);

    useEffect(() => {
        const loadSubstituteDrugs = async (patientId, drugId) => {
            try {
                const result = await axiosConfig.get('dermatologist/checkAllergy/' + patientId + '/' + drugId);
                setSubstitute(result.data);
            } catch (err) {
                console.log(err);
            }
        
        }
        if(patientId !== undefined)
             loadSubstituteDrugs(patientId, drugId);
    }, [patientId, drugId]);
    
    return (  
        <div>
            <ListGroup.Item>
         <Form.Label>If patient is allergic here is the list of substitute drugs</Form.Label>
            <Form.Control as="select">
                    {
                        substitute ? (
                            substitute.map(drug =>
                                <option key={drug.id} value={JSON.stringify({ id: drug.id, name: drug.name })}>
                                    {drug.name} 
                                </option>
                            )
                        ) : null}
                </Form.Control>
                </ListGroup.Item>
        </div>
    );
}
 
export default SubstituteDrugComponent;
