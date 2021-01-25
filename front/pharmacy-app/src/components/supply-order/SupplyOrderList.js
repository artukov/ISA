import React, { useState, useEffect, useContext } from 'react';
import {SupplyOrderContext} from '../context/SupplyOrderContext';


const SupplyOrderList = () => {

    const {orders} = useContext(SupplyOrderContext);

    useEffect(() => {
        console.log('orders',orders);
    }, [orders])

    return ( 
        <div>
            {
                orders.length ? orders.map((order,index) => <p key={index}>{order.id}</p>) : <p>Loading orders...</p>
            }
        </div>
     );
}
 
export default SupplyOrderList;