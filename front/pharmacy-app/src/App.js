import { BrowserRouter as Router, Switch, Route, Protected } from 'react-router-dom';
import './css/global.css';

import HomePage from './components/homePage';
import LoginPage from './components/loginPage';
import RegistrationPage from './components/registrationPage';
import PharmacyComponent from './components/pharmacy/PharmacyComponent';
import UserProfile from './components/view/userProfile'
import PharmacyAdminPage from './components/pharmacyAdmin/PharmacyAdminPage';
import CurrentUserContextProvider, { CurrentUserContext } from './components/context/CurrentUserContext';
import SupplierPage from './components/supplier/SupplierPage';
import DermatologistPage from './components/dermatologist/DermatologistPage';
import Pharmacistpage from './components/pharmacist/Pharmacistpage';

function App() {
  return (
    // <CurrentUserContextProvider>
    <Router>
      <Switch>
        <Route path='/login' exact component={LoginPage} />
        <Route path='/registration' exact component={RegistrationPage} />

        <Route path={['/','/home']} exact component={HomePage} />

        <Route path='/pharmacy' exact component={PharmacyComponent}></Route>
        <Route path='/pharmacyAdmin' exact component={PharmacyAdminPage}></Route>
        <Route path="/supplier" exact component={SupplierPage}></Route>
        <Route path="/dermatologist" exact component={DermatologistPage}></Route>
        <Route path="/pharmacist" exact component={Pharmacistpage}></Route>
        {/* <Route path='/'>404 not here sry</Route> */}

      </Switch>
    </Router>
    // </CurrentUserContextProvider>
  );
}

export default App;
