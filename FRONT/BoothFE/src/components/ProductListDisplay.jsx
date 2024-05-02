import React, { useContext } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";
import ProductCard from "./ProductCard";

const ProductListDisplay = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);

  const getAllProducts = async () => {
    try {
      const res = await fetchData(
        "/products",
        "GET",
        undefined,
        userCtx.accessToken
      );
      if (res.ok) {
        userCtx.setProductArr(res.data);
      }
    } catch (error) {
      console.log("Failed to get all");
      console.log(error);
    }
  };

  return (
    <div className="container">
      Product List Display
      <button
        onClick={() => {
          getAllProducts();
        }}
      >
        Get All Products
      </button>
      <div className="row">
        <div className="col-sm-1">Name</div>
        <div className="col-sm-1">price</div>
        <div className="col-sm-1">Seller</div>
        <div className="col-sm-1">In Stock?</div>
      </div>
      <div>
        {userCtx.productArr.length !== 0 ? (
          userCtx.productArr.map((item, id) => {
            return (
              <ProductCard
                key={item.id}
                id={item.id}
                name={item.name}
                price={item.price}
                photo={item.photo}
                sellerId={item.user.id}
                sellerName={item.user.name}
                isAvailable={item.isAvailable}
                setProductSelected={userCtx.setProductSelected}
              ></ProductCard>
            );
          })
        ) : (
          <div>Empty product list. Please Refresh</div>
        )}
      </div>
    </div>
  );
};

export default ProductListDisplay;
