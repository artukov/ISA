import React, { useState, useEffect , useContext} from 'react';
import { CardDeck } from 'react-bootstrap';
import { AbsenceRequestContext } from './AbsenceRequestContext';
import AbsenceRequestDetials from './AbsenceRequestDetails';

const AbsenceRequestsList = () => {

    const {requests} = useContext(AbsenceRequestContext);

    useEffect(() => {
        
    }, [requests]);

    return ( 
        <div>
            <CardDeck>
                {
                    requests ? (
                        requests.map(request => 
                                <AbsenceRequestDetials key={request.id} request={request}></AbsenceRequestDetials>
                            )
                    ) : <p>Loading requests...</p>
                }
            </CardDeck>
        </div>
     );
}
 
export default AbsenceRequestsList;