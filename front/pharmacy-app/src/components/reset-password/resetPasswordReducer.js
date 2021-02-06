
function setOldPassword(state, oldPassword){
    return {
        ...state,
        oldPassword
    }
}
function setNewPassword(state,newPassword){
    return {
        ...state,
        newPassword
    }
}
    
function init() {
    return {
        oldPassword : '',
        newPassword : '',
    }
}

export default function resetPasswordReducer(state,action){
        switch(action.type){
            case SET_OLD_PASSWORD : return setOldPassword(state,action.oldPassword);
            case SET_NEW_PASSWORD : return setNewPassword(state,action.newPassword);
            default : return init();
        }
}

export const SET_OLD_PASSWORD  = "SET_OLD_PASSWORD";
export const SET_NEW_PASSWORD = "SET_NEW_PASSWORD";