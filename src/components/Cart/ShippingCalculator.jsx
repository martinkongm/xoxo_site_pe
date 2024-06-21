import React, { useState, useEffect } from 'react';
import './ShippingCalculator.css';
import axios from 'axios';

const ShippingCalculator = ({ subtotal, carritoId }) => {
  const [country, setCountry] = useState('');
  const [province, setProvince] = useState('');
  const [address, setAddress] = useState('');
  const [preferenceId, setPreferenceId] = useState(null);


  const createPreference = async () => {
    try {
      // Fetching the cart details from the backend using the cartId
      const responseCarrito = await axios.get(`http://localhost:8080/api/v1/carrito/${carritoId}`);
      const carritoDetalles = responseCarrito.data.object.detalles;

      // Creating a preference for Mercado Pago
      const responseMP = await axios.post('http://localhost:8080/api/v1/mp', carritoDetalles);
      return responseMP.data; // Assuming the preference ID is returned as the response data
    } catch (error) {
      console.error('Error creating preference:', error);
      return null;
    }
  };

  const handleBuy = async () => {
    const id = await createPreference();
    if (id) {
      setPreferenceId(id);
    }
  };

  const handleCalculate = () => {
    // Lógica para calcular el envío
    console.log('Calculando envío...');
  };

  return (
    <div className="shipping-calculator">
      <div className="form-section mt-3 mx-5 mb-3">
        <h2>Envío</h2>
        <p>Ingresa los detalles para calcular el costo de envío:</p>
        <form>
          <div className="form-group">
            <label>País</label>
            <input type="text" value={country} onChange={(e) => setCountry(e.target.value)} placeholder="País" />
          </div>
          <div className="form-group">
            <label>Provincia</label>
            <input type="text" value={province} onChange={(e) => setProvince(e.target.value)} placeholder="Provincia" />
          </div>
          <div className="form-group">
            <label>Dirección</label>
            <input type="text" value={address} onChange={(e) => setAddress(e.target.value)} placeholder="Dirección" />
          </div>
          <button type="button" onClick={handleCalculate} className="btn btn-dark">Calcular</button>
        </form>
      </div>
      <div className="summary-section mt-3 mx-5">
        <div className="summary-item">
          <div className="font-weight-bold">SUBTOTAL</div>
          <div className="value">S/. {subtotal.toFixed(2)}</div>
        </div>
        <div className="summary-item">
          <div className="font-weight-bold">SHIPPING MODE</div>
          <div className="value">Standard</div>
        </div>
        <hr />
        <div className="summary-item">
          <div className="label">TOTAL</div>
          <div className="value">S/. {subtotal.toFixed(2)}</div>
          <button onClick={handleBuy} className="btn btn-dark">Comprar</button>
        </div>
      </div>
    </div>
  );
};

export default ShippingCalculator;
