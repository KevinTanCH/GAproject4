import React, { useContext } from "react";
import UserContext from "../context/user";
import useFetch from "../hooks/useFetch";
import OrderListCard from "./OrderListCard";

const OrderList = (props) => {
  const fetchData = useFetch();
  const userCtx = useContext(UserContext);

  const getOrderList = async () => {
    try {
      let orderListEndPoint = "";
      if (userCtx.role == "BUYER") {
        orderListEndPoint = "/order/history";
      } else {
        orderListEndPoint = "/order/salehistory";
      }
      const res = await fetchData(
        orderListEndPoint,
        "GET",
        undefined,
        userCtx.accessToken
      );
      if (res.ok) {
        userCtx.setOrderListArr(res.data);
      }
    } catch (error) {
      console.log("Failed to get order list");
      console.log(error);
    }
  };

  return (
    <div className="container">
      Order List Display
      <button
        onClick={() => {
          getOrderList();
        }}
      >
        Get Order List
      </button>
      <div className="row">
        <div className="col-sm-1">Order Id</div>
        <div className="col-sm-1">Product</div>
        <div className="col-sm-1">Amount Paid</div>
        <div className="col-sm-1">Buyer</div>
        <div className="col-sm-1">From</div>
        <div className="col-sm-1">To</div>
        <div className="col-sm-1">Time Ordered</div>
        <div className="col-sm-1">Time Delivered</div>
        <div className="col-sm-1">Status</div>
      </div>
      <div>
        {userCtx.orderListArr.length !== 0 ? (
          userCtx.orderListArr.map((item, id) => {
            return (
              <OrderListCard
                key={item.id}
                id={item.id}
                productId={item.product.id}
                productName={item.product.name}
                amountPaid={item.amountPaid}
                sellerId={item.product.user.id}
                sellerName={item.product.user.name}
                buyerId={item.user.id}
                buyerName={item.user.name}
                locationFrom={item.locationFrom}
                locationTo={item.locationTo}
                timeOrdered={item.timeOrdered}
                timeDelivered={item.timeDelivered}
                orderStatus={item.orderStatus}
              ></OrderListCard>
            );
          })
        ) : (
          <div>Empty product list. Please Refresh</div>
        )}
      </div>
    </div>
  );
};

export default OrderList;
