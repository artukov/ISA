import React, { useState, useEffect } from 'react';
import { Card } from 'react-bootstrap';

const DrugRequestDetails = ({request}) => {
    return ( 
        <Card>
            <Card.Header><Card.Title>Request info</Card.Title></Card.Header>
            <Card.Body>
                <Card.Text>Drug name : {request.drugName}</Card.Text>
                <Card.Text>User email : {request.userEmail}</Card.Text>
                <Card.Text>User role : {request.userRole}</Card.Text>
            </Card.Body>
        </Card>
     );
}
 
export default DrugRequestDetails;