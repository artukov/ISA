
class TokenService  {
    constructor(){
        
        this.token = null;
        this.expiresIn = null;
    }

    setToken(token, expiresIn){
        this.token = token;
        this.expiresIn = expiresIn;
    }

    getToken(){
        return this.token;
    }

}

const token = new TokenService(); 


export default token;