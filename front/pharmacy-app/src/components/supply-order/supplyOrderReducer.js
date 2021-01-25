
export const NEW_ORDER = "NEW_ORDER";
export const SET_ORDERS = "SET_ORDERS";
export const DELETE_ORDER = "DELETE_ORDER";


// const  loadWithoutOffers = async (pharmacyID,state) =>{

//     const resault = await axiosConfig.get(urlGetWithoutOffers + pharmacyID);
//     console.log(resault.data);

    
//     return resault.data;
    
// }

// async function loadWithStatus(status,pharmacyID){
//     const resault = await axiosConfig.get(urlGetWithStatus + status + "/" + pharmacyID);
//     return resault.data;
// }

 function newOrder(order, state){
    // try{
    //     await axiosConfig.post(urlPostNewOrder, order);
    //     return [...state, order];
    // }
    // catch(err){
    //     console.log(err);
    //     return;
    // }

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