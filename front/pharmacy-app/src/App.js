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
import ResetPasswordPage from './components/reset-password/ResetPasswordPage';
import SystemAdminPage from './components/system-admin/SystemAdminPage';
import PatientPage from './components/patient/PatientPage';
import  {Nav, Navbar, Button} from 'react-bootstrap';
import useCurrentUser from './hooks/loadCurrentUser';
import { useEffect } from 'react';
import { axiosConfig } from './config/AxiosConfig';

function App() {

  const signOut = async () => {
        try{
          await axiosConfig.post('/auth/logout');
          localStorage.setItem('token', JSON.stringify('token'));

        }
        catch(err){

        }
  }
  const redirectToUserPage = async () =>{
    try{
      await axiosConfig.get('/user/userPage');
    }
    catch(err){
      console.log(err.response);
    }
  }

  const currentUser = useCurrentUser();

  useEffect(() => {
    
  }, [currentUser])

  return (
    // <CurrentUserContextProvider>
    <div>
      <Navbar  bg="light" expand="lg" >
          <Nav  className="mr-auto">
              <Nav.Link onClick={()=> window.location = '/home'}>Home</Nav.Link>
               <Nav.Link onClick={()=> redirectToUserPage()}>User</Nav.Link>
          </Nav>
          
          <Nav>
              <Button onClick={()=>window.location = '/login'}>Login</Button>
          </Nav>
          <Nav>
            <Button variant="secondary" onClick={()=>window.location = '/registration'}>Sign in</Button>
          </Nav>
          <Nav>
            <Nav.Link  onClick={() => {signOut()}}>Sign out</Nav.Link>
          </Nav>
      </Navbar>
        <Router>
        <Switch>
            <Route path='/login' exact component={LoginPage} />
            <Route path='/registration' exact component={RegistrationPage} />

            <Route path={['/','/home']} exact component={HomePage} />

            <Route path='/pharmacy/:id' exact component={PharmacyComponent}></Route>
            <Route path='/pharmacyAdmin' exact component={PharmacyAdminPage}></Route>
            <Route path="/supplier" exact component={SupplierPage}></Route>
            <Route path="/dermatologist" exact component={DermatologistPage}></Route>
            <Route path="/pharmacist" exact component={Pharmacistpage}></Route>
            <Route path='/reset-password' exact component={ResetPasswordPage}></Route>
            <Route path="/systemAdmin" exact component={SystemAdminPage}></Route>
            <Route path="/patient" exact component={PatientPage}></Route>
            {/* <Route path='/'>404 not here sry</Route> */}

        </Switch>
        </Router>
    </div>
    // </CurrentUserContextProvider>
  );
}

export default App;
