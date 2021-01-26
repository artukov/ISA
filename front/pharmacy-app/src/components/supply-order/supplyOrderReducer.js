
export const NEW_ORDER = "NEW_ORDER";
export const SET_ORDERS = "SET_ORDERS";
export const DELETE_ORDER = "DELETE_ORDER";

 function newOrder(order, state){


    return state;
}

function setOrders(orders) {

    return orders;
}

function deleteOrder(id,state){

    const filteredState  = state.filter(order => order.id !== id);
    return filteredState;
}


export function supplyOrderReducer(state,action){

    switch(action.type){
        case NEW_ORDER : return newOrder(action.order,state);
        case SET_ORDERS : return setOrders(action.orders);
        case DELETE_ORDER : return deleteOrder(action.id,state);
        default : return state;
    }

}