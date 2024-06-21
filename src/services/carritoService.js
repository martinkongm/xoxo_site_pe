// services/carritoService.js

const BASE_URL = "http://localhost:8080/api/v1";

export const createCarrito = async (usuarioId) => {
  const response = await fetch(`${BASE_URL}/carrito`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ emailComprador: usuarioId }),
  });
  return response.json();
};

export const addProductToCart = async (carritoId, productoId, quantity, precioTotal) => {
  try {
    const response = await axios.post(`http://localhost:8080/api/v1/carrito/${carritoId}/productos`, {
      productoId,
      quantity,
      precioTotal
    });
    return response.data;
  } catch (error) {
    console.error('Error adding product to cart:', error);
    throw error;
  }
};

export const getCarritoById = async (carritoId) => {
  const response = await fetch(`${BASE_URL}/carrito/${carritoId}`);
  return response.json();
};
