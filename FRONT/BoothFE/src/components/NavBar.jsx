import React, { useContext } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";

const NavBar = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);

  const setNavComponent = (pageState) => {
    if (pageState == -1) {
      userCtx.setAccessToken("");
      userCtx.setRole("");
      userCtx.setUserId();
      userCtx.setOtherUserId();
      userCtx.setProductSelected();
      userCtx.setOrderListArr([]);
      pageState = 0;
    }
    userCtx.setViewState(pageState);
  };

  return (
    <div className="container">
      <button
        className="col-md-3"
        onClick={() => {
          setNavComponent(1);
        }}
      >
        Products
      </button>
      {/* {userCtx.role == "BUYER" ? (
        <>
          <button
            className="col-md-3"
            onClick={() => {
              setNavComponent(4);
            }}
          >
            View Cart
          </button>
        </>
      ) : (
        <></>
      )} */}
      {userCtx.role == "SELLER" ? (
        <>
          <button
            className="col-md-3"
            onClick={() => {
              setNavComponent(3);
            }}
          >
            Add New Product
          </button>
        </>
      ) : (
        <></>
      )}
      {userCtx.accessToken == "" ? (
        <>
          <button
            className="col-md-3"
            onClick={() => {
              setNavComponent(0);
            }}
          >
            Login
          </button>
        </>
      ) : (
        <>
          <button
            className="col-md-3"
            onClick={() => {
              setNavComponent(5);
            }}
          >
            Order History
          </button>
          <button
            className="col-md-2"
            onClick={() => {
              setNavComponent(6);
            }}
          >
            Account
          </button>
          <button
            className="col-md-1"
            onClick={() => {
              setNavComponent(-1);
            }}
          >
            Logout
          </button>
        </>
      )}
    </div>
  );
};

export default NavBar;
