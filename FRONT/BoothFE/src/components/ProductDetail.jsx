import React, { useContext, useEffect, useRef, useState } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";

const ProductDetail = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);
  const nameRef = useRef();
  const [data, setData] = useState("");

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
        setData(res.data);
      }
    } catch (error) {
      console.log("Failed to get 1 product detail");
      console.log(error);
    }
  };

  const deleteProduct = async (id) => {
    try {
      const res = await fetchData(
        "/products",
        "DELETE",
        { productId: props.productSelected },
        userCtx.accessToken
      );
      if (res.ok) {
        const data = await res.data;
        setData(res.data);
      }
    } catch (error) {
      console.log("Failed to delete 1 product detail");
      console.log(error);
    }
  };

  const buyProduct = async (id) => {
    try {
      const res = await fetchData(
        "/purchase",
        "PUT",
        { buyerId: userCtx.userId, productId: props.productSelected },
        userCtx.accessToken
      );
      if (res.ok) {
        const data = await res.data;
        setData(res.data);
        alert("Product bought.");
        userCtx.setViewState(5);
      }
    } catch (error) {
      console.log("Failed to delete 1 product detail");
      console.log(error);
    }
  };

  useEffect(() => {
    if (props.productSelected) {
      LoadProductDetails(props.productSelected);
    }
  }, [props.productSelected]);

  const handleSellerSelect = () => {
    userCtx.setOtherUserId(data.user.id);
    userCtx.setViewState(7);
  };

  return (
    <div className="container">
      {data != "" ? (
        <div>
          <div>Name: {data.name}</div>
          {/* <div>{data.photo}</div> */}
          <div>${data.price}</div>
          <div>Description: {data.description}</div>
          <div>{data.stock} stock left </div>
          <div
            onClick={(e) => {
              handleSellerSelect(e);
            }}
          >
            Seller: {data.user.name}
          </div>
          <div>
            Item is{" "}
            {data.isAvailable == true ? <>available</> : <>unavailable</>}
          </div>
          <div>
            {userCtx.role == "SELLER" ? (
              <>
                <button
                  onClick={() => {
                    deleteProduct();
                  }}
                >
                  Set Unavailable
                </button>
              </>
            ) : (
              <>
                {data.isAvailable == true ? (
                  <>
                    <button
                      onClick={() => {
                        buyProduct();
                      }}
                    >
                      Buy?
                    </button>
                  </>
                ) : (
                  <> </>
                )}{" "}
              </>
            )}
          </div>
          <div></div>
        </div>
      ) : (
        <div> Unavailable</div>
      )}
    </div>
  );
};

export default ProductDetail;
