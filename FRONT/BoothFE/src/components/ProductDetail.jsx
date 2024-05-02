import React, { useContext, useEffect, useState } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";

const ProductDetail = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);
  const [data, setData] = useState([]);

  const LoadProductDetails = async (id) => {
    try {
      const res = await fetchData(
        "/products",
        "POST",
        { productId: props.productSelected },
        userCtx.accessToken
      );
      if (res.ok) {
        const data = await res.data;
        console.log("loaded 1 product detail");
        setData(res.data);
      }
    } catch (error) {
      console.log("Failed to get 1 product detail");
      console.log(error);
    }
  };
  useEffect(() => {
    if (props.productSelected) {
      LoadProductDetails(props.productSelected);
    }
  }, [props.productSelected]);

  return (
    <div>
      {data != [] ? (
        <div>
          {data.name}
          {data.price}
          {data.description}
          {data.stock}
          {data.user.name}
        </div>
      ) : (
        <div> Unavailable</div>
      )}
    </div>
  );
};

export default ProductDetail;
