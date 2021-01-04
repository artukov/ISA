import {urlAuthLogin} from './UrlService'
import axiosConfig from '../config/AxiosConfig';
import token from './TokenService'

async function login(email,password){

    const user = {
        email,
        password
    }


    await axiosConfig.post(urlAuthLogin,user)
    .then(res => {

        token.setToken(res.data.accessToken, res.data.expiresIn);    

        localStorage.setItem('token', JSON.stringify(token)); 
       
        window.location = "/home"
    })
    .catch(err => {
        /* console.log(err.response.status);
        console.log(err.response); */
        alert(err.response.data); 

    });

 
}

export default login;