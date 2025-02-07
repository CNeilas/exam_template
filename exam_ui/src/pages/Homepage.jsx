import { useEffect, useState } from "react";
import axios from "axios";

export const Homepage = () => {
  const [listings, setListings] = useState([]);

  useEffect(() => {
    axios
      .get("https://localhost:8080/api/v1/listings")
      .then((response) => setListings(response.data))
      .catch((error) => console.log(error));
  }, []);

  return (
    <div className="listing-container">
      {listings.length > 0 ? (
        listings.map((listing) => (
          <div key={listing.id} className="listing">
            <h2>{listing.name}</h2>
            <p>{listing.description}</p>
            <p>Price: ${listing.price}</p>
            <p>City: {listing.city}</p>
          </div>
        ))
      ) : (
        <p>No listings available.</p>
      )}
    </div>
  );
};


