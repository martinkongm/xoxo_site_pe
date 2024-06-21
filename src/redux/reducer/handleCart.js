const initialCartState = {
  cart: JSON.parse(localStorage.getItem('cart')) || [],
  cartId: localStorage.getItem('cartId') || null,
};

const handleCart = (state = initialCartState, action) => {
  let newState;
  const product = action.payload;
  switch (action.type) {
    case 'ADDITEM': {
      const exist = state.cart.find((x) => x.idProducto === product.idProducto);
      if (exist) {
        newState = {
          ...state,
          cart: state.cart.map((x) =>
            x.idProducto === product.idProducto ? { ...x, quantity: x.quantity + 1 } : x
          ),
        };
      } else {
        newState = {
          ...state,
          cart: [...state.cart, { ...product, quantity: 1 }],
        };
      }
      break;
    }
    case 'DELITEM': {
      const exist = state.cart.find((x) => x.idProducto === product.idProducto);
      if (exist.quantity === 1) {
        newState = {
          ...state,
          cart: state.cart.filter((x) => x.idProducto !== product.idProducto),
        };
      } else {
        newState = {
          ...state,
          cart: state.cart.map((x) =>
            x.idProducto === product.idProducto ? { ...x, quantity: x.quantity - 1 } : x
          ),
        };
      }
      break;
    }
    case 'CREATE_CART': {
      newState = {
        ...state,
        cartId: action.payload,
      };
      break;
    }
    default:
      return state;
  }

  // Save to localStorage
  localStorage.setItem('cart', JSON.stringify(newState.cart));
  localStorage.setItem('cartId', newState.cartId);

  return newState;
};

export default handleCart;
