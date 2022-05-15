import "./App.css";
import Landloard from "./components/landloard/landloard";
import Welcome from "./components/helper/Welcome";
import RenderOnAnonymous from "./components/helper/RenderOnAnonymous";
import RenderOnAuthenticated from "./components/helper/RenderOnAuthenticated";

import StoreService from "./services/StoreService";
import { Provider } from "react-redux";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  const store = StoreService.setup();
  return (
    <Provider store={store}>
      <BrowserRouter>
        <Routes>
          <Route
            path="/landloard"
            element={
              <RenderOnAuthenticated>
                <Landloard />
              </RenderOnAuthenticated>
            }
          />
          <Route
            path="/"
            element={
              <RenderOnAnonymous>
                <Welcome />
              </RenderOnAnonymous>
            }
          />
        </Routes>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
