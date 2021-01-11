import {urlAuthLogin} from './UrlService'
import axiosConfig from '../config/AxiosConfig';
import token from './TokenService'

function login(email,password){

    const user = {
        email,
        password
    }

    console.log('token before logion \n', token);
    axiosConfig.post(urlAuthLogin,user)
    .then(res => {

        token.setToken(res.data.accessToken, res.data.expiresIn);    
        console.log('token after login', token);

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