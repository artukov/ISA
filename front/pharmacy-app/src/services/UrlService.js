const urlStart = 'http://localhost:8080';
const urlAuthLogin = '/auth/login';
const urlRefreshToken = 'auth/refresh';
const urlPharmacy = '/pharmacy';
const urlDermatologist = '/dermatologist'
const urlPharmacist = '/pharmacist';
const urlDrug = '/drug'
const urlExamination = '/examination'
const urlReservation = '/reservation'

const urlGetPharmacy = urlPharmacy + '/find/';
const urlGetPhramacyDermatologists = urlDermatologist + '/findByPharmacy/';
const urlGetPharmacyPharmacists = urlPharmacist + '/findAllByPharmacy/';
const urlGetPharmacyDrugs = urlDrug +'/allPharmacyDrugs/';
const urlGetPharmacyFreeExaminations = urlExamination + '/freePharmacyExam/'


const urlGetDermatologist = urlDermatologist + "/findByID/";

const urlNewReservation = urlReservation + "/new/";
const urlNewExamination = urlExamination + "/new";
const urlModifyExamination = urlExamination + "/modify/";

export {
    urlStart,
    urlAuthLogin,
    urlRefreshToken,
    urlGetPharmacy,
    urlGetPhramacyDermatologists,
    urlGetPharmacyPharmacists,
    urlGetPharmacyDrugs,
    urlGetPharmacyFreeExaminations,
    urlGetDermatologist,
    urlNewReservation,
    urlNewExamination,
    urlModifyExamination
}