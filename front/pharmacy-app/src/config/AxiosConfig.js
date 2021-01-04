import axios from 'axios';
import {urlStart, urlAuthLogin} from '../services/UrlService';


const instance = axios.create({
    baseURL: urlStart
});

instance.defaults.headers.Authorization = 'Bearer ' + JSON.parse(localStorage.getItem('token')).token;

instance.interceptors.request.use(
    config => {
        //console.log(config);
        if(config.url === urlAuthLogin){
            config.headers.Authorization = '';
            return config;
        }
        if(!config.headers.Authorization){
            const token = JSON.parse(localStorage.getItem('token')).token;

            if(token){
                config.headers.Authorization = 'Bearer ' + token;
            }
        }
        
        return config;
    },
    error => Promise.reject(error)
    
);

export default instance;