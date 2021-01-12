const urlStart = 'http://localhost:8080';
const urlAuthLogin = '/auth/login';
const urlPharmacy = '/pharmacy';
const urlDermatologist = '/dermatologist'

const urlGetPharmacy = urlPharmacy + '/find/';
const urlGetPhramacyDermatologists = urlDermatologist + '/findByPharmacy/';


export {
    urlStart,
    urlAuthLogin,
    urlGetPharmacy,
    urlGetPhramacyDermatologists
}