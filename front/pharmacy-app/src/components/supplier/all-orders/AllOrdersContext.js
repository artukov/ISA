import React, { useState, useEffect , createContext, useReducer} from 'react';
import { axiosConfig } from '../../../config/AxiosConfig';
import supplierOrdersReducer,{ SET_ORDERS } from '../reducers/supplierOrdersReducer';

export const AllOrdersContext = createContext();

const AllOrdersContextProvider = (props) => {

    const [state, dispatch] = useReducer(supplierOrdersReducer, {
        orders : []
    });

    useEffect(() => {
        const loadAllSupplierOrders = async (id) =>{
            try{
                const result = await axiosConfig.get('/supplier/allOrders/' + id);
                dispatch({type : SET_ORDERS, orders : result.data});
            }
            catch(err){
                console.log(err.response);
            }
        }
        if(props.supplierID !== undefined)
            loadAllSupplierOrders(props.supplierID);
    }, [props.supplierID]);

    const makeAnOffer = async (data) => {
        // console.log(data);
        const order = state.orders.find(order => order.id === data.orderID);
        // console.log(order);
        let orderWithOffer = {...order.supplierDTOS[0],...data}
        // console.log(orderWithOffer);

        try{
            await axiosConfig.put('/supplier/offer',orderWithOffer);
            // dispatch({type : DELETE_ORDER, id : data.orderID});
        }
        catch(err){
            console.log(err.response);
            if(err.response !== undefined)
                alert(err.response.data);
        }

    }


    return ( 
        <AllOrdersContext.Provider value = {{
            state,
            dispatch,
            makeAnOffer
        }}>
            {props.children}
        </AllOrdersContext.Provider>
     );
}
 
export default AllOrdersContextProvider;