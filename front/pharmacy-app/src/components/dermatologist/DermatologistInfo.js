import React, { useState, useEffect } from 'react'
import { Card, ListGroup } from 'react-bootstrap';
const DermatologistInfo = ({dermatologist}) => {
    return (
         <div>
            <Card >
                <Card.Header>
                    <Card.Title>Dermatologist details</Card.Title>
                </Card.Header>
                <Card.Body>
                    <Card.Text>Email : {/*dermatologist.email*/}</Card.Text>
                    <Card.Text>Firstname : {/*dermatologist.firstname*/}</Card.Text>
                    <Card.Text>Lastname : {/*dermatologist.lastname*/}</Card.Text>
                    <Card.Text>Phone number : {/*dermatologist.phoneNumber*/}</Card.Text>
                    <Card.Text>Ratings : {/*dermatologist.ratings !== null ? dermatologist.ratings : 0.0*/}</Card.Text>
                </Card.Body>
                <ListGroup>
                    <ListGroup.Item action variant="info">Working hours : {/*dermatologist.hours*/}</ListGroup.Item>
                    <ListGroup.Item action variant="info">Starting hours : {/*dermatologist.start_hour*/}</ListGroup.Item>
                </ListGroup>
                <Card.Footer>
                    {/* <Button variant='primary' onClick={() => openExamModal()}>Create examination</Button> */}
                    {/* <Button variant='danger' onClick={() => deleteDermatologist(dermatologist.id)}>Delete dermatologist</Button> */}
                </Card.Footer>
            </Card>

            {/* <Modal show={showModal} onHide={()=>closeModal()}>
                <Modal.Header closeButton><Modal.Title>Add description why the request has been denied</Modal.Title></Modal.Header>
                <Modal.Body>
                    <Form onSubmit={()=>{}}>
                        <Form.Control type="text" placeholder="Insert desciption" 
                            onChange = {(e) => request.description = e.target.value}
                        ></Form.Control>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick = {(e)=>{
                        e.preventDefault();
                        answerToTheRequest(request,false);
                        closeModal();
                    }}>Save changes</Button>
                    <Button variant="secondary" onClick = {()=>closeModal()}>Cancel</Button>
                </Modal.Footer>
            </Modal> */}
        </div>
      );
}
 
export default DermatologistInfo;