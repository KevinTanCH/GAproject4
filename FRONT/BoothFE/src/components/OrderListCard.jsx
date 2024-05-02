import React, { useContext } from "react";
import UserContext from "../context/user";

const OrderListCard = (props) => {
  const userCtx = useContext(UserContext);

  return (
    <div>
      A Product Card
      <div>{props.id}</div>
      <div>{props.productName}</div>
      <div>{props.amountPaid}</div>
      <div>
        {userCtx.role == "BUYER" ? (
          <>{props.sellerName}</>
        ) : (
          <>{props.buyerName}</>
        )}
      </div>
      <div>{props.locationFrom}</div>
      <div>{props.locationTo}</div>
      <div>{props.timeOrdered}</div>
      <div>{props.timeDelivered}</div>
      <div>{props.orderStatus}</div>
    </div>
  );
};

export default OrderListCard;
