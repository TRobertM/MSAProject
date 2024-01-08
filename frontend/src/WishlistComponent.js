import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './css/WishlistComponentCss.css';

const WishlistComponent = () => {
  const [sneakerList, setSneakerList] = useState([]);
  const navigate = useNavigate();
  const jwtToken = localStorage.getItem('jwtToken');

  useEffect(() => {

    const fetchInventory = async () => {
      try {
        const response = await fetch('http://localhost:8080/customer/wishlist', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwtToken}`,
          },
        });

        if (!response.ok) {
          throw new Error('Failed to fetch data');
        }

        const data = await response.json();
        setSneakerList(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchInventory();
  }, );

  const handleSneakerClick = (sneakerId) => {
    navigate(`/${sneakerId}`);
  };

  const handleDeleteButton = async (sneakerId) => {

    const sneakerToDelete = sneakerList.find(
      (sneaker) => sneaker.id === sneakerId
    );

    console.log(sneakerToDelete)
    if (!sneakerToDelete) {
      console.log('Inventory not found with id:', sneakerId);
      return;
    }
  
    try {
      const response = await fetch(
        'http://localhost:8080/customer/wishlist/remove-from-wishlist',
        {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${jwtToken}`,
          },
          body: JSON.stringify({sneakerToDelete}),
        }
      );
  
      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }
  
      setSneakerList((prevInventoryList) =>
        prevInventoryList.filter((sneaker) => sneaker.id !== sneakerToDelete.id)
      );
  
      const wishlistBoxToRemove = document.getElementById(
        `wishlist-box-${sneakerToDelete.id}`
      );
      if (wishlistBoxToRemove) {
        wishlistBoxToRemove.remove();
      }
  
      const data = await response.text();
      console.log('Server response:', data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  return (
    <div>
      <div className='page cart-page'>
        <p className='backButton'><a className='backButtonLink' href="/Sneakers">&lt;</a></p>
        
        {sneakerList.length > 0 ? (
          <div className='wishlist-box'>
            {sneakerList.map((sneaker) => (
              <div key={sneaker.id} className="cart-box">
                <img src={sneaker.image} alt={sneaker.name} className="sneaker-image-cart" onClick={() => handleSneakerClick(sneaker.id)}/>
                <div className="sneaker-details-cart">
                  <div className='wishlist-h3' onClick={() => handleSneakerClick(sneaker.id)}><h3>{sneaker.name}</h3></div>
                </div>
                <div className='cart-buttons'>
                  <div key={sneaker.id} className='delete-cart' onClick={() => handleDeleteButton(sneaker.id)}><p>X</p></div>
                </div>
              </div>
            ))}
          </div>
        ) : null}
      </div> 
    </div>
  );
};

export default WishlistComponent;