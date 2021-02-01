import React, { useState, useEffect, useContext } from 'react';
import { CardDeck } from 'react-bootstrap';
import { AllOrdersContext } from './AllOrdersContext';
import OrderDetails from './OrderDetails';


const AllOrdersList = () => {
    
    const {state} = useContext(AllOrdersContext);

    // useEffect(() => {
    //     console.log('state', state);
        
    // }, [state]);
    
    return ( 
        <div>
            <CardDeck>
                {
                    state.orders ? (
                        state.orders.map(order => 
                           <OrderDetails key={order.id} order = {order}></OrderDetails>
                        )
                    ) : <p>Loading orders ... </p>
                }
            </CardDeck>
        </div>
     );
}
 
export default AllOrdersList;