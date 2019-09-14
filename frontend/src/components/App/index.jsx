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
import QRCode from 'components/QRCode';

export const ReduxStore = createStore(reducer, composeWithDevTools(applyMiddleware(thunk)));

const App = () => (
  <>
    <Provider store={ReduxStore}>
      <BrowserRouter>
        <Route path="/" component={Logo} />
        <Layout>
          <Switch>
            <Route exact path="/" component={CashBox} />
            <Route path="/qr" component={QRCode} />
          </Switch>
        </Layout>
      </BrowserRouter>
    </Provider>
  </>
);

export default App;
