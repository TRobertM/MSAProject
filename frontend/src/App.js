// App.js

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainPage2 from './MainPage2';
import SneakersComponent from './SneakersComponent';
import SneakerInformation from './SneakerInformation';
import './css/Footer.css';
import './css/App.css';
import RegistrationPage from './RegisterPage';
import LoginPage from './LoginPage';
import MenSneakers from './MenSneakers';
import WomenSneakers from './WomenSneakers';
import WishlistComponent from './WishlistComponent';
import ProfileComponent from './ProfileComponent';
import CartComponent from './CartComponent';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainPage2 />} />
        <Route path="/Login" element={<LoginPage />} />
        <Route path="/Register" element={<RegistrationPage />} />
        <Route path="/Sneakers" element={<SneakersComponent />} />
        <Route path="/:id" element={<SneakerInformation />} />
        <Route path="/Men" element={<MenSneakers />} />
        <Route path="/Women" element={<WomenSneakers />} />
        <Route path="/Wishlist" element={<WishlistComponent />} />
        <Route path="/Profile" element={<ProfileComponent />} />
        <Route path="/Cart" element={<CartComponent />} /> 
      </Routes>
    </Router>
  );
};

export default App;