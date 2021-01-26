import axios from 'axios';
// import refreshToken from '../services/RefreshToken';
import {urlStart, urlAuthLogin} from '../services/UrlService';


const instance = axios.create({
    baseURL: urlStart
});

if(localStorage.getItem('token') === null){
    localStorage.setItem('token', JSON.stringify('token'));
}

instance.defaults.headers.Authorization = 'Bearer ' + JSON.parse(localStorage.getItem('token')).token;
//instance.defaults.headers.common['Content-Type'] = 'application/json';
instance.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8';

instance.defaults.headers.get['Accept'] = 'application/json';

instance.defaults.headers.put['Content-Type'] = 'application/json; charset=UTF-8';
instance.defaults.headers.put['Accept'] = 'application/json';

instance.interceptors.request.use(
    config => {
        //console.log(config);
        if(config.url === urlAuthLogin){
            config.headers.Authorization = '';
            return config;
        }
        if(config.headers.Authorization){
            // console.log(JSON.parse(localStorage.getItem('token')));
            const jwt = JSON.parse(localStorage.getItem('token'));
            // console.log('expiresDate:',jwt.expiresDate);
            
            // const tokenTime = new Date(jwt.expiresDate);
            // const now = new Date();
            // if( tokenTime.getTime() - now.getTime() > 499000 ){
            //     refreshToken();
            // }
            
            if(jwt.token){
                config.headers.Authorization = 'Bearer ' + jwt.token;
            }
        }
        
        return config;
    },
    error => Promise.reject(error)
    
);

instance.interceptors.response.use( 
    response  => {

        return response
    },
    error =>{
       
        if((error.response.status !== undefined) && (error.response.status === 401)){
            // console.log('intercepter error \n', error.response);
            // const resault = refreshToken();
            // return Promise.resolve(resault);
        }
        return Promise.reject(error);
    }
 );


export  {instance as axiosConfig, instance as default};