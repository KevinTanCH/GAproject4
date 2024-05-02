import React, { useState } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";
import Login from "./Login";
import UserDetail from "./UserDetail";
import ProductListDisplay from "./ProductListDisplay";
import ProductDetail from "./ProductDetail";
import Cart from "./Cart";
import OrderList from "./OrderList";

const MainDisplay = () => {
  const [accessToken, setAccessToken] = useState("");
  const [role, setRole] = useState("");
  const [userId, setUserId] = useState();
  const [otherUserId, setOtherUserId] = useState();
  const [productSelected, setProductSelected] = useState();
  const [productId, setProductId] = useState();
  const [orderId, setOrderId] = useState();
  const [productArr, setProductArr] = useState([]);
  const [orderListArr, setOrderListArr] = useState([]);

  return (
    <>
      <UserContext.Provider
        value={{
          accessToken,
          setAccessToken,
          role,
          setRole,
          userId,
          setUserId,
          otherUserId,
          setOtherUserId,
          productSelected,
          setProductSelected,
          productId,
          setProductId,
          orderId,
          setOrderId,
          productArr,
          setProductArr,
          orderListArr,
          setOrderListArr,
        }}
      >
        <Login></Login>
        <UserDetail></UserDetail>
        <ProductListDisplay></ProductListDisplay>
        <ProductDetail productSelected={productSelected}></ProductDetail>
        <Cart></Cart>
        <OrderList></OrderList>
      </UserContext.Provider>
    </>
  );
};

export default MainDisplay;
