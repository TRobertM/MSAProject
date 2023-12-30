import React, { useState, useEffect } from 'react';
import SneakerBox from './SneakerBox';



function Men() {
  /*
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

  */
};

export default Men;