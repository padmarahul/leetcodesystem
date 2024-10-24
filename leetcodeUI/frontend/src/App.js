import React from "react";
import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./pages/LoginComponent";
import Dashboard from "./pages/DashboardComponent/index";
import UpdatePersonalDetails from "./pages/UpdatePersonalDetails"
import Home from "./pages/index"
import 'bootstrap/dist/css/bootstrap.min.css';
import { GlobalStateProvider } from './GlobalStateContext'
import SignUpComponent from "./pages/SignUpComponent";
import ForgotPassword from "./pages/ForgotPassword";
import CodingEditorComponent from "./pages/CodingEditorComponent";
function App() {
  return (
    <>
      <GlobalStateProvider>
        <Router>
          <Routes>
          <Route path='/' element={<Home />} exact />
            <Route path='/login' element={<Login />} exact />
            <Route path='/signup' element={<SignUpComponent />} exact />
            <Route path='/dashboard' element={<Dashboard />} exact />
            <Route path='/updatepersonaldetails/:id' element={<UpdatePersonalDetails />} exact />
            <Route path='/changepassword' element={< ForgotPassword/>} exact />
            <Route path='/coding-editor/:id' element={<CodingEditorComponent />} exact />
          </Routes>
        </Router>
      </GlobalStateProvider>
    </>

  );
};

export default App;
