
function init(){
    return {
        drugSpecification : {},
        substituteDrugs : []
    }
}

export default function drugReducer(state,action){

    switch(action.type) {
        case SET_NAME : return setName(state,action.name);
        case SET_CODE : return setCode(state,action.code);
        case SET_TYPE : return setType(state,action.drugType);
        case SET_SHAPE : return setShape(state,action.shape);
        case SET_MANUFACTURER :  return setManufacturer(state,action.manufacturer);
        case SET_RECEIPT : return setReceipt(state,action.receipt);
        case SET_SIDE_EFFECTS : return setSideEffects(state,action.sideEffects);
        case SET_COMPOSITION : return setComposition(state,action.composition);
        case SET_RECOMMENDED_CONSUMPTION : return setRecommendeConsumption(state,action.recommendedConsumption);
        case SET_SUBSTITUTE_DRUGS : return setSubstituteDrugs(state,action.substituteDrug);
        default : return init();
    }
}

export const SET_NAME  = "SET_NAME ";
export const SET_CODE = "SET_CODE";
export const SET_TYPE = "SET_TYPE";
export const SET_SHAPE = "SET_SHAPE";
export const SET_MANUFACTURER = "SET_MANUFACTURER";
export const SET_RECEIPT = "SET_RECEIPT";
export const SET_SIDE_EFFECTS = "SET_SIDE_EFFECTS";
export const SET_COMPOSITION  = "SET_COMPOSITION";
export const SET_RECOMMENDED_CONSUMPTION  = "SET_RECOMMENDED_CONSUMPTION ";
export const SET_SUBSTITUTE_DRUGS = "SET_SUBSTITUTE_DRUGS";


function setName(state,name){
    return {
        ...state,
        name
    }
}

function setCode(state,code){
    return {
        ...state,
        code
    }

}

function setType(state,type){
    return {
        ...state,
        type
    }
}

function setShape(state,shape){
    return {
        ...state,
        shape
    }

}

function setManufacturer(state,manufacturer){
    return {
        ...state,
        manufacturer
    }

}

function setSideEffects(state,sideEffects){
    return {
        ...state,
        drugSpecification : {
            ...state.drugSpecification,
            sideEffects,
        }
    }
}

function setComposition(state,composition){
    return {
        ...state,
        drugSpecification : {
            ...state.drugSpecification,
            composition
        }
    }
}

function setReceipt(state,receipt){
    return {
        ...state,
        receipt
    }
}

function setRecommendeConsumption(state,recommendedConsumption){
    return {
        ...state,
        drugSpecification : {
            ...state.drugSpecification ,
            recommendedConsumption
        }
    }
}

function  setSubstituteDrugs(state,substituteDrug){
    if(state.substituteDrugs.length === 0){
        return {
            ...state,
            substituteDrugs : [substituteDrug]
        }
    }

    if(state.substituteDrugs.find(drug => drug === substituteDrug)){
        return {
            ...state,
            substituteDrugs : state.substituteDrugs.filter(drug => drug !== substituteDrug)
        }
    }

    return {
        ...state,
        substituteDrugs : [
            ...state.substituteDrugs,
            substituteDrug
        ]
    }

}