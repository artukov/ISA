
export const NEW_ORDER = "NEW_ORDER";
export const SET_ORDERS = "SET_ORDERS";
export const DELETE_ORDER = "DELETE_ORDER";
export const CHECK_BUTTON_VISIBILITY = "CHECK_BUTTON_VISIBILITY";

 function newOrder(order, state){


    return state;
}

function setOrders(orders) {

    let buttonsVisibilty = false;
    let state = orders.map(order =>{
        let retObj = {
            ...order,
            supplierDTOS : order.supplierDTOS.map(supplier => {
               
                if(supplier.status === "PENDING")
                    if(supplier.deliveryDate === null || supplier.priceOffer === null)
                        buttonsVisibilty = true;
                    else
                        buttonsVisibilty = false
                return supplier;
            })
        }
        retObj.buttonsVisibilty = buttonsVisibilty
        return retObj
    })

    return state;
}

function deleteOrder(id,state){

    const filteredState  = state.filter(order => order.id !== id);
    return filteredState;
}

function checkButtonVisibility(state){

}

export function supplyOrderReducer(state,action){

    switch(action.type){
        case NEW_ORDER : return newOrder(action.order,state);
        case SET_ORDERS : return setOrders(action.orders);
        case DELETE_ORDER : return deleteOrder(action.id,state);
        case CHECK_BUTTON_VISIBILITY : return checkButtonVisibility(state);
        default : return state;
    }

}