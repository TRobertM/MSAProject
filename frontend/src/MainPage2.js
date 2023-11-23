import React, { useState , useEffect} from 'react';
import backgroundImage from './images/Background2.png'
import './css/MainPage.css';
import ShoeList from './ShoeList';
import nike1 from './images/nike1.jpg'
import nike2 from './images/nike2.png'
import nike3 from './images/nike3.jpg'
import nike4 from './images/nikeWomen1.jpg';
import nike5 from './images/nikeWomen2.jpg';
import nike6 from './images/nikeWomen3.jpg';

const shoes = [
    { id: 1, name: 'Air Force 1 High', price: 799.99, image: nike1, brand: "Nike"},
    { id: 2, name: 'Court Vision Mid', price: 449.99, image: nike4, brand: "Nike" },
    { id: 3, name: 'SB Pogo Skate', price: 549.99, image: nike3, brand: "Nike" },
    { id: 1, name: 'Air Force 1 High', price: 799.99, image: nike2, brand: "Nike"},
    { id: 3, name: 'SB Pogo Skate', price: 549.99, image: nike3, brand: "Nike" },
    { id: 2, name: 'Court Vision Mid', price: 449.99, image: nike5, brand: "Nike" },
    { id: 2, name: 'Court Vision Mid', price: 449.99, image: nike2, brand: "Nike" },
    { id: 1, name: 'Air Force 1 High', price: 799.99, image: nike6, brand: "Nike"},
    { id: 3, name: 'SB Pogo Skate', price: 549.99, image: nike3, brand: "Nike" },
    { id: 2, name: 'Court Vision Mid', price: 449.99, image: nike2, brand: "Nike" },
    { id: 3, name: 'SB Pogo Skate', price: 549.99, image: nike3, brand: "Nike" },
    { id: 1, name: 'Air Force 1 High', price: 799.99, image: nike1, brand: "Nike"},
    { id: 3, name: 'SB Pogo Skate', price: 549.99, image: nike1, brand: "Nike" },
    { id: 1, name: 'Air Force 1 High', price: 799.99, image: nike5, brand: "Nike"},
    { id: 2, name: 'Court Vision Mid', price: 449.99, image: nike2, brand: "Nike" },
]

function MainPage2() {
  const [isSmallScreen, setIsSmallScreen] = useState(window.innerWidth < 600);
  const [isSidebarOpen, setSidebarOpen] = useState(!isSmallScreen);

  useEffect(() => {
    const handleResize = () => {
      const newIsSmallScreen = window.innerWidth < 600;

      setIsSmallScreen(newIsSmallScreen);

      if (!newIsSmallScreen) {
        setSidebarOpen(true); // Show sidebar if the screen is larger than 600 pixels
      }
    };

    window.addEventListener('resize', handleResize);

    // Cleanup the event listener on component unmount
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  const openSidebar = () => {
    setSidebarOpen(true);
  };

  const closeSidebar = () => {
    setSidebarOpen(false);
  };

  const toggleSidebar = () => {
    setSidebarOpen(!isSidebarOpen);
  };

  return (
    <div className={`MainPage2 ${isSmallScreen ? 'small-screen' : ''}`}>
      {isSmallScreen ? (
      <>
        <button onClick={toggleSidebar} className="sidebar-toggle">
          {isSidebarOpen ? 'Close Sidebar' : 'Open Sidebar'}
        </button>

        <div className={`sidebar ${isSidebarOpen ? 'open' : ''}`}>
          <a href="/Men">Men</a>
          <a href="/Women">Women</a>
          <a href="/">Home</a>
          <a href="#">Sales</a>
          <a href="#">About Us</a>
          <a href="#">Log In</a>
        </div>
      </>
    ) : (
      <div className="navbar">
        <a href="/Men">Men</a>
        <a href="/Women">Women</a>
        <a href="/">Home</a>
        <a href="#">Sales</a>
        <a href="#">About Us</a>
        <a href="#">Log In</a>
      </div>
    )}

      <img src={backgroundImage} className="background-image" alt="Background" />
      <div className="header-text">
        <h1 className="header-title">Most Wanted</h1>
      </div>
      <div>
        <ShoeList shoes={shoes} />
      </div>
    </div>
  );
}

export default MainPage2;