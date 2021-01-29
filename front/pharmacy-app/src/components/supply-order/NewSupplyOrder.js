import React, { useState, useEffect,useContext, useReducer } from 'react';
import { Col, Button ,Form, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlGetAllSuppliers, urlGetAllSystemDrugs } from '../../services/UrlService';
import { SupplyOrderContext } from '../context/SupplyOrderContext';
import { ADD_DRUG, ADD_SUPPLIER, DELETE_DRUG, INIT, newOrderReducer, SET_DATE, SET_TIME } from './newOrderReducer';

const NewSupplyOrder = () => {

    const {showAddForm,saveOrder,closeAddForm} = useContext(SupplyOrderContext);
    const [drugs, setDrugs] = useState([]);
    const [suppliers, setSuppliers] = useState([]);

    const [selectedDrug, setSelectedDrug] = useState(null);
    const [amount, setAmount] = useState(0.0);


    const [state, dispatch] = useReducer(newOrderReducer, {
        drugs : [],
        supplier : [],
        date : null,
        time : null
    });

    useEffect(() => {
        const loadSuppliers = async () =>{
            try{
                const result = await axiosConfig.get(urlGetAllSuppliers);
                setSuppliers(result.data);
            }
            catch(err){
                console.log(err);
                setSuppliers([]);
            }
        }

        const loadDrugs = async () =>{
            //console.log("reload");
            try{
                const result = await axiosConfig.get(urlGetAllSystemDrugs);
                setDrugs(result.data);
                setSelectedDrug({
                    id : result.data[0].id,
                    name : result.data[0].name
                });
                
            }
            catch(err){
                console.log(err);
                setDrugs([]);
            }
        }

        const setReducerState = () =>{
            dispatch({type : INIT});
        }

       
        loadDrugs();
        loadSuppliers();
        setReducerState();
    }, [showAddForm]);


    const cancelCreation = () =>{
        dispatch({type : INIT});
        setSelectedDrug({});
        setDrugs([]);
        setSuppliers([]);

        closeAddForm();
    }

    return ( 
        <div hidden = {!showAddForm}>
            <Form>
                <Form.Group>
                    <Form.Label>Choese which drug you want ot order and the amount</Form.Label>
                    <Row>
                        <Col>
                            <Form.Control as="select" onClick = {(e)=>{
                                setSelectedDrug(JSON.parse(e.target.value));
                            }}>
                                {
                                    drugs.length ? (
                                        // drugs.filter(drug => drug.id !== selectedDrug);
                                        drugs.map(drug =>{
                                            return(
                                                <option key={drug.id} 
                                                value={JSON.stringify({id : drug.id, name : drug.name})}
                                                >{drug.name}</option>
                                            );
                                        }
                                            )
                                    ) : null
                                }

                            </Form.Control>
                        </Col>
                        <Col>
                            <Form.Control type="number" onChange = {(e)=>{
                                setAmount(parseInt(e.target.value));
                            }}></Form.Control>
                        </Col>
                        <Col>
                            <Button onClick={()=> {
                                dispatch({type : ADD_DRUG, payload : { id : selectedDrug.id, name : selectedDrug.name, amount} });
                                // let nextSelect = selectedDrug.id;
                                setDrugs(drugs.filter(drug => drug.id !== selectedDrug.id));
                                const nextSelected = drugs.find(drug => drug.id !== selectedDrug.id);
                                setSelectedDrug({
                                    id : nextSelected.id,
                                    name : nextSelected.name
                                });
                                
                            }}>Add to the list</Button>
                        </Col>
                    </Row>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Enter the deadline date</Form.Label>
                    <Row>
                        <Col>
                            <Form.Control type="date" onChange = {(e)=> dispatch({type : SET_DATE, date : e.target.value})}></Form.Control>
                        </Col>
                        <Col>
                            <Form.Control type="time" onChange = {(e) => 
                                dispatch({type : SET_TIME, time : e.target.value})}
                            ></Form.Control>
                        </Col>
                    </Row>
                </Form.Group>
                <Form.Group>
                    <Form.Label >Choose suppliers</Form.Label>
                    <Form.Control as="select" multiple onClick={(e) => 
                        dispatch({type : ADD_SUPPLIER, payload : parseInt(e.target.value)})}
                        >
                        {
                            suppliers ? (
                                suppliers.map(supplier =>{
                                    return (<option key={supplier.id} value={supplier.id}>{supplier.email}</option>)
                                }
                                    )
                            ) : null
                        }
                    </Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>List of all the drug to be ordered</Form.Label>
                        {
                            state.drugs ? (
                                state.drugs.map(drug => {
                                    return (
                                        <ListGroup key = {drug.id} horizontal>
                                            <ListGroup.Item variant ="primary">{drug.name}</ListGroup.Item>
                                            <ListGroup.Item variant ="success">{drug.amount}</ListGroup.Item>
                                           {
                                               state.suppliers ? (
                                                   state.suppliers.map(supplier => 
                                                        <ListGroup.Item key={supplier}>{supplier}</ListGroup.Item>
                                                    )
                                               ) : null
                                           }
                                            <ListGroup.Item>
                                                <Button onClick={() => {
                                                    dispatch({type : DELETE_DRUG, id : drug.id});
                                                    setDrugs([
                                                        {
                                                            id : drug.id,
                                                            name : drug.name
                                                        },
                                                        ...drugs
                                                    ])
                                                }}
                                                 variant="warning">Delete from list</Button>
                                            </ListGroup.Item>
                                        </ListGroup>
                                    )
                                })
                            ) : null
                        }
                </Form.Group>
                <Button variant ="primary" onClick = {()=> saveOrder(state)}>Save changes</Button>
                <Button variant = "secondary" onClick = {() => cancelCreation()}>Cancel</Button>
            </Form>
        </div>
     );
}
 
export default NewSupplyOrder;