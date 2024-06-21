import React, { useEffect, useState } from "react";
import { NavLink } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { addCart } from '../../redux/action';
import { Modal, Button } from 'react-bootstrap';

const ProductSection = ({ title, data }) => {
    const [modifiedData, setModifiedData] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const dispatch = useDispatch();

    const addProductToCart = (producto) => {
        dispatch(addCart(producto))
            .then(() => {
                setShowModal(true);
            })
            .catch((error) => {
                console.error("Error al agregar el producto al carrito", error);
            });
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
        return null;
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
                                <button className="btn btn-outline-dark mb-2" onClick={() => addProductToCart(producto)} style={{ borderRadius: '20px' }}>
                                    Agregar carrito
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <Modal show={showModal} onHide={() => setShowModal(false)} centered>
                <Modal.Header closeButton>
                    <Modal.Title>Producto Agregado al Carrito</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>El producto ha sido agregado al carrito con éxito.</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={() => setShowModal(false)}>
                        Cerrar
                    </Button>
                    <Button variant="success" onClick={() => {
                        setShowModal(false);
                        window.location.href = "/cart";
                    }}>
                        Pagar Ahora
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default ProductSection;
