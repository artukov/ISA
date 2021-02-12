


function setPharmacy(state,pharmacyID){
    return { ...state, pharmacyID}
}

function setDermaNotInPharmacy(state,unemployed){
    return {
        ...state,
        unemployed
    }
}

function addNewDermatologist(state,dermatologist){

    return {
        ...state,
        employed : [
            ...state.employed,
            dermatologist
        ]
    }
}

function openAddForm(state){
    return {
        ...state,
        showAddForm : true
    }
}

function closeAddForm(state){
    return {
        ...state,
        showAddForm : false
    }
}

function setDermatologistsInPharmacy(state,employed){
    return {
        ...state,
        employed,
      
    }
}

function deleteDermatologist(state,id){

    return {
        ...state,
        employed : state.employed.filter(dermatologist => dermatologist.id !== id)
    }

}

export default function dermatologistReducer(state, action) {

    switch(action.type){
        case SET_PHARMACY : return setPharmacy(state,action.pharmacyID);
        case SET_DERMA_NOT_IN_PHARMACY : return setDermaNotInPharmacy(state,action.unemployed);
        case ADD_DERMATOLOGIST : return addNewDermatologist(state,action.dermatologist);
        case OPEN_ADD_FORM : return openAddForm(state);
        case CLOSE_ADD_FORM : return closeAddForm(state);
        case SET_DERMATOLOGISTS : return setDermatologistsInPharmacy(state,action.employed);
        case DELETE_DERMATOLOGIST : return deleteDermatologist(state,action.id);
        default : throw new Error("dermatologist reducer error");
    }
}

export const SET_PHARMACY = "SET_PHARMACY";
export const SET_DERMA_NOT_IN_PHARMACY = "SET_DERMA_NOT_IN_PHARMACY";
export const ADD_DERMATOLOGIST = "ADD_DERMATOLOGIST";
export const OPEN_ADD_FORM = "OPEN_ADD_FORM";
export const CLOSE_ADD_FORM = "CLOSE_ADD_FORM";
export const SET_DERMATOLOGISTS = "SET_DERMATOLOGISTS";
export const DELETE_DERMATOLOGIST = "DELETE_DERMATOLOGIST";
