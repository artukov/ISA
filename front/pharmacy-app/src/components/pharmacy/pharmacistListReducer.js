function setPharmacists(state,pharmacists){
    // console.table(pharmacists);
        return {
            ...state,
            pharmacists
        }
}
function addPharmacist(state,pharmacist) {
    if(state.pharmacists.length === 0){
        return {
            ...state,
            pharmacists : [pharmacist]
        }
    }
    return {
        ...state,
        pharmacists : [
            ...state.pharmacists,
            pharmacist
        ]
    }
    
}

export default function pharmacistListReducer(state,action){
    switch(action.type){
        case SET_PHARMACISTS : return setPharmacists(state,action.pharmacists);
        case ADD_PHARMACIST : return addPharmacist(state,action.pharmacist);
        default : return;
    }
}

export const SET_PHARMACISTS = "SET_PHARMACISTS"
export const ADD_PHARMACIST = "ADD_PHARMACIST";