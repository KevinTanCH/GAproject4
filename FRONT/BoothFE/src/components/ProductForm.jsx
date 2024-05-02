import React, { useContext, useEffect, useRef, useState } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";

const ProductForm = () => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);
  const nameRef = useRef();
  const priceRef = useRef();
  const descriptionRef = useRef();
  const stockRef = useRef();
  const isAvailableRef = useRef();
  const [data, setData] = useState("");

  const addNewProduct = async (id) => {
    try {
      const res = await fetchData(
        "/products",
        "PUT",
        {
          name: nameRef.current.value,
          price: priceRef.current.value,
          sellerId: userCtx.userId,
          description: descriptionRef.current.value,
          photo: "",
          stock: stockRef.current.value,
          isAvailable: isAvailableRef.current.value,
        },
        userCtx.accessToken
      );
      if (res.ok) {
        const data = await res.data;
        setData(res.data);
        alert("Product added.");
        userCtx.setViewState(1);
      }
    } catch (error) {
      console.log("Failed to get 1 product detail");
      console.log(error);
    }
  };

  return (
    <div className="container">
      <div>
        <div>Name: </div>
        <input ref={nameRef} type="text"></input>
        <div>Price: </div>
        <input ref={priceRef} type="text"></input>
        <div>Description: </div>
        <input ref={descriptionRef} type="text"></input>
        <div>Stock left: </div>
        <input ref={stockRef} type="text"></input>
        <div>Available?: </div>
        <select ref={isAvailableRef}>
          <option value="true">true</option>
          <option value="false">false</option>
        </select>
        <button
          onClick={() => {
            addNewProduct();
          }}
        >
          Add new product
        </button>
      </div>
    </div>
  );
};
export default ProductForm;
