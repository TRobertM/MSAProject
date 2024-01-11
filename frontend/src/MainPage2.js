import './css/MainPage.css';
import mainShoe from './images/mainShoe.png';

function MainPage2() {
  return (
    <div className={`MainPage2 ${isSmallScreen ? 'small-screen' : ''}`}>
      {isSmallScreen ? (
      <>
        <button onClick={toggleSidebar} className="sidebar-toggle">
          &#9776;
        </button>

        <div className={`sidebar ${isSidebarOpen ? 'open' : ''}`}>
          <a href="/">Home</a>
          <a href="/Men">Men</a>
          <a href="/Women">Women</a>
          <a href="#">Sales</a>
          <a href="#">About Us</a>
          <a href="#">Log In</a>
          <button className="close-sidebar-button" onClick={closeSidebar}>
              X
            </button>
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