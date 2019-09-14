import React from 'react';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { Provider } from 'react-redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import reducer from 'reducers';
import Logo from 'components/Logo';
import Layout from 'components/Layout';
import CashBox from 'components/CashBox';

const store = createStore(reducer, composeWithDevTools(applyMiddleware(thunk)));

const App = () => (
  <>
    <Provider store={store}>
      <BrowserRouter>
        <Route path="/" component={Logo} />
        <Layout>
          <Switch>
            <Route path="/" component={CashBox} />
          </Switch>
        </Layout>
      </BrowserRouter>
    </Provider>
  </>
);

export default App;
