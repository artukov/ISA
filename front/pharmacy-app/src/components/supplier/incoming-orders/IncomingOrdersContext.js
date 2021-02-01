import React, { useState, useEffect, createContext, useReducer} from 'react';
import { axiosConfig } from '../../../config/AxiosConfig';
import { DELETE_ORDER } from '../../supply-order/supplyOrderReducer';
import incomingOrdersReducer, {SET_ORDERS} from '../reducers/incomingOrdersReducer';

export const IncomingOrdersContext = createContext();

const IncomingOrdersContextProvider = (props) => {


    const [orders, dispatch] = useReducer(incomingOrdersReducer,{
        orders : []
    });

    useEffect(() => {
        const loadIncomingOrders = async (id) =>{
            const result = await axiosConfig.get('supplier/allIncomingOrders/'+id);
            // console.log(result.data);
            dispatch({type : SET_ORDERS, orders : result.data});
        }
        // console.log(props.supplierID);

        if(props.supplierID !== undefined){
            loadIncomingOrders(props.supplierID);
        }
        
    }, [props.supplierID]);


    const makeAnOffer = async (data) => {
        // console.log(data);
        const order = orders.find(order => order.id === data.orderID);
        // console.log(order);
        let orderWithOffer = {...order.supplierDTOS[0],...data}
        // console.log(orderWithOffer);

        try{
            await axiosConfig.put('/supplier/offer',orderWithOffer);
            dispatch({type : DELETE_ORDER, id : data.orderID});
        }
        catch(err){
            console.log(err.response);
            if(err.response !== undefined)
                alert(err.response.data);
        }

    } 

    return ( 
        <IncomingOrdersContext.Provider value ={{
            orders,
            dispatch,
            makeAnOffer
        }}>
            {props.children}
        </IncomingOrdersContext.Provider>
     );
}
 
export default IncomingOrdersContextProvider;