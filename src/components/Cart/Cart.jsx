import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { NavLink } from 'react-router-dom';
import { addCart, delCart } from '../../redux/action/index';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { addProductToCart } from '../../services/carritoService';
import ShippingCalculator from './ShippingCalculator';
import './Cart.css';

const Cart = () => {
  const cart = useSelector((state) => state.cart.cart || []); // Asegurar que obtenemos el cart del estado
  const dispatch = useDispatch();

  const handleButton = async (product, action) => {
    const carritoId = localStorage.getItem('carritoId');
    if (!carritoId) return; // Si no hay carritoId, salir

    try {
      if (action === 'increment') {
        await addProductToCart(carritoId, product.idProducto, product.quantity + 1, product.precioProducto * (product.quantity + 1));
        dispatch(addCart(product));
      } else if (action === 'decrement') {
        if (product.quantity > 1) {
          await addProductToCart(carritoId, product.idProducto, product.quantity - 1, product.precioProducto * (product.quantity - 1));
          dispatch(delCart(product));
        }
      }
    } catch (error) {
      console.error('Error updating cart', error);
    }
  };

  const handleRemove = (product) => {
    dispatch(delCart(product));
  };

  const subtotal = cart.reduce((total, item) => total + item.precioProducto * item.quantity, 0);

  return (
    <div className="container py-5">
      <h1 className="text-right font-weight-bold">Mi Carrito</h1>
      <div className="row">
        <div className="col-12">
          <div className="d-flex justify-content-between font-weight-bold py-2">
            <div className="col-4">PRODUCTO</div>
            <div className="col-2">PRECIO</div>
            <div className="col-2">CANTIDAD</div>
            <div className="col-2">TOTAL</div>
            <div className="col-2"></div> {/* Icono de eliminar */}
          </div>
          <hr />
          {cart.length === 0 ? (
            <div className="text-center my-5">
              <p>Tu carrito está vacío</p>
              <NavLink to="/" className="btn btn-dark">Agrega productos</NavLink>
            </div>
          ) : (
            cart.map((item) => (
              <div key={item.idProducto} className="d-flex justify-content-between align-items-center py-2">
                <div className="col-4 d-flex align-items-center">
                  <img src={item.imagenProducto} alt={item.nombreProducto} className="img-thumbnail cart-product-image" />
                  <div className="ml-3">
                    <p className="mb-0">{item.nombreProducto}</p>
                    <small className="text-muted">{item.nombreColeccion}</small>
                    <div className="text-warning">
                      Rating {item.rating && item.rating.rate} <i className="fa fa-star"></i>
                    </div>
                  </div>
                </div>
                <div className="col-2">S/. {item.precioProducto}</div>
                <div className="col-2 d-flex align-items-center">
                  <button className="btn btn-outline-secondary" onClick={() => handleButton(item, 'decrement')}>-</button>
                  <span className="mx-2">{item.quantity}</span>
                  <button className="btn btn-outline-secondary" onClick={() => handleButton(item, 'increment')}>+</button>
                </div>
                <div className="col-2">S/. {(item.precioProducto * item.quantity).toFixed(2)}</div>
                <div className="col-2">
                  <button className="btn btn-outline-danger" onClick={() => handleRemove(item)}>
                    <FontAwesomeIcon icon={faTrash} />
                  </button>
                </div>
              </div>
            ))
          )}
        </div>
      </div>
      <div style={{ marginTop: '180px' }}>
        <ShippingCalculator subtotal={subtotal} />
      </div>
    </div>
  );
};

export default Cart;
