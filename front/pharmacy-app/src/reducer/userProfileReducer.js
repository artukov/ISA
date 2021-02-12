
function setFirtName(state,firstname){
    state.firstname = firstname;
    return state;
}

function setLastname(state,lastname){
    state.lastname = lastname;
    return state;
}

function setEmail(state,email){
    // console.log(state, "\n email \t", email);
    state.email = email;
    return state;
}

function setPassword(state,password){
    state.password = password;
    return state;
}

function setPhoneNumber(state,phoneNumber){
    state.phoneNumber = phoneNumber;
    return state;
}

function setRole(state,role){
    state.role = role;
    return state;
}

function setAddress(state,address){
    // state.address = address;
    // console.log(address);
    state.address = address;
    return state;
}

function init(state,initState){
    state = initState;
    state.address = {}
    return state;
}



function userProfile(state, action){

    switch(action.type){
        case 'firstname' : return setFirtName(state,action.payload); 
        case 'lastname' : return setLastname(state,action.payload);
        case 'email' : return setEmail(state,action.payload);
        case 'password' : return setPassword(state,action.payload);
        case 'phoneNumber' :  return setPhoneNumber(state,action.payload); 
        case 'role' : return setRole(state,action.payload); 
        case 'address' : return setAddress(state,action.address );
        case 'init' : return init(state,action.payload); 
        default : throw Error("user profile reducer error");
    }
}

export default userProfile;