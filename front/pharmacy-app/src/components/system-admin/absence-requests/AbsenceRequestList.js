import React, { useState, useEffect, useContext } from 'react';
import { CardDeck } from 'react-bootstrap';
import AbsenceRequestDetails from './AbsenceRequestDetails';
import {AbsenceRequestContext} from './AbsenceRequestsContext'

const AbsenceRequestList = () => {

    const {requests} = useContext(AbsenceRequestContext);

    return ( 
        <div>
           <CardDeck>
            {
                requests.length ? (
                    requests.map(request => 
                            <AbsenceRequestDetails key={request.id} request={request}></AbsenceRequestDetails>
                        )
                ) : null
            }
           </CardDeck>
        </div>
     );
}
 
export default AbsenceRequestList;