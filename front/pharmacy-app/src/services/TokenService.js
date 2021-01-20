
class TokenService  {
    constructor(){
        
        this.token = null;
        this.expiresIn = null;
        this.expiresDate = null;
    }

    setToken(token, expiresIn, expiresDate){
        this.token = token;
        this.expiresIn = expiresIn;
        this.expiresDate = expiresDate;
    }

    getToken(){
        return this.token;
    }

    getExpiresIn(){
        return this.expiresIn;
    }

    getExpiresDate(){
        return this.expiresDate;
    }

}

const token = new TokenService(); 


export default token;