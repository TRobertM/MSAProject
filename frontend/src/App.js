import React from 'react';
import backgroundImage from './images/background2.jpg'
import './css/App.css';

function App() {
  return (
    <div className="App">
      <div className="navbar">
        <a href="#">Men</a>
        <a href="#">Women</a>
        <a href="#">Home</a>
        <a href="#">Sales</a>
        <a href="#">About Us</a>
      </div>
      <img src={backgroundImage} className="background-image" alt="Background" />
      <div className="header-text">
        <h1 className="header-title">Most Wanted</h1>
      </div>
      
      
    </div>
  );
}

export default App;