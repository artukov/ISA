

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

export default function absencerequestReducers(state, action) {
    
    switch (action.type) {
        case SET_START_DATE: return setStartDate(state, action.startDate);
        case SET_END_DATE: return setEndDate(state, action.endDate);
        default: return;
    }
}

export const SET_START_DATE = 'SET_START_DATE';
export const SET_END_DATE = 'SET_END_DATE';