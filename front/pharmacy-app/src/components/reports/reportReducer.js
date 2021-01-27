export const SET_YEARS = "SET_YEARS";
export const SET_TIMESPAM_APPOINTMENTS = "SET_TIMESPAM_APPOINTMENTS";
export const SET_TIMESPAM_DRUGS  = "SET_TIMESPAM_DRUGS";
export const SET_APPOINTMENTS = "SET_APPOINTMENTS";

function setYears(state,years){
    // console.log(years);
    return {...state,years};
}

function setTimeSpamAppointments(state,timespam){

    return {
        ...state,
        appointments : {
            // stats : [...state.appointments.stats],
            ...state.appointments,
            timespam : timespam
        }
    }

}

function setTimeSpamDrugs(state,timespam){

    return {
        ...state,
        drugs : {
            stats : [...state.drugs.stats],
            timespam : timespam
        }
    }

}

function setAppointments(state, appointments){

    return {
        ...state,
        appointments : {
            ...state.appointments,
            stats : appointments,
        }
    }
    
}

export function reportReducer(state,action){

    switch(action.type){
        case SET_YEARS : return setYears(state,action.years);
        case SET_TIMESPAM_APPOINTMENTS : return setTimeSpamAppointments(state,action.timespam);
        case SET_TIMESPAM_DRUGS : return setTimeSpamDrugs(state,action.timespam);
        case SET_APPOINTMENTS : return setAppointments(state, action.appointments);
        default : throw new Error("report reducer error");
    }
} 