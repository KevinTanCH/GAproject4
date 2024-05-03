import React, { useContext, useEffect, useRef, useState } from "react";
import useFetch from "../hooks/useFetch";
import UserContext from "../context/user";

const UserDetail = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);
  const [data, setData] = useState("");

  const LoadProfileDetails = async () => {
    try {
      const res = await fetchData(
        "/user/self",
        "POST",
        { userId: props.userId },
        userCtx.accessToken
      );
      if (res.ok) {
        const data = await res.data;
        setData(res.data);
      }
    } catch (error) {
      console.log("Failed to get self detail");
      console.log(error);
    }
  };

  useEffect(() => {
    if (props.userId) {
      LoadProfileDetails(props.userId);
    }
  }, [props.userId]);

  return (
    <div className="container">
      {data != "" ? (
        <div>
          <div>{data.role}</div>
          <div>Name: {data.name}</div>
          {/* <div>{data.photo}</div> */}
          <div>Username: {data.username}</div>
          <div>Email: {data.email}</div>
          <div>Location: {data.location}</div>
          <div>Profile Description: {data.profileText}</div>
          <div>
            {data.isActive == true ? (
              <>Active account</>
            ) : (
              <>Inactive account</>
            )}
          </div>
        </div>
      ) : (
        <div> Unavailable</div>
      )}
    </div>
  );
};

export default UserDetail;
