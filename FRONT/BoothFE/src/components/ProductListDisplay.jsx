import React, { useContext } from "react";
import ProductCard from "./ProductCard";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";

const ProductListDisplay = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);

  const getAllProducts = async () => {
    try {
      console.log(userCtx.accessToken);
      const res = await fetchData(
        "/products",
        "GET",
        undefined,
        userCtx.accessToken
      );
      if (res.ok) {
        console.log(res.data);
      }
    } catch (error) {
      console.log("failed to get all");
      console.log(error);
    }
  };

  return (
    <div>
      Product List Display
      <button
        onClick={() => {
          getAllProducts();
        }}
      >
        Get All
      </button>
      <ProductCard></ProductCard>
    </div>
  );
};

export default ProductListDisplay;
