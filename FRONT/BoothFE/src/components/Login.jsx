import React, { useContext, useEffect, useRef, useState } from "react";
import useFetch from "../hooks/useFetch";
import UserContext from "../context/user";
import { jwtDecode } from "jwt-decode";

const Login = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);
  const usernameRef = useRef();
  const passwordRef = useRef();
  const emailRef = useRef();
  const nameRef = useRef();
  const roleRef = useRef();
  const [boolRegister, setBoolRegister] = useState(false);
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
      props.setUserId(decoded.userId);
      setMessage("Success, Loading page for " + userCtx.role);
      userCtx.setViewState(6);
    } else {
      alert(JSON.stringify(res.data));
      setMessage("Login error, check login details");
    }
  };

  const handleUserRegister = async () => {
    const res = await fetchData("/auth/register", "POST", {
      username: usernameRef.current.value,
      password: passwordRef.current.value,
      name: nameRef.current.value,
      email: emailRef.current.value,
      photo: "photo",
      location: "location",
      profileText: "profile text",
      isActive: true,
      role: roleRef.current.value,
    });

    if (res.ok) {
      console.log("Register Success.");
      userCtx.setAccessToken(res.data.accessJWT);
      const decoded = jwtDecode(res.data.accessJWT);

      userCtx.setRole(decoded.role);
      userCtx.setUserId(decoded.userId);
      setMessage("Success, Loading page for " + userCtx.role);
      userCtx.setViewState(6);
    } else {
      alert(JSON.stringify(res.data));
      setMessage("Register error");
    }
  };

  return (
    <>
      <div className="container">
        <input ref={usernameRef} type="text" placeholder="username"></input>
        <input ref={passwordRef} type="password" placeholder="password"></input>

        {boolRegister == false ? (
          <>
            <button
              onClick={() => {
                handleUserLogin();
              }}
            >
              Login
            </button>
            <button
              onClick={() => {
                setBoolRegister(true);
              }}
            >
              Don't have an account? Register.
            </button>
          </>
        ) : (
          <>
            <input ref={emailRef} type="text" placeholder="email"></input>
            <input ref={nameRef} type="text" placeholder="name"></input>
            <select ref={roleRef}>
              <option value="BUYER">BUYER</option>
              <option value="SELLER">SELLER</option>
            </select>
            <button
              onClick={() => {
                handleUserRegister();
              }}
            >
              Register
            </button>
            <button
              onClick={() => {
                setBoolRegister(false);
              }}
            >
              Have an account? Login.
            </button>
          </>
        )}
        <p>{message}</p>
      </div>
    </>
  );
};

export default Login;
