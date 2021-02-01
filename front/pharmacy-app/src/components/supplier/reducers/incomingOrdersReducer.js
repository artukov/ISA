function setOrders(state,orders) {
    return orders; 
}

function deleteOrder(state,id){
    return state.filter(order => order.id !== id);
}

function init(){
    return {
        orders: []
    }
}

export default function incomingOrdersReducer(state,action){

    switch(action.type){
        case SET_ORDERS : return setOrders(state,action.orders);
        case DELETE_ORDER : return deleteOrder(state,action.id);
        case INIT : return init();
        
        default : throw new Error("incoming orders reducer error");
    }
}

export const SET_ORDERS = "SET_ORDERS";
export const DELETE_ORDER = "DELETE_ORDER";
export const INIT = "INIT";