const urlStart = 'http://localhost:8080';
const urlAuthLogin = '/auth/login';
const urlPharmacy = '/pharmacy';
const urlDermatologist = '/dermatologist'
const urlPharmacist = 'pharmacist';

const urlGetPharmacy = urlPharmacy + '/find/';
const urlGetPhramacyDermatologists = urlDermatologist + '/findByPharmacy/';
const urlGetPharmacyPharmacists = urlPharmacist + '/findAllByPharmacy/';

export {
    urlStart,
    urlAuthLogin,
    urlGetPharmacy,
    urlGetPhramacyDermatologists,
    urlGetPharmacyPharmacists
}