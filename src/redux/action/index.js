import axios from 'axios';

export const ADDITEM = "ADDITEM";
export const DELITEM = "DELITEM";
export const CREATE_CART = "CREATE_CART";

export const addCart = (product) => async (dispatch, getState) => {
  let carritoId = localStorage.getItem('carritoId');
  
  if (!carritoId) {
    try {
      const response = await axios.post('http://localhost:8080/api/v1/carrito', { emailComprador: null });
      carritoId = response.data.object.id;
      localStorage.setItem('carritoId', carritoId);
      dispatch({ type: CREATE_CART, payload: carritoId });
    } catch (error) {
      console.error("Error creating cart", error);
      throw new Error("Error al crear el carrito");
    }
  }

  try {
    const detalleResponse = await axios.post(`http://localhost:8080/api/v1/carrito/${carritoId}/productos/${product.idProducto}?cantidad=1&precioTotal=${product.precioProducto}`);
    dispatch({ type: ADDITEM, payload: { ...product, carritoId, detalleId: detalleResponse.data.object.id } });
    return { success: true, carritoId, detalleId: detalleResponse.data.object.id };
  } catch (error) {
    console.error("Error adding product to cart", error);
    throw new Error("Error al agregar el producto al carrito");
  }
};

export const delCart = (product) => {
  return {
    type: DELITEM,
    payload: product,
  };
};
