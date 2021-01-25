const urlStart = 'http://localhost:8080';
const urlAuthLogin = '/auth/login';
const urlRefreshToken = 'auth/refresh';
const urlPharmacy = '/pharmacy';
const urlDermatologist = '/dermatologist'
const urlPharmacist = '/pharmacist';
const urlDrug = '/drug';
const urlExamination = '/examination';
const urlReservation = '/reservation';
const urlSupplyOrder = '/supplyorder';

const urlGetPharmacy = urlPharmacy + '/find/';
const urlGetPhramacyDermatologists = urlDermatologist + '/findByPharmacy/';
const urlGetPharmacyPharmacists = urlPharmacist + '/findAllByPharmacy/';
const urlGetPharmacyDrugs = urlDrug +'/allPharmacyDrugs/';
const urlGetPharmacyFreeExaminations = urlExamination + '/freePharmacyExam/'
const urlAddNewDermatologist = urlPharmacy + "/addNewDermatologist/";


const urlGetDermatologist = urlDermatologist + "/findByID/";
const urlDeleteDermaPharmacy = urlDermatologist + "/delete/pharmacy/";
const urlGetDermaNotInPharmacy = urlDermatologist + "/findNotInPharmacy/"

const urlNewReservation = urlReservation + "/new/";
const urlNewExamination = urlExamination + "/new";
const urlModifyExamination = urlExamination + "/modify/";

const urlGetDrugsNotInPharmacy = urlDrug + "/findNotInPharmacy/";
const urlAddNewDrugToPharmacy = urlDrug + "/add/";
const urlDeleteDrugFromPharmacy = urlDrug + "/delete/";
const urlModfiyDrug = urlDrug + "/modify/";

const urlNewPharmacist = urlPharmacist + "/new";
const urlModifyPharmacist = urlPharmacist + "/modify";
const urlDeletePharmacist = urlPharmacist + "/delete/pharmacy/";

const urlGetOrderStatuses = urlSupplyOrder + "/statuses";
const urlGetWithoutOffers = urlSupplyOrder + "/findWithoutOffers/";
const urlGetWithStatus = urlSupplyOrder + "/findWithStatus/";
const urlPostNewOrder = urlSupplyOrder + "/new";

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
    urlModifyExamination,
    urlDeleteDermaPharmacy,
    urlGetDermaNotInPharmacy,
    urlAddNewDermatologist,
    urlGetDrugsNotInPharmacy,
    urlAddNewDrugToPharmacy,
    urlDeleteDrugFromPharmacy,
    urlModfiyDrug,
    urlNewPharmacist,
    urlModifyPharmacist,
    urlDeletePharmacist,
    urlGetOrderStatuses,
    urlGetWithoutOffers,
    urlGetWithStatus,
    urlPostNewOrder
}