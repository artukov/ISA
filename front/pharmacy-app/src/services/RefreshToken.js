import { axiosConfig } from "../config/AxiosConfig";
import { urlRefreshToken } from "./UrlService";
import token from './TokenService';


export default async function refreshToken(){

    const resault = await axiosConfig.post(urlRefreshToken);
    console.log('refresh resault \n', resault);
    // token = resault.data;
    token.setToken(resault.data.accessToken, resault.data.expiresIn);  
    localStorage.setItem('token', JSON.stringify(token));

    return resault;

    // axiosConfig.post(urlResetToken)
    // .then(res => {
    //     console.log("refresh token",res.data);
    //     const token = res.data;
    //     localStorage.setItem('token', JSON.stringify(token));
    // })
    // .catch(err =>{
    //     console.log(err.response);
    // })

}
