import React, { useContext, useRef, useState } from "react";
import useFetch from "../hooks/useFetch";
import UserContext from "../context/user";

const OrderListCard = (props) => {
  const userCtx = useContext(UserContext);
  const fetchData = useFetch();
  const patchStatusRef = useRef();

  const handleOrderStatus = async () => {
    try {
      const res = await fetchData(
        "/order/sellerpatch",
        "PATCH",
        {
          id: props.id,
          productId: props.productId,
          userId: props.buyerId,
          orderStatus: patchStatusRef.current.value,
        },
        userCtx.accessToken
      );
      if (res.ok) {
      }
    } catch (error) {
      console.log("Failed to patch order list");
      console.log(error);
    }
  };
  const handleOrderStatusBuyer = async () => {
    try {
      const res = await fetchData(
        "/order/buyerpatch",
        "PATCH",
        {
          id: props.id,
          productId: props.productId,
          userId: props.buyerId,
          orderStatus: patchStatusRef.current.value,
        },
        userCtx.accessToken
      );
      if (res.ok) {
      }
    } catch (error) {
      console.log("Failed to patch order list");
      console.log(error);
    }
  };

  const handleUserSelect = () => {
    if (userCtx.role == "BUYER") {
      userCtx.setOtherUserId(props.buyerId);
    } else {
      userCtx.setOtherUserId(props.sellerId);
    }
    userCtx.setViewState(7);
  };

  return (
    <div className="row">
      <div className="col-sm-1">{props.id}</div>
      <div className="col-sm-1">{props.productName}</div>
      <div className="col-sm-1">${props.amountPaid}</div>
      <div
        className="col-sm-1"
        onClick={(e) => {
          handleUserSelect();
        }}
      >
        {userCtx.role == "BUYER" ? (
          <>{props.sellerName}</>
        ) : (
          <>{props.buyerName}</>
        )}
      </div>
      <div className="col-sm-1">{props.locationFrom}</div>
      <div className="col-sm-1">{props.locationTo}</div>
      <div className="col-sm-1">{props.timeOrdered}</div>
      <div className="col-sm-1">{props.timeDelivered}</div>
      <div className="col-sm-1">{props.orderStatus}</div>
      <div className="col-sm-3">
        {userCtx.role == "SELLER" ? (
          <>
            <select ref={patchStatusRef}>
              <option value="PENDING_PURCHASE">PENDING_PURCHASE</option>
              <option value="PURCHASED">PURCHASED</option>
              <option value="PENDING_DELIVERY">PENDING_DELIVERY</option>
              <option value="DELIVERED">DELIVERED</option>
              <option value="CANCELLED">CANCELLED</option>
            </select>
            <button
              className="col-sm-3"
              onClick={() => {
                handleOrderStatus();
              }}
            >
              Update
            </button>
          </>
        ) : (
          <>
            <select ref={patchStatusRef}>
              <option value="PENDING_PURCHASE">PENDING_PURCHASE</option>
              <option value="PURCHASED">PURCHASED</option>
            </select>
            <button
              className="col-sm-3"
              onClick={() => {
                handleOrderStatusBuyer();
              }}
            >
              Update
            </button>
          </>
        )}
      </div>
    </div>
  );
};

export default OrderListCard;
