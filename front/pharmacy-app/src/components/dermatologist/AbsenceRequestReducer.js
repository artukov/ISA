

function setStartDate(state, startDate) {
    // state.startDate = startDate;
    // return state;
    return {
        ...state,
        startDate
    }

}

function setEndDate(state, endDate) {
    return {
        ...state,
        endDate
    }
}
function setStartTime(state, startTime) {
    return {
        ...state,
        startTime
    }
}
function setEndTime(state, endTime) {
    return {
        ...state,
        endTime
    }
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

export default function absencerequestReducers(state, action) {
    
    switch (action.type) {
        case SET_START_DATE: return setStartDate(state, action.startDate);
        case SET_END_DATE: return setEndDate(state, action.endDate);
        case SET_START_TIME: return setStartTime(state, action.startTime);
        case SET_END_TIME: return setEndTime(state, action.endTime);
        case INIT : return init(state);
        default: return;
    }
}


export const SET_START_DATE = 'SET_START_DATE';
export const SET_END_DATE = 'SET_END_DATE';
export const SET_START_TIME = 'SET_START_TIME';
export const SET_END_TIME = 'SET_END_TIME';
export const INIT = "INIT";