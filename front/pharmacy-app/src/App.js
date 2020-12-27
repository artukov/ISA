import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import HomePage from './components/homePage';
import LoginPage from './components/loginPage';
import RegistrationPage from './components/registrationPage';

function App() {
  return (
    <Router>
      <Switch>
        <Route path='/' exact component={HomePage} />
        <Route path='/login' exact component={LoginPage} />
        <Route path='/registration' exact component={RegistrationPage} />
      </Switch>
    </Router>
  );
}

export default App;
