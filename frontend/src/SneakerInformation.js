import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './css/SneakerInformationsCss.css';
import { ReactComponent as HeartRegular } from './images/heart-regular.svg';
import { ReactComponent as HeartSolid } from './images/heart-solid.svg';

const SneakerInformation = () => {
  const { id } = useParams();
  const [inventories, setInventories] = useState([]);
  const [sneaker, setSneaker] = useState("");
  const [selectedColorId, setSelectedColorId] = useState(null);
  const [selectedSizes, setSelectedSizes] = useState([]);
  const [selectedSize, setSelectedSize] = useState(null);
  const [wishlistClicked, setWishlistClicked] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/p/sneakers/${id}`);
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
  
        const inventoriesData = await response.json();
        setInventories(inventoriesData);

        const secondResponse = await fetch(`http://localhost:8080/p/sneakers/i/${id}`)
        if(!secondResponse.ok){
          throw new Error('Error response was not ok');
        }

        const secondData = await secondResponse.json();
        setSneaker(secondData);
      } catch (error) {
        console.error('Error fetching sneakers:', error);
      } 
    };
  
    fetchData();
  }, []);

  const handleCircleClick = (colorId) => {
    setSelectedColorId(colorId);
    const sizes = inventories
      .filter(inventory => inventory.color.id === colorId)
      .map(inventory => inventory.size.number);
    setSelectedSizes(sizes);
    setSelectedSize("");
  };

  const handleSizeClick = (size) => {
    setSelectedSize(size);
  };

  const handleWishlistClick = async () => {
    if(wishlistClicked){
      setWishlistClicked(false);
      const jwtToken = localStorage.getItem('jwtToken');
      try {
        const response = await fetch('http://localhost:8080/customer/wishlist/remove-from-wishlist', {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${jwtToken}`,
          },
          body: JSON.stringify(sneaker),
        });
     
        if (response.status === 400) {
          const errorMessage = await response.text();
          console.error(errorMessage);
          return;
        }
     
        if (response.status === 404) {
          console.error('Wishlist not found');
          return;
        }
     
        if (response.status !== 200) {
          console.error('Error removing item from wishlist');
          return;
        }
     
        const result = await response.text();
        console.log('Successfully removed from wishist:', result);
      } catch (error) {
        console.error('Error removing from wishlist:', error.message);
      }
    } else{
      setWishlistClicked(true);
      const jwtToken = localStorage.getItem('jwtToken');
      try {
        const response = await fetch('http://localhost:8080/customer/wishlist/add-to-wishlist', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${jwtToken}`,
          },
          body: JSON.stringify(sneaker),
        });
     
        if (response.status === 400) {
          const errorMessage = await response.text();
          console.error(errorMessage);
          return;
        }
     
        if (response.status === 404) {
          console.error('Wishlist not found');
          return;
        }
     
        if (response.status !== 200) {
          console.error('Error adding item to wishlist');
          return;
        }
     
        const result = await response.text();
        console.log('Successfully added to wishist:', result);
      } catch (error) {
        console.error('Error adding to wishlist:', error.message);
      }
     };
  }


  const handleOrder = async () => {
    if(selectedSize == "null" || selectedColorId == "null"){
      return;
    }
   
    const inventory = inventories.find(
      (inventory) =>
        inventory.color.id === selectedColorId &&
        inventory.size.number === selectedSize
    );
   
    if (!inventory) {
      console.error('No matching inventory found');
      return;
    }
   
    const inventoryId = inventory.id;
   
    if (!inventoryId) {
      console.error('Invalid inventory ID');
      return;
    }
   
    const jwtToken = localStorage.getItem('jwtToken');
   
    try {
      const response = await fetch('http://localhost:8080/customer/cart/add-to-cart', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${jwtToken}`,
        },
        body: JSON.stringify(inventoryId),
      });
   
      if (response.status === 400) {
        const errorMessage = await response.text();
        console.error(errorMessage);
        return;
      }
   
      if (response.status === 404) {
        console.error('Inventory not found');
        return;
      }
   
      if (response.status !== 200) {
        console.error('Error adding item to cart');
        return;
      }
   
      const result = await response.text();
      console.log('Successfully added to cart:', result);
    } catch (error) {
      console.error('Error adding to cart:', error.message);
    }
   };
   
  return (
    <div className='back-box'>

      <div className='image-box'>
        <p className='backButton'><a className='backButtonLink' href="/Sneakers">&lt;</a></p>
        <img src={sneaker.image} alt="Sneaker Image" className='shoe-photo'/>
        <HeartRegular className='wishlist-button' onClick={() => handleWishlistClick()}></HeartRegular>
        {wishlistClicked && <HeartSolid className='wishlist-button-clicked' onClick={() => handleWishlistClick()}>Sizes: </HeartSolid>}
      </div>

      <div className='sneaker-information'>
        <h1 className='sneaker-name'>{sneaker.name}</h1>
        <h3 className='sneaker-price'>${sneaker.price}</h3>
        <h3 className='sneaker-colors'>Colors:</h3>
      </div>

      <div className='color-picker'>
        {Array.from(new Set(inventories.map(inventory => inventory.color.id))).map(colorId => (
          <button
            key={colorId}
            className={`circle ${selectedColorId === colorId ? 'selected' : ''}`}
            style={{ backgroundColor: inventories.find(inventory => inventory.color.id === colorId).color.name }}
            onClick={() => handleCircleClick(colorId)}
          ></button>
        ))}
      </div>
        
      <div className='size-picker'>
        {selectedSizes.length > 0 && <h3 className='sneaker-sizes'>Sizes: </h3>}
        {selectedSizes.map((size) => (
          <button key={size} className={`size-button ${selectedSize === size ? 'selected' : ''}`} onClick={() => handleSizeClick(size) }>{size}</button>
        ))}
      </div>

      <button className='place-order' onClick={() => handleOrder()}>
          ADD TO CART
      </button>
    </div>
  );


};

export default SneakerInformation;
