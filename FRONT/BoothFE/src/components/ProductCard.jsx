import React, { useContext } from "react";
import UserContext from "../context/user";

const ProductCard = (props) => {
  const userCtx = useContext(UserContext);

  const handleProductSelect = () => {
    props.setProductSelected(props.id);
    console.log(props.id);
  };

  return (
    <div
      onClick={(e) => {
        handleProductSelect(e);
      }}
    >
      A Product Card
      <div>{props.photo}</div>
      <div>{props.name}</div>
      <div>{props.price}</div>
      <div>{props.sellerName}</div>
      <div>
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
