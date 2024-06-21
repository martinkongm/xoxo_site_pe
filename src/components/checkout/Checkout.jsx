import React from 'react';
import PaypalCheckoutButton from "../checkout/PaypalCheckoutButton";

function Checkout() {
    const products = [
        { nombre: "Laptop", price: 899 },
        { nombre: "Monitor", price: 399 },
        { nombre: "Keyboard", price: 149 },
    ];

    return (
        <div className="checkout">
            <h1>PayPal Checkout</h1>
            <p className="checkout-title">
                Design and Code React PayPal Checkout Procedure
            </p>
            <div className="products-list">
                {products.map((product, index) => (
                    <div key={index} className="product-item">
                        <h2>{product.nombre}</h2>
                        <p className="checkout-description">
                            Learn how to build a website with React JS
                        </p>
                        <h1 className="checkout-price">${product.price}</h1>
                        <img className="product-image" alt="Product Image" />
                    </div>
                ))}
            </div>
            <div className="separator"></div>
            <div className="paypal">
                <p className="checkout-title">PAY WITH PAYPAL</p>
                <div className="paypal-button-container">
                    <PaypalCheckoutButton products={products} />
                </div>
            </div>
        </div>
    );
}

export default Checkout;