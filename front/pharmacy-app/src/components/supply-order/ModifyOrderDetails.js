import React, { useState, useEffect, useContext, useReducer } from 'react';
import {Modal,Button,Form,Row,Col, ListGroup} from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlGetAllSuppliers, urlGetAllSystemDrugs } from '../../services/UrlService';
import { SupplyOrderContext } from '../context/SupplyOrderContext';
import { newOrderReducer, SET_ID,SET_DATE,SET_TIME,ADD_DRUG,ADD_SUPPLIER,INIT, DELETE_DRUG } from './newOrderReducer';

const ModfiyOrderDetails = () => {

    const {showModifyModal,saveOrder, order,closeModifyModal} = useContext(SupplyOrderContext);
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
            
           if(order !== null){
            for(let i  = 0; i < order.drugs.length; i++){
                dispatch({type : ADD_DRUG, payload : { id : order.drugs[i], name : order.drugsNames[i], amount : order.amount[i]} });
                // setDrugs(drugs.filter(drug => drug.id !== order.drugs[i]));
               
            }
            order.supplierDTOS.map(supplier =>
                dispatch({type : ADD_SUPPLIER, payload :supplier.supplierID })
            );

            const [date, time,] = order.deadlineDate.split(" ");
            const [day,month,year] = date.split("-");
            dispatch({type : SET_DATE, date : year+"-"+month+"-"+day});
            dispatch({type : SET_TIME, time });
            dispatch({type : SET_ID, id : order.id});

            // console.log('new state',state);

        }
           }

       
        loadDrugs();
        loadSuppliers();
        setReducerState();
    }, [showModifyModal]);

    const cancelCreation = () =>{
        dispatch({type : INIT});
        setSelectedDrug({});
        setDrugs([]);
        setSuppliers([]);

        closeModifyModal();
    }

    return ( 
        <div>
            <Modal show={showModifyModal} onHide={()=>cancelCreation()}>
                <Modal.Header closeButton><Modal.Title>Modifying supply order</Modal.Title></Modal.Header>
                <Modal.Body>
                <Form>
                <Form.Group>
                    <Form.Label>Choose which drug you want ot order and the amount</Form.Label>
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
                            <Form.Control type="date" 
                            value = {state.date ? state.date : ""}
                            onChange = {(e)=> dispatch({type : SET_DATE, date : e.target.value})}></Form.Control>
                        </Col>
                        <Col>
                            <Form.Control type="time" 
                            value = {state.time ? state.time : ""}
                            onChange = {(e) => 
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
                                                    const id = drug.id;
                                                    const deletedDrug = drugs.find(drug => drug.id === id);
                                                    if(!deletedDrug){
                                                        setDrugs([
                                                            {
                                                                id : drug.id,
                                                                name : drug.name
                                                            },
                                                            ...drugs
                                                        ])
                                                    }
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


                </Modal.Body>
            </Modal>
        </div>
     );
}
 
export default ModfiyOrderDetails;