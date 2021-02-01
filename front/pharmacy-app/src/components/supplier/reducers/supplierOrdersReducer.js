
function setOrders(state,orders){
   
    state.orders = orders.map(order => {
                if(order.supplierDTOS[0].status === "PENDING")
                    if(order.supplierDTOS[0].deliveryDate === null || order.supplierDTOS[0].priceOffer === null){
                        return {
                            ...order,
                            buttonsVisibilty : true,
                        }
                    }
                return {
                    ...order,
                    buttonsVisibilty : false
                }
            });
            
    if(state.allOrders.length === 0)
        state.allOrders = state.orders;
    return state;
}

function filterOrders(state,status){
    state.orders = state.allOrders;
    if(status !== 'no-status')
        state.orders = state.allOrders.filter(order => order.supplierDTOS[0].status === status);

    // console.log('orders',state.orders);
    // state.orders = orders;
    return state;
}

export default function  supplierOrderReducer(state,action){
    switch(action.type){
       case SET_ORDERS : return setOrders(state,action.orders);
       case FILTER_ORDERS : return filterOrders(state,action.status);
       default : throw new Error("supplier order reducer error");

    }
}

export const SET_ORDERS = "SET_ORDERS";
export const FILTER_ORDERS = "FILTER_ORDERS";