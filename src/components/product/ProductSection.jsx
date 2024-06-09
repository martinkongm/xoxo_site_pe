import React, { useEffect, useState } from "react";
import { NavLink } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { addCart } from '../../redux/action';

const ProductSection = ({ title, data }) => {
    const [modifiedData, setModifiedData] = useState([]);
    const dispatch = useDispatch();

    const addProduct = (producto) => {
        dispatch(addCart(producto));
    };

    useEffect(() => {
        const updatedData = data.map(producto => {
            const modifiedImageUrl = producto.imagenProducto.replace("static", "");
            const fullImageUrl = `http://localhost:8080${modifiedImageUrl}`;
            return { ...producto, imagenProducto: fullImageUrl };
        });
        setModifiedData(updatedData);
    }, [data]);

    if (!modifiedData.length) {
        return null; // No renderizar la sección si no hay productos
    }

    return (
        <div className="product-section mb-5">
            <h4 className="mb-4" style={{ color: '#E45F9A' }}>{title}</h4>
            <div className="row">
                {modifiedData.map((producto) => (
                    <div className="col-md-3 mb-4" key={producto.idProducto}>
                        <div className="card h-100 text-center p-0 border-0">
                            <NavLink to={`/products/${producto.idProducto}`}>
                                <img src={producto.imagenProducto} className="card-img-top" alt={producto.nombreProducto} />
                            </NavLink>
                            <div className="card-body">
                                <h6 className="card-title mb-0" style={{ fontSize: '18px' }}>
                                    <NavLink to={`/products/${producto.idProducto}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                                        {producto.nombreProducto}
                                    </NavLink>
                                </h6>
                                <p className="card-text lead fw-bold">${producto.precioProducto}</p>
                                <button className="btn btn-outline-dark mb-2" onClick={() => addProduct(producto)} style={{ borderRadius: '20px' }}>
                                    Add to Cart
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ProductSection;
