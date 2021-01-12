import axiosConfig from '../config/AxiosConfig';
import {urlGetPhramacyDermatologists} from '../services/UrlService';

async function loadPharmacyDermatologists (id) {

    let dermatologists = [];

    if(id !== undefined && id !== null)
        await axiosConfig.get(urlGetPhramacyDermatologists + id)
        .then(res => {
            console.log(res);
            dermatologists = res.data;
        })
        .catch(err => alert(err.response.data));

    return dermatologists;
}

export {
    loadPharmacyDermatologists
}