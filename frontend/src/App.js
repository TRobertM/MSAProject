// App.js

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainPage2 from './MainPage2';
import Women from './Women';
import SneakersComponent from './SneakersComponent';
import SneakerInformation from './SneakerInformation';
import './css/Footer.css';
import './css/App.css';
import RegistrationPage from './RegisterPage';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainPage2 />} />
        <Route path="/Register" element={<RegistrationPage />} />
        <Route path="/Women" element={<Women />} />
        <Route path="/Sneakers" element={<SneakersComponent />} />
        <Route path="/Sneakrs/:id" element={<SneakerInformation />} />
      </Routes>
    </Router>
  );
};

export default App;