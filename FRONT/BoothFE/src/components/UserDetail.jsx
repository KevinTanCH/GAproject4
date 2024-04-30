import React, { useContext } from "react";
import UserContext from "../context/user";

const UserDetail = (props) => {
  const userCtx = useContext(UserContext);
  return (
    <div>
      {userCtx.userId}
      {userCtx.role}
    </div>
  );
};

export default UserDetail;
