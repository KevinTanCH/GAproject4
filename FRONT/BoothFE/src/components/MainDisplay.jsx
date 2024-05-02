import React, { useState } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";
import NavBar from "./NavBar";
import Login from "./Login";
import ProductListDisplay from "./ProductListDisplay";
import ProductDetail from "./ProductDetail";
import ProductForm from "./ProductForm";
import Cart from "./Cart";
import OrderList from "./OrderList";
import UserDetail from "./UserDetail";
import OtherUserDetail from "./OtherUserDetails";

const MainDisplay = () => {
  const [viewState, setViewState] = useState(0);
  const [accessToken, setAccessToken] = useState("");
  const [role, setRole] = useState("");
  const [userId, setUserId] = useState();
  const [otherUserId, setOtherUserId] = useState();
  const [productSelected, setProductSelected] = useState();
  const [productArr, setProductArr] = useState([]);
  const [orderListArr, setOrderListArr] = useState([]);

  return (
    <>
      <UserContext.Provider
        value={{
          viewState,
          setViewState,
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
          productArr,
          setProductArr,
          orderListArr,
          setOrderListArr,
        }}
      >
        <h2>SpringBOOTh</h2>
        <NavBar></NavBar>
        <br></br>
        {viewState == 0 ? (
          <>
            <Login setUserId={setUserId}></Login>
          </>
        ) : (
          <> </>
        )}
        {viewState == 1 ? (
          <>
            <ProductListDisplay></ProductListDisplay>
          </>
        ) : (
          <> </>
        )}
        {viewState == 2 ? (
          <>
            <ProductDetail productSelected={productSelected}></ProductDetail>
          </>
        ) : (
          <> </>
        )}
        {viewState == 3 ? (
          <>
            <ProductForm productSelected={productSelected}></ProductForm>
          </>
        ) : (
          <> </>
        )}
        {viewState == 4 ? (
          <>
            <Cart></Cart>
          </>
        ) : (
          <> </>
        )}
        {viewState == 5 ? (
          <>
            <OrderList></OrderList>
          </>
        ) : (
          <> </>
        )}
        {viewState == 6 ? (
          <>
            <UserDetail userId={userId}></UserDetail>
          </>
        ) : (
          <> </>
        )}
        {viewState == 7 ? (
          <>
            <OtherUserDetail otherUserId={otherUserId}></OtherUserDetail>
          </>
        ) : (
          <> </>
        )}
      </UserContext.Provider>
    </>
  );
};

export default MainDisplay;
