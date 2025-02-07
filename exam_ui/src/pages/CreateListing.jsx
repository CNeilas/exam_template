
import { useState } from "react";
import axios from "axios";

export const CreateListing = () => {
  const [formData, setFormData] = useState({
    name: "",
    description: "",
    price: 0,
    city: ""
  });

  const handleChange = (event) => {
    setFormData({ ...formData, [event.target.name]: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post("https://localhost8080/api/v1/listings", formData)
      .then((response) => console.log(response))
      .catch((error) => console.log(error.response.data));
  };

  return (
    <div className="listing-form">
      <form onSubmit={handleSubmit}>
        <label>Name</label>
        <input type="text" name="name" onChange={handleChange} required />
        
        <label>Description</label>
        <textarea name="description" onChange={handleChange} required />
        
        <label>Price</label>
        <input type="number" name="price" onChange={handleChange} required />
        
        <label>City</label>
        <input type="text" name="city" onChange={handleChange} required />
        
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};
