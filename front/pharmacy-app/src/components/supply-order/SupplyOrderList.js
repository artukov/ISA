import React, { useState, useEffect, useContext } from 'react';
import { Card, CardDeck } from 'react-bootstrap';
import {SupplyOrderContext} from '../context/SupplyOrderContext';
import SupplyOrderDetails from './SupplyOrderDetails';


const SupplyOrderList = () => {

    const {orders} = useContext(SupplyOrderContext);

    useEffect(() => {
        //console.log('orders',orders);
    }, [orders])

    return ( 
        <div>
            <CardDeck>
            {
                orders.length ? orders.map((order,index) => {
                    return (
                        <SupplyOrderDetails order = {order} key = {index}></SupplyOrderDetails>
                    )
                }) : null
            }
            </CardDeck>
        </div>
     );
}
 
export default SupplyOrderList;