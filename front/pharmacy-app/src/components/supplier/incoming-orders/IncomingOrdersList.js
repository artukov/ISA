import React, { useState, useEffect, useContext } from 'react';
import { CardDeck } from 'react-bootstrap';
import { IncomingOrdersContext } from './IncomingOrdersContext';
import OrderDetails from './OrderDetails';

const IncomingOrdersList = () => {

    const {orders} = useContext(IncomingOrdersContext);

    useEffect(() => {
      
    }, [orders.length]);

    return ( 
        <div>
           <CardDeck>
           {
                orders.length ? (
                    orders.map(order => 
                            <OrderDetails key={order.id} order={order}></OrderDetails>
                        )
                ) : (<p>Loading orders </p>)
            }
           </CardDeck>
        </div>
     );
}
 
export default IncomingOrdersList;