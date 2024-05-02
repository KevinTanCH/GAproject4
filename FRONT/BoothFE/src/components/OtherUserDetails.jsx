import React, { useContext, useEffect, useRef, useState } from "react";
import useFetch from "../hooks/useFetch";
import UserContext from "../context/user";

const OtherUserDetails = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);
  const [data, setData] = useState("");

  const LoadOtherUserDetails = async () => {
    try {
      let post1UserEndPoint = "";
      if (userCtx.role == "SELLER") {
        post1UserEndPoint = "/user/buyer";
      } else {
        post1UserEndPoint = "/user/seller";
      }
      const res = await fetchData(
        post1UserEndPoint,
        "POST",
        { userId: userCtx.otherUserId },
        userCtx.accessToken
      );
      if (res.ok) {
        const data = await res.data;
        setData(res.data);
      }
    } catch (error) {
      console.log("Failed to get other user detail");
      console.log(error);
    }
  };
  useEffect(() => {
    if (props.otherUserId) {
      LoadOtherUserDetails(props.otherUserId);
    }
  }, [props.otherUserId]);

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

export default OtherUserDetails;
