import './css/App.css';
import shoeimage from './images/nike.png'

const Shoe = () => {
  return (
    <div className='shoecontainer'>
      <img src={shoeimage} className='shoeimage'/>
    </div>
  )
}

function Mainpage() {
  return (
    <div className="background">
      <Shoe />
    </div>
  );
}

export default Mainpage;
