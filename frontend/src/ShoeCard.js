// ShoeCard.js

import React from 'react';
import './css/ShoeCard.css'; // Import the CSS file
import nike1 from './images/nike1.jpg'

const ShoeCard = ({ shoe }) => {
  const { name, brand, price, image } = shoe;

  return (
    <div className="container">
      <div className="images">
        <img src={image} />
      </div>
      <div className="product">
        <h1>{brand} {name}</h1>
        <h2>${price}</h2>
        <div className="buttons">
          <button className="add">Add to Cart</button>
          <button className="like"><span>♥</span></button>
        </div>
      </div>
    </div>
  );
};

export default ShoeCard;
