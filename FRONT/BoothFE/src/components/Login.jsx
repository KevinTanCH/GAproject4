import React, { useContext, useRef, useState } from "react";
import useFetch from "../hooks/useFetch";
import UserContext from "../context/user";
import { jwtDecode } from "jwt-decode";

const Login = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);
  const usernameRef = useRef();
  const passwordRef = useRef();
  const [message, setMessage] = useState("Please Login");

  const handleUserLogin = async () => {
    const res = await fetchData("/auth/authenticate", "POST", {
      username: usernameRef.current.value,
      password: passwordRef.current.value,
    });

    if (res.ok) {
      console.log("Login Success.");
      userCtx.setAccessToken(res.data.accessJWT);
      const decoded = jwtDecode(res.data.accessJWT);

      userCtx.setRole(decoded.role);
      userCtx.setUserId(decoded.userId);
      setMessage("Success, Loading page for " + userCtx.role);
    } else {
      alert(JSON.stringify(res.data));
      setMessage("login error");
    }
  };

  return (
    <>
      <div>
        <input ref={usernameRef} type="text" placeholder="username"></input>
        <input ref={passwordRef} type="password" placeholder="password"></input>
        <button
          onClick={() => {
            handleUserLogin();
          }}
        >
          Login
        </button>
        <p>{message}</p>
      </div>
    </>
  );
};

export default Login;
