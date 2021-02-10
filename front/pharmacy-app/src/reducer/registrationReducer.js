function setFirstname(state,firstname) {
    return {
        ...state,
        firstname
    }
}

function setLastname(state,lastname) {
    return {
        ...state,
        lastname
    }    
}

function setPhonenumber(state,phoneNumber){
    return {
        ...state,
        phoneNumber
    }
}

function setAddress(state,address){
    return {
        ...state,
        address
    }
}

function setEmail(state,email){
    return {
        ...state,
        email
    }
}

function setPassword(state,password){
    return{
        ...state,
        password
    }
}

function init() {
    return {};
}

export default function registrationReducer(state,action) {
    switch(action.type){
        case SET_FIRSTNAME : return setFirstname(state,action.firstname);
        case SET_LASTNAME : return setLastname(state,action.lastname);
        case SET_PHONENUMBER : return setPhonenumber(state,action.phoneNumber);
        case SET_ADDRESS : return setAddress(state,action.address);
        case SET_EMAIL : return setEmail(state,action.email);
        case SET_PASSWORD : return setPassword(state,action.password);
        default : return init();
    }
}

export const SET_FIRSTNAME = "SET_FIRSTNAME";
export const SET_LASTNAME = "SET_LASTNAME";
export const SET_PHONENUMBER = "SET_PHONENUMBER";
export const SET_ADDRESS = "SET_ADDRESS";
export const SET_EMAIL = "SET_EMAIL";
export const SET_PASSWORD = "SET_PASSWORD";
