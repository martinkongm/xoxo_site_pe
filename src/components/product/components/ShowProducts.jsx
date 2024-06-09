import React from 'react';
import { NavLink } from 'react-router-dom';

const ShowProducts = ({ data, filter, setFilter, filterProducto }) => {
    if (!Array.isArray(filter)) {
        return (
            <div className="col-md-12 text-center">
                <p>No se encontraron productos.</p>
            </div>
        );
    }

    return (
        <div>
            <div className="buttons d-flex justify-content-center mb-5 pb-5">
                <button className="btn btn-outline-dark me-2" onClick={() => setFilter(data)}>All</button>
                <button className="btn btn-outline-dark me-2" onClick={() => setFilter(filterProducto(data, 'Body-splash'))}>Body Splash</button>
                <button className="btn btn-outline-dark me-2" onClick={() => setFilter(filterProducto(data, 'Exfoliante corporal'))}>Exfoliante Corporal</button>
                <button className="btn btn-outline-dark me-2" onClick={() => setFilter(filterProducto(data, 'Manteca corporal'))}>Manteca Corporal</button>
                <button className="btn btn-outline-dark me-2" onClick={() => setFilter(filterProducto(data, 'Manteca emulsionada'))}>Manteca Emulsionada</button>
            </div>
            <div className="row">
                {filter.map((producto) => (
                    <div className="col-md-3 mb-4" key={producto.idProducto}>
                        <div className="card h-100 text-center p-4">
                            <img src={`http://localhost:8080/${producto.imagenProducto}`} className="card-img-top" alt={producto.nombreProducto} />
                            <div className="card-body">
                                <h5 className="card-title mb-0">{producto.nombreProducto}</h5>
                                <p className="card-text lead fw-bold">${producto.precioProducto}</p>
                                <NavLink to={`/products/${producto.idProducto}`} className="btn btn-outline-dark">Ver Detalle</NavLink>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ShowProducts;
