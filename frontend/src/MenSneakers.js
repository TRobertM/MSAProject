import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import './css/Footer.css'
import Footer from './Footer.js';

const MenSneakers = () => {
    const [sneakers, setSneakers] = useState([]);
    const location = useLocation();
    const key = location.state.key;
    const navigate = useNavigate();

    const handleBoxClick = (path, key) => {
     navigate(path, {state: { key: key }});
    };

    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await fetch(`http://localhost:8080/p/sneakers/g/${key}`);
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
        <div className="page">
        <div className="sneaker-page">
        <div className='header-wrapper'>
          <div className='header-bar'>
            <p onClick={() => handleBoxClick('/Sneakers')}>Home</p>
            <p key='male' onClick={() => handleBoxClick('/Men', 'male')}>Men</p>
            <p key='female' onClick={() => handleBoxClick('/Women', 'female')}>Women</p>
          </div>
        </div>
          <h1 className="logo">SS</h1>
          <div className="sneakers-container">
            {sneakers.map((sneaker) => (
              <div key={sneaker.id} className="sneaker-box" onClick={() => handleBoxClick(`/${sneaker.id}`, null)}>
                <img src={sneaker.image} alt={sneaker.name} className="sneaker-image" />
                <div className="sneaker-details">
                  <h3>{sneaker.name}</h3>
                  <p>{`Price: $${sneaker.price}`}</p>
                </div>
              </div>
            ))}
              <Footer />
          </div>
        </div>
        </div>
       );
};

export default MenSneakers;
