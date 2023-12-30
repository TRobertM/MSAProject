import React from 'react';
import { useParams } from 'react-router-dom';

const SneakerInformation = () => {
  // Access the id parameter from the URL
  const { id } = useParams();

  return (
    <div>
      <h2>Sneaker Information</h2>
      <p>Displaying information for sneaker with ID: {id}</p>
      {/* Add logic to fetch and display details for the specific sneaker */}
    </div>
  );
};

export default SneakerInformation;
