import React, { useState } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";
import Login from "./Login";

const MainDisplay = () => {
  const [accessToken, setAccessToken] = useState("");
  return (
    <>
      <UserContext.Provider
        value={{
          accessToken,
          setAccessToken,
        }}
      >
        <Login></Login>
      </UserContext.Provider>
    </>
  );
};

export default MainDisplay;
