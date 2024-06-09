// redux/reducer/handleCart.js

const initialCartState = {
  cart: [],
};

const handleCart = (state = initialCartState, action) => {
  const product = action.payload;
  switch (action.type) {
    case 'ADDITEM': {
      const exist = state.cart.find((x) => x.idProducto === product.idProducto);
      if (exist) {
        return {
          ...state,
          cart: state.cart.map((x) =>
            x.idProducto === product.idProducto ? { ...x, quantity: x.quantity + 1 } : x
          ),
        };
      } else {
        return {
          ...state,
          cart: [...state.cart, { ...product, quantity: 1 }],
        };
      }
    }
    case 'DELITEM': {
      const exist = state.cart.find((x) => x.idProducto === product.idProducto);
      if (exist.quantity === 1) {
        return {
          ...state,
          cart: state.cart.filter((x) => x.idProducto !== product.idProducto),
        };
      } else {
        return {
          ...state,
          cart: state.cart.map((x) =>
            x.idProducto === product.idProducto ? { ...x, quantity: x.quantity - 1 } : x
          ),
        };
      }
    }
    default:
      return state;
  }
};

export default handleCart;
