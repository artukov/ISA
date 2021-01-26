export const SET_PHARMACY = "SET_PHARMACY";
export const SET_START_DATE = "SET_START_DATE";
export const SET_START_TIME = "SET_START_TIME";
export const SET_END_DATE = "SET_END_DATE";
export const SET_END_TIME = "SET_END_TIME";
export const SET_DRUGS = "SET_DRUGS";
export const INIT = "INIT";
export const SET_STATE = "SET_STATE";

function setStartDate(state,date){
    return {...state, startDate : date};
}

function setStartTime(state,time){
    return {...state, startTime : time};
}

function setEndDate(state,date){
    return {...state, endDate : date};
}

function setEndTime(state,time){
    return {...state, endTime : time};
}

function setPharmacy(state,id) {
    return {...state,pharmacyID : id};
}

function setDrugs(state, payload) {

    // console.log(state.drugs,payload);

    if(!state.drugs){
        return {...state,drugs : [payload]};
    }
    if(state.drugs.find(drug => drug.id === payload.id)){
        return {
            ...state,
            drugs : state.drugs.map(drug => {
                if(drug.id === payload.id)
                    return payload;
                return drug;
            })
        };
    }

    return {...state,drugs : [...state.drugs,payload]};

    
}

function init(state) {

    return {
        startDate : null,
        startTime : null,
        endDate : null,
        endTime : null,
        pharmacyID : null,
        drugs : []
    }
    
}

function setState(state, payload){
    // const [startDate,startTime,] = payload.startDate.split(" ");
    // const [endDate, endTime,] = payload.endDate.split(" ");
    const drugs = payload.drugs.map(drug => {
        return {
            id : drug.drugID,
            name : drug.name,
            price : drug.price
        };
    })

    return {
        ...state,
        id : payload.id,
        startDate :payload.startDate,
        startTime : undefined,
        endDate : payload.endDate,
        endTime : undefined,
        drugs
    }

}

export function priceListReducer(state,action) {

    switch(action.type){
        case SET_START_DATE : return setStartDate(state,action.date);
        case SET_START_TIME : return setStartTime(state,action.time);
        case SET_END_DATE : return setEndDate(state,action.date);
        case SET_END_TIME : return setEndTime(state,action.time);
        case SET_PHARMACY  : return setPharmacy(state,action.id);
        case SET_DRUGS : return setDrugs(state,action.payload);
        case SET_STATE : return setState(state,action.payload);
        case INIT : return init(state);
        default : throw new Error("price list reducer error");
    }
    
}