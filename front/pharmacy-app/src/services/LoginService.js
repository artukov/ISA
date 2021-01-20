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

        let expiresDate = new Date();
        let time = expiresDate.getTime();
        expiresDate.setTime(res.data.expiresIn + time);
        //console.log(expiresDate);
        token.setToken(res.data.accessToken, res.data.expiresIn,expiresDate);    
        console.log('token after login', token);

        localStorage.setItem('token', JSON.stringify(token)); 
       
        //window.location = "/home"
    })
    .catch(err => {
        /* console.log(err.response.status);
        console.log(err.response); */
        alert(err.response.data); 

    });

 
}

export default login;