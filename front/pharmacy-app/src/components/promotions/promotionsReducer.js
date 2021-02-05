function setStartDate(state,startDate) {
    return {
        ...state,
        startDate
    }
}

function setStartTime(state,startTime){
    return {
        ...state,
        startTime
    }
}

function setEndDate(state,endDate) {
    return {
        ...state,
        endDate,
    }
}

function setEndTime(state,endTime){
    return {
        ...state,
        endTime,
    }
}

function setContent(state,content) {
    return {
        ...state,
        content,
    }
}

function setPharmacy(state,pharmacyID){
    return {
        ...state,
        pharmacyID
    }
}

function init() {
    return {
        startDate : null,
        startTime : null,
        endDate : null,
        endTime : null,
        content : null,
        pharmacyID : null
    }
}

export default function promotionsReducer(state,action) {
    switch(action.type){
        case SET_START_DATE : return setStartDate(state,action.startDate);
        case SET_START_TIME : return setStartTime(state,action.startTime);
        case SET_END_DATE : return setEndDate(state,action.endDate);
        case SET_END_TIME : return setEndTime(state,action.endTime);
        case SET_CONTENT : return setContent(state,action.content);
        case SET_PHARMACY : return setPharmacy(state,action.id);
        default : return init();
    }
}

export const SET_START_DATE = "SET_START_DATE";
export const SET_START_TIME = "SET_START_TIME";
export const SET_END_DATE = "SET_END_DATE";
export const SET_END_TIME = "SET_END_TIME ";
export const SET_CONTENT = "SET_CONTENT";
export const SET_PHARMACY = "SET_PHARMACY";
