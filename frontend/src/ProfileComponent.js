import React, { useState, useEffect } from 'react';
import './css/ProfileComponentCss.css';

const ProfileComponent = () => {
  const [editMode, setEditMode] = useState(false);
  const [address, setAddress] = useState("");
  const [user, setUser] = useState({});
  const jwtToken = localStorage.getItem('jwtToken');

  const handleEditClick = () => {
    setEditMode(!editMode);
  };

  const handleSaveClick = async () => {
    try {
      const response = await fetch('http://localhost:8080/customer/settings/change_address', {
        method: 'PUT',
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
        body: JSON.stringify(address),
      });

      if (!response.ok) {
        throw new Error('Failed to fetch customer settings');
      }

      const data = await response.json();
      setUser(data);
      setAddress(data.address);
      console.log(data.address);
    } catch (error) {
      console.error('Error fetching customer settings:', error);
    }

    setEditMode(false);
  };

  useEffect(() => {
    const fetchCustomerSettings = async () => {
      try {
        const response = await fetch('http://localhost:8080/customer/settings', {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        });

        if (!response.ok) {
          throw new Error('Failed to fetch customer settings');
        }

        const data = await response.json();
        setUser(data);
        setAddress(data.address);
        console.log(data.address);
      } catch (error) {
        console.error('Error fetching customer settings:', error);
      }
    };

    fetchCustomerSettings();
  }, [jwtToken]);

  return (
    <div>
      <p className='backButton'><a className='backButtonLink' href="/Sneakers">&lt;</a></p>
      <div className='page profile-page'>

        <div className='settings-box'>
          <h1>Settings</h1>

          <div className='shipping-address-box'>
            <div className='address-flex'>
              <span>Address:</span>
            </div>
            <div className='edit-address'>
              {editMode ? (
                <button onClick={handleSaveClick}>Save</button>
              ) : (
                <button onClick={handleEditClick}>Edit</button>
              )}
            </div>
            <div className='input-box auto-grow'>
            {editMode ? (
                <span>
                  <textarea
                    className='address-input'
                    type="text"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                  />
                </span>
              ) : (
                <span className='address-text'>{address}</span>
              )}
            </div>
          </div>


        </div>
      </div>
    </div>
  );
};

export default ProfileComponent;