import React, { useContext } from "react";
import ProductCard from "./ProductCard";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";

const ProductListDisplay = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);

  return (
    <div>
      Product List Display
      <ProductCard></ProductCard>
    </div>
  );
};

export default ProductListDisplay;
