export const ADD_DRUG = "ADD_DRUG";
export const DELETE_DRUG = "DELETE_DRUG";
export const ADD_SUPPLIER = "ADD_SUPPLIER";
export const SET_DATE = "SET_DATE";
export const SET_TIME = "SET_TIME";
export const INIT = "INIT";

function addDrugAmount(state,payload){
    state.drugs = [...state.drugs, payload];
    //console.log(state);
    return state;
}

function deleteDrug(state, id){
    return {...state,drugs : state.drugs.filter(drug => drug.id !== id)};
}

function addSupplier(state,payload){

    // console.log('suppliers in the begging', payload);
    if(!state.suppliers){
        state.suppliers = [payload];
        //console.log('suppliers',state.suppliers);
        return state;
    }
    if(!state.suppliers.find(iter => iter === payload)){
        return {...state,suppliers : [...state.suppliers,payload]}
    }

    return {...state,suppliers : state.suppliers.filter(iter => iter !== payload)};
}

function setDate(state,date){
    return {...state,date};
}

function setTime(state,time){
    return {...state,time};
}

function init(){
    return {
        drugs : [],
        suppliers : [],
        date : null,
        time : null
    }
}

export function newOrderReducer(state,action){

    switch(action.type){
        case ADD_DRUG : return addDrugAmount(state,action.payload);
        case DELETE_DRUG : return deleteDrug(state, action.id);
        case ADD_SUPPLIER : return addSupplier(state,action.payload);
        case SET_DATE : return setDate(state,action.date);
        case SET_TIME : return setTime(state,action.time);
        case INIT : return init();
        default : throw new Error(" new order reducer error");
    }
}