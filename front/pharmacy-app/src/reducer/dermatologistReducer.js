
function addDermatologist(){

}

function deleteDermatologist(){

}

function dermatologistReducer(state, action){

    switch(action.type){
        case  'hello' : console.log('hello from reducer' + state); break;
        case   'add' : addDermatologist(); break;
        case  "delete" : deleteDermatologist(); break;
        default : throw new Error();
    }

}

export default dermatologistReducer;