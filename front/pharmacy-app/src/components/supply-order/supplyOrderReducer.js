
export const NEW_ORDER = "NEW_ORDER";
export const SET_ORDERS = "SET_ORDERS";
export const DELETE_ORDER = "DELETE_ORDER";
export const CHECK_BUTTON_VISIBILITY = "CHECK_BUTTON_VISIBILITY";
export const ADD_ORDER = "ADD_ORDER";

 function newOrder(order, state){


    return state;
}

function setOrders(orders) {

    let buttonsVisibilty = false;
    let state = orders.map(order =>{
        let retObj = {
            ...order,
            buttonsVisibilty
        }
        return retObj
    })

    return state;
}

function deleteOrder(id,state){

    const filteredState  = state.filter(order => order.id !== id);
    return filteredState;
}



function checkButtonVisibility(state,order){

    const checkIfOfferExists = (supplier) =>{
        
        if(supplier.status === "PENDING")
            if(supplier.deliveryDate === null || supplier.priceOffer === null)
                return true;
            else
                return false;
        return false;
    }

    let buttonsVisibilty = false
    if(order.supplierDTOS.every(checkIfOfferExists))
        buttonsVisibilty = true;

    const orderID = order.id;

   return state.map(order => {
       if(order.id === orderID)
            order.buttonsVisibilty = buttonsVisibilty;
        return order;
   });

    

}

function addOrder(state,order){

    return [
        ...state,
        order
    ]
}

export function supplyOrderReducer(state,action){

    switch(action.type){
        case NEW_ORDER : return newOrder(action.order,state);
        case SET_ORDERS : return setOrders(action.orders);
        case DELETE_ORDER : return deleteOrder(action.id,state);
        case CHECK_BUTTON_VISIBILITY : return checkButtonVisibility(state,action.order);
        case ADD_ORDER : return addOrder(state,action.order);
        default : return state;
    }

}