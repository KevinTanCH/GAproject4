import React, { useState } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";
import Login from "./Login";
import ProductListDisplay from "./ProductListDisplay";
import OrderList from "./OrderList";
import Cart from "./Cart";
import UserDetail from "./UserDetail";

const MainDisplay = () => {
  const [accessToken, setAccessToken] = useState("");
  const [role, setRole] = useState("");
  const [userId, setUserId] = useState();
  const [otherUserId, setOtherUserId] = useState();
  const [productId, setProductId] = useState();
  const [orderId, setOrderId] = useState();
  const [productArr, setProductArr] = useState([]);

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
          productId,
          setProductId,
          orderId,
          setOrderId,
          productArr,
          setProductArr,
        }}
      >
        <Login></Login>
        <UserDetail></UserDetail>
        <ProductListDisplay></ProductListDisplay>
        <OrderList></OrderList>
        <Cart></Cart>
      </UserContext.Provider>
    </>
  );
};

export default MainDisplay;
