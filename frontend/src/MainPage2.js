import './css/MainPage.css';
import mainShoe from './images/mainShoe.png';


function MainPage2() {
  return (
    <div className="start-page">
      <img src={mainShoe} alt="Shoe" className="shoe-image" />
      <div className="text-behind">SNEAKERS</div>
      <div className="text-behind-2">STORE</div>
      <button className="bottom-button" ><a href="/Register">GET STARTED</a></button>
    </div>
  );
}

export default MainPage2;