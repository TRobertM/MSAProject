import React from 'react';

const SneakerBox = ({ sneaker }) => {
  return (
    <div className="sneaker-box">
      <img src={sneaker.image} alt={sneaker.name} />
      <h3>{sneaker.name}</h3>
      <p>${sneaker.price}</p>
    </div>
  );
};

export default SneakerBox;