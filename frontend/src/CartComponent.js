import React, { useState, useEffect } from 'react';
import './css/CartComponentCss.css';

const CartComponent = () => {
  const [inventoryList, setInventoryList] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const jwtToken = localStorage.getItem('jwtToken');

  useEffect(() => {

    const fetchInventory = async () => {
      try {
        const response = await fetch('http://localhost:8080/customer/cart', {
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
        setInventoryList(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchInventory();
  }, );

  useEffect(() => {
    let total = 0;

    inventoryList.forEach((inventory) => {
      total += inventory.sneaker.price;
    });

    setTotalPrice(total);
  }, [inventoryList]);

  const handleDeleteButton = async (inventoryId) => {
    const inventoryToDelete = inventoryList.find(
      (inventory) => inventory.id === inventoryId
    );
    if (!inventoryToDelete) {
      console.log('Inventory not found with id:', inventoryId);
      return;
    }
  
    try {
      const response = await fetch(
        'http://localhost:8080/customer/cart/remove-from-cart',
        {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${jwtToken}`,
          },
          body: JSON.stringify(inventoryToDelete),
        }
      );
  
      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }
  
      setInventoryList((prevInventoryList) =>
        prevInventoryList.filter((inventory) => inventory.id !== inventoryToDelete.id)
      );
  
      const wishlistBoxToRemove = document.getElementById(
        `wishlist-box-${inventoryToDelete.id}`
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

  const handleOrderButton = async () => {
    try {
      const itemIds = inventoryList.map(item => item.id);
  
      const response = await fetch('http://localhost:8080/customer/orders/add-to-orders', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${jwtToken}`,
        },
        body: JSON.stringify(itemIds), 
      });
  
      if (response.ok) {
        const data = await response.text(); 
        console.log('Order successfully placed', data);
      } else {
        const errorMessage = await response.text(); 
        console.error('Error placing order:', response.status, response.statusText, errorMessage);
      }

      const response2 = await fetch('http://localhost:8080/customer/cart/empty-cart', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${jwtToken}`,
        },
      });
  
      if (response2.ok) {
        const data = await response2.text(); 
        console.log('Cart emptied', data);
      } else {
        const errorMessage = await response2.text(); 
        console.error('Error:', response2.status, response2.statusText, errorMessage);
      }
    } catch (error) {
      console.error('Error:', error.message);
    }
  };

  return (
    <div>
      <div className='page cart-page'>
        <p className='backButton'><a className='backButtonLink' href="/Sneakers">&lt;</a></p>
        
        {inventoryList.length > 0 ? (
          <div className='items-box'>
            {inventoryList.map((inventory) => (
              <div key={inventory.id} className="cart-box">
                <img src={inventory.sneaker.image} alt={inventory.sneaker.name} className="sneaker-image-cart" />
                <div className="sneaker-details-cart">
                  <div className='cart-h3'><h3>{inventory.sneaker.name}</h3></div>
                  <div className='cart-color'><button key={inventory.color.id} className='cart-circle'
                    style={{ backgroundColor: inventory.color.name }}></button></div>
                  <div className='cart-price'><p>{`Price: $${inventory.sneaker.price}`}</p></div>
                  <div className='cart-size'><p>{`Size: ${inventory.size.number}`}</p></div>
                </div>
                <div className='cart-buttons'>
                  <div key={inventory.id} className='delete-cart' onClick={() => handleDeleteButton(inventory.id)}><p>X</p></div>
                </div>
              </div>
            ))}
          </div>
        ) : null}
  
        {inventoryList.length > 0 && (
          <div className='price-box'>
            <h2>Total: </h2>
            <p>{totalPrice}</p>
          </div>
        )}
  
        <button className='order-button' onClick={handleOrderButton}>Place order</button> 
      </div> 
    </div>
  );
  
};

export default CartComponent;