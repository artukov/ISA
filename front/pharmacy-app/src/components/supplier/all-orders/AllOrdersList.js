import React, { useState, useEffect, useContext } from 'react';
import { CardDeck } from 'react-bootstrap';
import { AllOrdersContext } from './AllOrdersContext';
import OrderDetails from './OrderDetails';


const AllOrdersList = () => {
    
    const {state,orders} = useContext(AllOrdersContext);

    useEffect(() => {
    }, [orders]);
    
    return ( 
        <div>
            <CardDeck>
                {
                    
                    state.orders.length ? (
                        state.orders.map((order, index) => 
                           <OrderDetails key={index} order = {order}></OrderDetails>
                        )
                    ) : <p>Loading orders ... </p>
                }
            </CardDeck>
        </div>
     );
}
 
export default AllOrdersList;