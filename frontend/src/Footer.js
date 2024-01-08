import React from 'react';
import './css/Footer.css'; 
import { ReactComponent as HeartSolid } from './images/heart-solid.svg';
import { ReactComponent as UserSolid } from './images/user-solid.svg';
import { ReactComponent as CartSolid } from './images/cart-shopping-solid.svg';
import { useNavigate } from 'react-router-dom';

const Footer = () => {
  const navigate = useNavigate();

  const handleButtonClick = (path) => {
    navigate(path);
   };

  return (
    <footer className="footer">
      <HeartSolid className='footer-icon' onClick={() => handleButtonClick(`/Wishlist`)}></HeartSolid>
      <CartSolid className='footer-icon' onClick={() => handleButtonClick(`/Cart`)}></CartSolid>
      <UserSolid className='footer-icon' onClick={() => handleButtonClick(`/Profile`)}></UserSolid>
    </footer>
  );
};

export default Footer;
