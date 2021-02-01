
function setOrders(state,orders){

    return {
        ...state,
        orders : orders.map(order => {
            // let buttonsVisibilty = false;
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
        })
    }
}


export default function  supplierOrderReducer(state,action){
    switch(action.type){
       case SET_ORDERS : return setOrders(state,action.orders);
       default : throw new Error("supplier order reducer error");

    }
}

export const SET_ORDERS = "SET_ORDERS"