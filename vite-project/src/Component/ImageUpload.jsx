import React, { useState, useEffect } from "react";
import axios from "axios";
import "./ImageUpload.css";

function ImageUpload() {
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState("");
  const [images, setImages] = useState([]);
  const [selectedImage, setSelectedImage] = useState(null);

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append("image", file);

    try {
      const response = await axios.post(
        "http://localhost:8080/upload",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      setMessage(response.data); // handle response from backend

      handleGetImages();
    } catch (error) {
      console.error("Error uploading image:", error);
    }
  };

  const handleGetImages = async () => {
    try {
      const response = await axios.get("http://localhost:8080/images");
      setImages(response.data);
    } catch (error) {
      console.error("Error fetching images:", error);
    }
  };

  const handleDeleteImage = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/${id}`);
      // Refresh images after deletion
      handleGetImages();
    } catch (error) {
      console.error("Error deleting image:", error);
    }
  };

  //   useEffect(() => {
  //     handleGetImages();
  //   }, []);

  return (
    <div className="container">
      <h2>Upload Image</h2>
      <form onSubmit={handleSubmit}>
        {message && <p>{message}</p>}
        <input type="file" onChange={handleFileChange} />
        <button type="submit">Upload</button>
      </form>
      <div>
        <button onClick={handleGetImages}>Get All Images</button>
        <ul className="image-list">
          {images.map((image) => (
            <li key={image.id}>
              <div className="image-container">
                <img
                  src={`data:image/jpeg;base64,${image.data}`}
                  alt={image.name} // Use image name as alt text
                  width="100"
                />
                {/* <p>{image.name}</p> Display image name */}
                <button onClick={() => handleDeleteImage(image.id)}>
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default ImageUpload;
