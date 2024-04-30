import React, { useState } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";
import Login from "./Login";
import ProductListDisplay from "./ProductListDisplay";
import UserDetail from "./UserDetail";

const MainDisplay = () => {
  const [accessToken, setAccessToken] = useState("");
  const [role, setRole] = useState("");
  const [userId, setUserId] = useState("");
  const [otherUserId, setOtherUserId] = useState("");
  const [productId, setProductId] = useState("");
  const [orderId, setOrderId] = useState("");

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
        }}
      >
        <Login></Login>
        <ProductListDisplay></ProductListDisplay>
        <UserDetail></UserDetail>
      </UserContext.Provider>
    </>
  );
};

export default MainDisplay;
