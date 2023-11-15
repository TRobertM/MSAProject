import React from 'react';
import { Link } from 'react-router-dom';
import './css/MainPage.css';
import ShoeList from './ShoeList';
import backgroundImage from './images/BackgroundPink.jpg';
import nike1 from './images/nikeWomen1.jpg';
import nike2 from './images/nikeWomen2.jpg';
import nike3 from './images/nikeWomen3.jpg';

const shoes = [
    { id: 1, name: 'Air Force 1 High', price: 799.99, image: nike1, brand: "Nike"},
    { id: 2, name: 'Court Vision Mid', price: 449.99, image: nike2, brand: "Nike" },
    { id: 3, name: 'SB Pogo Skate', price: 549.99, image: nike3, brand: "Nike" },
    { id: 1, name: 'Air Force 1 High', price: 799.99, image: nike1, brand: "Nike"},
    { id: 3, name: 'SB Pogo Skate', price: 549.99, image: nike3, brand: "Nike" },
    { id: 2, name: 'Court Vision Mid', price: 449.99, image: nike2, brand: "Nike" },
    { id: 2, name: 'Court Vision Mid', price: 449.99, image: nike2, brand: "Nike" },
    { id: 1, name: 'Air Force 1 High', price: 799.99, image: nike1, brand: "Nike"},
    { id: 3, name: 'SB Pogo Skate', price: 549.99, image: nike3, brand: "Nike" },
    { id: 2, name: 'Court Vision Mid', price: 449.99, image: nike2, brand: "Nike" },
    { id: 3, name: 'SB Pogo Skate', price: 549.99, image: nike3, brand: "Nike" },
    { id: 1, name: 'Air Force 1 High', price: 799.99, image: nike1, brand: "Nike"},
    { id: 3, name: 'SB Pogo Skate', price: 549.99, image: nike3, brand: "Nike" },
    { id: 1, name: 'Air Force 1 High', price: 799.99, image: nike1, brand: "Nike"},
    { id: 2, name: 'Court Vision Mid', price: 449.99, image: nike2, brand: "Nike" },
]

function Women() {
  return (
    <div className="Women">
      <div className="navbar">
        <a href="/Men">Men</a>
        <Link to="/Women">Women</Link>
        <Link to="/Men">Men</Link>
        <a href="#">Sales</a>
        <a href="#">About Us</a>
        <a href="#">Log In</a>
      </div>
      <img src={backgroundImage} className="background-image" alt="Background" />
      <div className="header-text">
        <h1 className="header-title">Most Wanted</h1>
      </div>
      <div>
        <ShoeList shoes={shoes} />
      </div>
    </div>
  );
};

export default Women;