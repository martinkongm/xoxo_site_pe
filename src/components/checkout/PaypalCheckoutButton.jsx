import React, {useState} from 'react';
import {PayPalScriptProvider, PayPalButtons} from "@paypal/react-paypal-js";

const PaypalCheckoutButton = ({ products }) => {
    const [paidFor, setPaidFor] = useState(false);
    const [error, setError] = useState(null);

    const handleApprove = (orderId) => {
        setPaidFor(true);
    };

    if (paidFor) {
        alert("Thank You for purchasing from Eazy2Code");
    }

    if (error) {
        alert(error);
    }

    return (
        <PayPalScriptProvider>
            <PayPalButtons
                onClick={(data, actions) => {
                    const hasAlreadyBoughtCourse = false;
                    if (hasAlreadyBoughtCourse) {
                        setError("You already bought this course");
                        return actions.reject();
                    } else {
                        return actions.resolve();
                    }
                }}
                createOrder={(data, actions) => {
                    // Validar que products sea un array y que cada producto tenga la propiedad price
                    if (!Array.isArray(products) || products.some(product => product.price == null)) {
                        setError("Invalid product data");
                        return actions.reject();
                    }

                    const totalValue = products.reduce((total, product) => total + product.price, 0).toFixed(2);
                    const items = products.map(product => ({
                        name: product.nombre,
                        description: 'Description of the item',
                        unit_amount: {
                            value: product.price.toFixed(2),
                            currency_code: 'USD',
                        },
                        quantity: 1,
                    }));

                    return actions.order.create({
                        purchase_units: [
                            {
                                amount: {
                                    value: totalValue,
                                    currency_code: 'USD',
                                    breakdown: {
                                        item_total: {
                                            currency_code: 'USD',
                                            value: totalValue,
                                        }
                                    }
                                },
                                items: items,
                            },
                        ],
                    });
                }}
                onApprove={async (data, actions) => {
                    const order = await actions.order.capture();
                    console.log("order", order);

                    handleApprove(data.orderID);
                }}
                onCancel={() => { }}
                onError={(err) => {
                    setError(err);
                    console.log("PayPal Checkout onError", err);
                }}
            />
        </PayPalScriptProvider>
    );
};

export default PaypalCheckoutButton;