// ShoeList.js

import React from 'react';
import ShoeCard from './ShoeCard'; // Make sure to provide the correct path to your ShoeCard component
import './css/ShoeList.css'; // Create a new CSS file for styling the ShoeList component

const ShoeList = ({ shoes }) => {
  return (
    <div className="shoe-list">
      {shoes.map((shoe, index) => (
        <ShoeCard key={index} shoe={shoe} />
      ))}
    </div>
  );
};

export default ShoeList;
