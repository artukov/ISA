import React, { useState, useEffect, useReducer } from 'react'
import { Button, Col, Form, ListGroup, Row } from 'react-bootstrap';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import absencerequestReducers, { SET_START_DATE, SET_START_TIME } from './AbsenceRequestPReducer';
import SubstituteDrugsComponentP from './SubstituteDrugsComponentP';

const NewConsultation = () => {
   const [selectedPatient, setSelectedPatient] = useState({});
    const [patients, setPatients] = useState([]);
    const [pharmacist, setPharmacist] = useState({});
    const [report, setReport] = useState({});
    const [description, setDescription] = useState({});
    const [price, setPrice] = useState({})
    const [allDrugs, setAllDrugs] = useState([]);
    const [selectedDrug, setSelectedDrug] = useState({});
    const [choosenDrugs, setChoosenDrugs] = useState([]);
    const [selectedAppointment, setSelectedAppointment] = useState({});
    const [showForm, setShowForm] = useState(false);
    const [substituteDrugs, setSubstituteDrugs] = useState([]);
    const [isAllergic, setIsAllergic] = useState({});
    const [showFormBookAppointment, setShowFormBookAppointment] = useState(false);
    const [duration, setDuration] = useState({});

    const openForm  = () => {
        setShowForm(true);

    }
    
    const openFormForAppointment = () => {
        setShowFormBookAppointment(true);
    }

     const createAppointment = async () =>{
        
        console.log(state);
        let newAppointment = {
            beggingDateTime : formatDate(state.startDate,state.startTime),
            duration: duration,
            pharmacyID: pharmacist.pharmacyID,
            patient_id: selectedPatient.id,
            drugs: []
        };
        console.table(newAppointment);


        try{
            await axiosConfig.post('pharmacist/appointment/new',newAppointment);
            // dispatch({type : INIT});
            // setLatestPL(state);
            //setReload(!reload);
            alert("Appointment created");
        }
        catch(err){
            console.log(err);
            alert(err.response.data);
        }
    }
    useEffect(() => {
        const loadPharmacist = async () => {
            try {
                const result = await axiosConfig.get('/pharmacist/current');
                setPharmacist(result.data);
            }
            catch (err) {
                console.log(err.response.data);
            }
        }

        loadPharmacist();
       
    }, []);

    useEffect(() => {
        const loadPharmacistPatients = async () => {
            try {
                const result = await axiosConfig.get('/pharmacist/patientsDistinct/');
                setPatients(result.data);

            } catch (err) {
                console.log(err.response.data);
            }
            
        }
        if (pharmacist.id !== undefined)
            loadPharmacistPatients(pharmacist.id);
            
    }, [pharmacist.id])

    useEffect(() => {
        const loadDrugs = async () => {
            try {
                const result = await axiosConfig.get('/drug/allPharmacyDrugs/'+selectedAppointment.pharmacyID);
                setAllDrugs(result.data);
            } catch (err) {
                console.log(err.response.data);
            }
            
        }
        if (selectedAppointment.pharmacyID !== undefined)
            loadDrugs();
    }, [selectedAppointment.pharmacyID])

    const loadConsultation = async (id) => {
        const [year, month, day] = state.startDate.split("-");
        const [hours, minutes] = state.startTime.split(":");
        let dateTime = new Date(year, month, day, hours, minutes, 0);
        dateTime.setMonth(dateTime.getMonth() - 1);
            try {
                const result = await axiosConfig.get('/pharmacist/findConsultation/' + id, {
                    params: { dateTime: dateTime.getTime() }
                })
                setSelectedAppointment(result.data);

            } catch (err) {
                console.log(err.response.data);
                alert(err.response.data);
            }
            
    }
    
    const addPenalty = async (id) => {


        try{
            await axiosConfig.put('/pharmacist/addPenalty/'+id);
            //dispatch({type : INIT});
            // setLatestPL(state);
            alert("Penalty added");
        }
        catch(err){
            console.log(err);
            alert(err.response.data);
        }
    }

    const saveConsultation = async () => {
        let consultation = {
            id: selectedAppointment.id,
            report: report,
            beggingDateTime: selectedAppointment.beggingDateTime,
            duration: selectedAppointment.duration,
            finished: true,
            drugs: [],
            patient_id: selectedAppointment.patient_id,
            pharmacyID: selectedAppointment.pharmacyID,
            pharmacistID: pharmacist.id
            
        }
        for (let drug of choosenDrugs) {
            consultation.drugs = [...consultation.drugs, drug.id]
        }

        try{
            await axiosConfig.put('/pharmacist/consultation/new',consultation);
            //dispatch({type : INIT});
            // setLatestPL(state);
            alert("Consultation saved");
        }
        catch(err){
            console.log(err);
            alert(err.response.data);
        }
    }

     const loadSubstituteDrugs = async (patientId, drugId) => {
        try {
            const result = await axiosConfig.get('pharmacist/checkAllergy/'+patientId+'/'+drugId);
            setSubstituteDrugs(result.data);
        } catch (err) {
            console.log(err);
        }
        
    }

    const [state, dispatch] = useReducer(absencerequestReducers, {
        startDate: '',
        startTime: ''
    });


    return (  
 <div>
            <Form onSubmit={(e) => e.preventDefault()}>
                <Row>
                    <Col>
                        <div>Select patient</div>
                        <Form.Control as = "select" placeholder = "Patient" onClick = {(e) => setSelectedPatient(JSON.parse(e.target.value))}>
                            {
                                patients ? (
                                    patients.map(patient =>
                                        <option key={patient.id} value={JSON.stringify({ id: patient.id, name: patient.firstname })}>
                                            {patient.firstname} {patient.lastname}
                                        </option>
                                    )
                                ) : null}
                        </Form.Control> 
                    </Col>1
                    <Col>
                        <div>Select date</div>
                    <Form.Control type = "date"  onChange = {(e) => dispatch({ type: SET_START_DATE, startDate: e.target.value })}></Form.Control>
                    </Col>
                    <Col>
                        <div>Select time</div>
                    <Form.Control type = "time" onChange = {(e) => dispatch({type : SET_START_TIME, startTime : e.target.value})}></Form.Control>
                    </Col>

                </Row>
                <Row>
                    <Col>
                        <Button onClick={(e) => {
                            openForm();
                            loadConsultation(selectedPatient.id);
                    }}>Start Consultation</Button>
                    </Col>
                    <Col>
                        <Button onClick={(e) => {
                            addPenalty(selectedPatient.id);
                    }}>Did not show up</Button>
                    </Col>
                </Row>
            </Form>
            { showForm ? 
                <Form onSubmit={(e) => e.preventDefault()}>
                <Row>

                    {/* <Col>
                        <div>Diagnose</div>
                        <Form.Control type="text" placeholder = "Diagnose" onChange = { (e) => setDescription(e.target.value)}></Form.Control>
                    </Col> */}
                    <Col>
                        <div>Report</div>
                        <Form.Control type="text" placeholder = "Report" onChange = { (e) => setReport(e.target.value) }></Form.Control>
                    </Col>
                    
                    
                </Row>
                <Row>
                    <Col>
                        <Form.Label>Select drugs</Form.Label>
                    <Form.Control as="select" onClick = {(e) => setSelectedDrug(JSON.parse(e.target.value))}>
                            {
                                allDrugs ? (
                                    allDrugs.map(drug =>
                                        <option key={drug.id} value={JSON.stringify({ id: drug.id, name: drug.name })}>
                                            {drug.name} 
                                        </option>
                                    )
                                ) : null}
                        
                        </Form.Control>
                        </Col>
                        
                        <Button onClick={(e) => {
                            setChoosenDrugs([...choosenDrugs, selectedDrug]);
                          
                            setAllDrugs(allDrugs.filter(drug => drug.id !== selectedDrug.id));
                            }}>Add drug to the list</Button>
                        
                        {/* <Col>
                        <div>Price</div>
                        <Form.Control type="number" placeholder = "Price" onChange = { (e) => setPrice(parseFloat(e.target.value)) }></Form.Control>
                        </Col> */}

                </Row>
           <Form.Group>
                    <Form.Label>List of all the drug selected</Form.Label>
                        {
                            choosenDrugs ? (
                                choosenDrugs.map(drug => {
                                    return (
                                        <ListGroup key = {drug.id} horizontal>
                                            <ListGroup.Item variant ="primary">{drug.name}</ListGroup.Item>
                                          
                                            <ListGroup.Item>
                                                <Button onClick={() => {
                                                    const id = drug.id;
                                                    setChoosenDrugs(choosenDrugs.filter(drug => drug.id !== id))
                                                    setAllDrugs([
                                                        {
                                                            id : drug.id,
                                                            name : drug.name
                                                        },
                                                        ...allDrugs
                                                    ])
                                                }}
                                                 variant="warning">Delete from list</Button>
                                            </ListGroup.Item>
                                            <SubstituteDrugsComponentP patientId={selectedAppointment.patient_id} drugId={drug.id}></SubstituteDrugsComponentP>
                                            {/* <ListGroup.Item>
                                                <Button onClick={async () => {
                                                    const id = drug.id;
                                                    await loadSubstituteDrugs(selectedAppointment.patient_id, id);
                                                    // if (substituteDrugs.length !== 0) {
                                                    //    const temp =  choosenDrugs.filter(drug => drug.id !== id);
                                                    //     setAllDrugs([
                                                    //     {
                                                    //         id : drug.id,
                                                    //         name : drug.name
                                                    //     },
                                                    //         ...allDrugs
                                                    //     ])
                                                    //     setChoosenDrugs([
                                                    //     {
                                                    //         id : substituteDrugs[0].id,
                                                    //         name : substituteDrugs[0].name
                                                    //     },
                                                    //         ...temp
                                                    //     ])
                                                    // }
                                                    // setSubstituteDrugs([]);
                                                }}
                                                 variant="warning">Check for allergy</Button>
                                            </ListGroup.Item> */}
                                            <ListGroup.Item>
                                                {/* <Form.Label>If patient is allergic here is the list of substitute drugs</Form.Label>
                                                    <Form.Control as="select">
                                                            {
                                                                substituteDrugs ? (
                                                                    substituteDrugs.map(drug =>
                                                                        <option key={drug.id} value={JSON.stringify({ id: drug.id, name: drug.name })}>
                                                                            {drug.name} 
                                                                        </option>
                                                                    )
                                                                ) : null}
                        
                                                </Form.Control> */}
                                            </ListGroup.Item>
                                        </ListGroup>
                                    )
                                })
                            ) : null
                        }
                    </Form.Group>
                    <Row>
                        <Col>
                <Button onClick={() => {
                saveConsultation();
                            }}>Save examination</Button>
                        </Col>
                        <Col>
                    <Button onClick={() => {
                        openFormForAppointment();
                    }}>Book another appointment</Button>
                        </Col>
                         </Row>
                    {showFormBookAppointment ?
                        <Form onSubmit={(e) => e.preventDefault()}>
                            <Row>
                                <Col>
                            <div>Set duration</div>
                    <Form.Control type="number" onChange = {(e) => setDuration(parseInt(e.target.value))}>
                        
                            </Form.Control>
                        </Col>
                                <Col>
                                    <div>Start date</div>
                        <Form.Control type = "date"
                                onChange = {(e) => dispatch({ type: SET_START_DATE, startDate: e.target.value })}
                             ></Form.Control>
                        </Col>
                                <Col>
                                    <div>Start time</div>
                            <Form.Control type="time" onChange = {(e) => dispatch({type : SET_START_TIME, startTime : e.target.value})}></Form.Control>
                            
                                </Col>
                                <Col>
                                    <Button onClick={() => {
                                        createAppointment();
                                }}>Book</Button>
                                </Col>
                </Row>
                            </Form> : null
                            }
                           
                       
                             
                </Form> : null
            } 
            
            
        </div>
    );
}
 
export default NewConsultation;