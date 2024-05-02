import React, { useContext } from "react";
import UserContext from "../context/user";

const ProductCard = (props) => {
  const userCtx = useContext(UserContext);

  const handleProductSelect = () => {
    props.setProductSelected(props.id);
    userCtx.setViewState(2);
  };

  return (
    <div
      className="row"
      onClick={(e) => {
        handleProductSelect(e);
      }}
    >
      {/* <div>{props.photo}</div> */}
      <div className="col-sm-1">{props.name}</div>
      <div className="col-sm-1">{props.price}</div>
      <div className="col-sm-1">{props.sellerName}</div>
      <div className="col-sm-1">
        {props.isAvailable == true ? (
          <div>Available</div>
        ) : (
          <div> Unavailable</div>
        )}
      </div>
    </div>
  );
};

export default ProductCard;
