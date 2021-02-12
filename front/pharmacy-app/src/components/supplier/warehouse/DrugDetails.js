import React, { useState, useEffect } from 'react';
import { ListGroup, Card, } from 'react-bootstrap';

const DrugDetails = ({drug}) => {
    return ( 
        <div>
            <Card>
                <Card.Header>
                    <Card.Title>Drug info</Card.Title>
                </Card.Header>
                <ListGroup>
                    <ListGroup.Item>Drug name : {drug.name}</ListGroup.Item>
                    <ListGroup.Item>Amount : {drug.amount}</ListGroup.Item>
                </ListGroup>
                <Card.Footer>

                </Card.Footer>
            </Card>
        </div>
     );
}
 
export default DrugDetails;