import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';

const SearchResults = () => {
    const [productos, setProductos] = useState([]);
    const [error, setError] = useState('');
    const location = useLocation();

    useEffect(() => {
        const query = new URLSearchParams(location.search).get('query');
        if (query) {
            fetchProductos(query);
        }
    }, [location.search]);

    const fetchProductos = async (nombre) => {
        try {
            const response = await axios.get(`api/v1/productos/buscar?nombre=${nombre}`);
            const productosData = response.data?.object || [];
            setProductos(productosData);
        } catch (error) {
            setError('Hubo un error al buscar los productos.');
        }
    };

    return (
        <div>
            <h2>Resultados de la búsqueda</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <ul>
                {productos.length > 0 ? (
                    productos.map((producto) => (
                        <li key={producto.idProducto}>
                            <h3>{producto.nombreProducto}</h3>
                            <p>Precio: {producto.precioProducto}</p>
                            {/* Otros detalles del producto */}
                        </li>
                    ))
                ) : (
                    <p>No se encontraron productos.</p>
                )}
            </ul>
        </div>
    );
};

export default SearchResults;
