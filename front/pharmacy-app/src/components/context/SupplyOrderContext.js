import React, { useState, useEffect, createContext, useReducer } from 'react';
import { axiosConfig } from '../../config/AxiosConfig';
import { urlAcceptOffer, urlGetOrderStatuses, urlGetWithoutOffers, urlGetWithStatus } from '../../services/UrlService';
import { DELETE_ORDER, SET_ORDERS, supplyOrderReducer } from '../supply-order/supplyOrderReducer';

export const SupplyOrderContext = createContext();

const SupplyOrderContextProvider = (props) => {

    const [statuses, setStatuses] = useState([]);

    useEffect(() => {
        const loadOrderStatuses = async () =>{
            const resault = await axiosConfig.get(urlGetOrderStatuses);
            setStatuses(resault.data);
        }
        if(props.pharmacyID !== undefined)
            loadOrderStatuses();
    }, [props.pharmacyID]);

    const [orders, dispatch] = useReducer(supplyOrderReducer, []);

    // const [orders, setOrders] = useState([]);

    const loadWithoutOffers = async () =>{
        try{
            const result = await axiosConfig.get(urlGetWithoutOffers + props.pharmacyID);
            dispatch({type : SET_ORDERS, orders : result.data});
        }   
        catch(err){
            if(err.response !== undefined)
                alert(err.response.data);
            console.log(err);
        }     
    }

    const loadWithStatus = async (status) =>{
        try{
            const result = await axiosConfig.get(urlGetWithStatus + status + "/" + props.pharmacyID);
            dispatch({type : SET_ORDERS, orders :result.data});
        }
        catch(err){
            if(err.response !== undefined)
                alert(err.response.data);
            console.log(err);
        }
    }

    const acceptOffer = async (order, supplierID) =>{
        // console.log(order, supplierID);
        const acceptedOrder = order.supplierDTOS.find(supplier => supplier.supplierID === supplierID);
        // console.log('accepted offfer', acceptedOrder);

        try{
            await axiosConfig.put(urlAcceptOffer, acceptedOrder);
            dispatch({type : DELETE_ORDER, id : order.id});
        }
        catch(err){
            console.log(err.response);
        }

    }

    const deleteOrder = async (id) =>{
        console.log(id);

    }

    
    return ( 
        <SupplyOrderContext.Provider 
            value = {{
                statuses,
                orders,
                loadWithoutOffers,
                loadWithStatus,
                acceptOffer,
                deleteOrder
                // pharmacyID : props.pharmacyID
            }}
        >
            {props.children}
        </SupplyOrderContext.Provider>
     );
}
 
export default SupplyOrderContextProvider;