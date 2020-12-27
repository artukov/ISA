import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import './css/global.css';

import HomePage from './components/homePage';
import LoginPage from './components/loginPage';
import RegistrationPage from './components/registrationPage';

function App() {
  return (
    <Router>
      <Switch>
        <Route path='/login' exact component={LoginPage} />
        <Route path='/home' exact component={HomePage} />
        <Route path='/registration' exact component={RegistrationPage} />
        <Route path='/'>404 not here sry</Route>
      </Switch>
    </Router>
  );
}

export default App;
