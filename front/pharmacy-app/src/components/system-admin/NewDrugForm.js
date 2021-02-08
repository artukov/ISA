import React, { useState, useEffect, useReducer } from 'react';
import {Form,Button} from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import drugReducer, {SET_NAME,SET_CODE,SET_COMPOSITION,SET_MANUFACTURER,
    SET_RECEIPT,SET_RECOMMENDED_CONSUMPTION,SET_SHAPE,SET_SIDE_EFFECTS,SET_SUBSTITUTE_DRUGS,SET_TYPE} from "./reducer/drugReducer";

const NewDrugForm = () => {

    const [drugs, setDrugs] = useState([]);
    const [shapes, setShapes] = useState([])

    const [state, dispatch] = useReducer(drugReducer, {
        drugSpecification : {},
        substituteDrugs : [],
    });

    useEffect(() => {
        const loadAllDrugs = async () => {
            try{
                const result = await axiosConfig.get('/drug/findAll');
                setDrugs(result.data);
            }
            catch(err){
                console.log(err.response);
            }
        }
        const loadDrugShapes = async () => {
            try{
                const result = await axiosConfig.get('/drug/shapes');
                setShapes(result.data);
            }
            catch(err){
                console.log(err.respnse);
            } 
        }
        loadAllDrugs();
        loadDrugShapes();
        dispatch({type : ''});
    }, []);

    const saveDrug = async (e) => {
        e.preventDefault();
        console.table(state);
        try{
            await axiosConfig.post('/drug/new',state);
        }
        catch(err){
            console.log(err.response);
        }
    }

    return ( 
        <div>
            <Form>
                <Form.Group>
                    <Form.Label>Enter name of the drug</Form.Label>
                    <Form.Control type="text"
                        onChange = {(e) => dispatch({type : SET_NAME , name : e.target.value})}
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Enter code of the drug</Form.Label>
                    <Form.Control type="text"
                        onChange = {(e) => dispatch({type : SET_CODE , code : e.target.value})}
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Enter type of the drug</Form.Label>
                    <Form.Control type="text" 
                        onChange={(e)=> dispatch({type : SET_TYPE, drugType : e.target.value}) }>

                    </Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Enter shape of the drug</Form.Label>
                    <Form.Control as="select"  onClick={(e) => dispatch({type : SET_SHAPE, shape : e.target.value})}>
                        {
                            shapes.length ? (
                                shapes.map(shape => 
                                        <option key={shape} value={shape}>{shape}</option>
                                    )
                            ) 
                            : <option></option>
                        }
                    </Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Enter manifacturer of the drug</Form.Label>
                    <Form.Control type="text"
                        onChange = {(e) => dispatch({type : SET_MANUFACTURER , manufacturer : e.target.value})}
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Enter composition of the drug</Form.Label>
                    <Form.Control as="textarea" rows={2}
                        onChange = {(e) => dispatch({type : SET_COMPOSITION , composition : e.target.value})}
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Enter side effects of the drug</Form.Label>
                    <Form.Control as="textarea" rows={4}
                        onChange = {(e) => dispatch({type : SET_SIDE_EFFECTS, sideEffects : e.target.value})}
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Enter recommended consumption    of the drug</Form.Label>
                    <Form.Control type="text"
                        onChange = {(e) => dispatch({type : SET_RECOMMENDED_CONSUMPTION , recommendedConsumption : e.target.value})}
                    ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Choose substitute drugs</Form.Label>
                    <Form.Control as="select" multiple>
                        {
                            drugs.length ? (
                                drugs.map(drug => 
                                        <option onClick={(e)=> dispatch({type : SET_SUBSTITUTE_DRUGS, substituteDrug : e.target.value})}
                                         key={drug.id} value={drug.id}>{drug.name}</option>
                                    )
                            ) : <option></option>
                        }
                    </Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Check type="checkbox" label  = "Does patient needs receipt"
                        onClick = {(e) => dispatch({type : SET_RECEIPT , receipt : e.target.checked})}
                    ></Form.Check>
                </Form.Group>
                <Button onClick={(e) => saveDrug(e)}>Save changes</Button>
            </Form>
        </div>
     );
}
 
export default NewDrugForm;