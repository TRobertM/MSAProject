import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './css/SneakersComponent.css';
import Footer from './Footer'
import './css/Footer.css'

const SneakersComponent = () => {
 const [sneakers, setSneakers] = useState([]);
 const navigate = useNavigate();

 const handleBoxClick = (path) => {
  navigate(path);
 };

 useEffect(() => {
  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8080/p/sneakers');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const sneakersData = await response.json();
      setSneakers(sneakersData);
    } catch (error) {
      console.error('Error fetching sneakers:', error);
    }
  };

  fetchData();
 }, []);

 return (
  <div className = "back-ground">
  <div className="sneakers-container">
    {sneakers.map((sneaker) => (
      <div key={sneaker.id} className="sneaker-box" onClick={() => handleBoxClick(`/${sneaker.id}`)}>
        <img src={sneaker.image} alt={sneaker.name} className="sneaker-image" />
        <div className="sneaker-details">
          <h3>{sneaker.name}</h3>
          <p>{`Price: $${sneaker.price}`}</p>
        </div>
      </div>
    ))}
  </div>
  <Footer/>
  </div>
 );
};

export default SneakersComponent;
