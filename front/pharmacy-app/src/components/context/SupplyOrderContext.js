import React, { useState, useEffect, createContext, useReducer } from 'react';
import { axiosConfig } from '../../config/AxiosConfig';
import formatDate from '../../config/DateFormatConfig';
import { urlAcceptOffer, urlDeleteSupplyOrder, urlGetOrderStatuses, urlGetWithoutOffers, urlGetWithStatus, urlNewSupplyOrder } from '../../services/UrlService';
import { ADD_ORDER, DELETE_ORDER, SET_ORDERS, supplyOrderReducer } from '../supply-order/supplyOrderReducer';

export const SupplyOrderContext = createContext();

const SupplyOrderContextProvider = (props) => {

    const [statuses, setStatuses] = useState([]);
    const [modifying, setModifying] = useState(false);
    

    useEffect(() => {
        const loadOrderStatuses = async () =>{
            const resault = await axiosConfig.get(urlGetOrderStatuses);
            setStatuses(resault.data);
        }
        if(props.pharmacyID !== undefined)
            loadOrderStatuses();
    }, [props.pharmacyID]);

    const [showAddForm, setShowAddForm] = useState(false);
    const closeAddForm = () => {setShowAddForm(false);};
    const openAddForm = () => setShowAddForm(true);

    const [showModifyModal, setShowModifyModal] = useState(false);
    const closeModifyModal = () => {setOrder(null); setShowModifyModal(false)};
    const openModifyModal = () => setShowModifyModal(true);

    const [orders, dispatch] = useReducer(supplyOrderReducer, []);

    const [order, setOrder] = useState(null);

    const isModifying = (id) => {

        // console.log('modified order', id);
        setModifying(true);
        setOrder(orders.find(order => order.id === id));
        openModifyModal();

    }

   


    const loadWithoutOffers = async () =>{
        closeAddForm();
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
        closeAddForm();
        try{
            const result = await axiosConfig.get(urlGetWithStatus + status + "/" + props.pharmacyID);
            dispatch({type : SET_ORDERS, orders : result.data});
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
        try{
            await axiosConfig.delete(urlDeleteSupplyOrder + id);
            dispatch({type : DELETE_ORDER, id : id});
        }
        catch(err){
            console.log(err);
        }

    }

    const saveOrder = async (order) =>{
        console.log('old order',order);
        let newOrder = {
            deadlineDate : formatDate(order.date,order.time),
            adminID : 200,
            drugs : order.drugs.map(drug => drug.id),
            amount : order.drugs.map(drug => drug.amount),
            supplierDTOS : []
        }
        console.log('new order', newOrder);

        for(let supplier of order.suppliers){
            // console.log('supplier : ',supplier);
            newOrder.supplierDTOS.push({
                orderID : order.id,
                supplierID : supplier,
                priceOffer : null,
                deliveryDate : null,
                status : statuses[0]
            });
        }

        // console.log(newOrder);
        try{
            if(modifying){
                newOrder.id = order.id;
                await axiosConfig.put("/supplyorder/modify/", newOrder);
                setModifying(false);

            }
            else{
                await axiosConfig.post(urlNewSupplyOrder, newOrder);
            }
            
            dispatch({type : ADD_ORDER, order : newOrder});
            console.log(orders);
        }
        catch(err){
            console.log(err.response);
        }

        closeAddForm();
        closeModifyModal();
       
        
    }
    

    
    return ( 
        <SupplyOrderContext.Provider 
            value = {{
                statuses,
                orders,
                loadWithoutOffers,
                loadWithStatus,
                acceptOffer,
                deleteOrder,
                openAddForm,
                closeAddForm,
                showAddForm,
                saveOrder,
                dispatch,
                isModifying,
                order,
                showModifyModal,
                closeModifyModal
            }}
        >
            {props.children}
        </SupplyOrderContext.Provider>
     );
}
 
export default SupplyOrderContextProvider;