import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import HttpService from "./services/HttpService";
import UserService from "./services/UserService";


// const renderApp = () => ReactDOM.render(<App />, document.getElementById("app"));
// renderApp();
//UserService.initKeycloak(renderApp);
//HttpService.configure();
const root = ReactDOM.createRoot(document.getElementById('app'));
root.render(
  //<Provider store={store}>
        <App />
  //</Provider>
  // <React.StrictMode>

  // </React.StrictMode>
);

reportWebVitals();
