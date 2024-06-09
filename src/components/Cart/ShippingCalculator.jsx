import React, { useState } from 'react';
import './ShippingCalculator.css';

const ShippingCalculator = ({ subtotal }) => {
  const [country, setCountry] = useState('');
  const [province, setProvince] = useState('');
  const [address, setAddress] = useState('');

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
            <input type="text" value={country} onChange={(e) => setCountry(e.target.value)} placeholder="Pais" />
          </div>
          <div className="form-group">
            <label>Provincia</label>
            <input type="text" value={province} onChange={(e) => setProvince(e.target.value)} placeholder="Provincia" />
          </div>
          <div className="form-group">
            <label>Dirección</label>
            <input type="text" value={address} onChange={(e) => setAddress(e.target.value)} placeholder="Dirección" />
          </div>
          <button type="button" onClick={handleCalculate} className="btn btn-dark" >Calcular</button>
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
        </div>
        <button type="button" className="btn btn-dark" >Checkout</button>
      </div>
    </div>
  );
};

export default ShippingCalculator;
