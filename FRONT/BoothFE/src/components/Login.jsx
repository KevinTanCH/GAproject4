import React, { useContext, useRef, useState } from "react";
import useFetch from "../hooks/useFetch";
import UserContext from "../context/user";
import { jwtDecode } from "jwt-decode";

const Login = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);
  const usernameRef = useRef();
  const passwordRef = useRef();
  const [role, setRole] = useState("BUYER");
  const [message, setMessage] = useState("Please Login");

  const handleUserLogin = async () => {
    const res = await fetchData("/auth/authenticate", "POST", {
      username: usernameRef.current.value,
      password: passwordRef.current.value,
    });

    if (res.ok) {
      userCtx.setAccessToken(res.data.accessJWT);
      const decoded = jwtDecode(res.data.accessJWT);

      userCtx.setRole(decoded.role);
      userCtx.setUserId(decoded.userId);
      setMessage("Success, Loading page" + role);
      console.log("works");
    } else {
      alert(JSON.stringify(res.data));
      setMessage("login error");
    }
  };

  return (
    <>
      <div>
        <input ref={usernameRef} type="text" placeholder="username"></input>
        <input ref={passwordRef} type="text" placeholder="password"></input>
        {/* TODO change type to password */}
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
