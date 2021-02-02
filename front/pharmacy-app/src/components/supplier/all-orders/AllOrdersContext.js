import React, { useState, useEffect , createContext, useReducer} from 'react';
import { axiosConfig } from '../../../config/AxiosConfig';
import supplierOrdersReducer,{ FILTER_ORDERS, SET_ORDERS } from '../reducers/supplierOrdersReducer';

export const AllOrdersContext = createContext();

const AllOrdersContextProvider = (props) => {

    const [state, dispatch] = useReducer(supplierOrdersReducer, {
        orders : [],
        allOrders : []
    });

    const [statuses, setStatuses] = useState([]);
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        const loadAllSupplierOrders = async (id) =>{
            try{
                const result = await axiosConfig.get('/supplier/allOrders/' + id);
                dispatch({type : SET_ORDERS, orders : result.data});
                setOrders(state.orders);
            }
            catch(err){
                console.log(err.response);
            }
        }

        const loadStatuses = async (id) => {
            try{
                const result = await axiosConfig.get('/supplyorder/statuses');
                setStatuses(result.data);
            }
            catch(err){
                console.log(err);
            }
        }

        if(props.supplierID !== undefined)
            loadAllSupplierOrders(props.supplierID);
            loadStatuses();
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

    const filterOrders = (status) =>{
        dispatch({type : FILTER_ORDERS, status});
        dispatch({type : SET_ORDERS, orders : state.orders});
        setOrders(state.orders);

    }


    return ( 
        <AllOrdersContext.Provider value = {{
            state,
            dispatch,
            makeAnOffer,
            statuses,
            filterOrders,
            orders
        }}>
            {props.children}
        </AllOrdersContext.Provider>
     );
}
 
export default AllOrdersContextProvider;