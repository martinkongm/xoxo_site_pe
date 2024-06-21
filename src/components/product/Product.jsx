import React, { useState, useEffect } from 'react';
import { useParams, NavLink } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { addCart } from '../../redux/action';
import { fetchProductById } from '../../api/Api';
import Skeleton from 'react-loading-skeleton';

const Product = () => {
  const { idProducto } = useParams();
  const [producto, setProducto] = useState({});
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();

  const addProduct = (producto) => {
    dispatch(addCart(producto));
  };

  useEffect(() => {
    const getProduct = async () => {
      setLoading(true);
      const product = await fetchProductById(idProducto);
      const modifiedImageUrl = product.imagenProducto.replace("static", "");
      const fullImageUrl = `http://localhost:8080${modifiedImageUrl}`;
      setProducto({ ...product, imagenProducto: fullImageUrl });
      setLoading(false);
    };
    getProduct();
  }, [idProducto]);

  const Loading = () => (
    <div>
      <div className="col-md-6" style={{ lineHeight: 2 }}>
        <Skeleton height={400} />
      </div>
      <div className="col-md-6">
        <Skeleton height={300} width={300} />
        <Skeleton height={75} />
        <Skeleton height={25} width={150} />
        <Skeleton height={50} />
        <Skeleton height={150} />
        <Skeleton height={50} width={100} style={{ marginLeft: 6 }} />
      </div>
    </div>
  );

  const ShowProduct = () => {
    return (
      <>
        <div className="col-md-6">
          <img src={producto.imagenProducto} alt={producto.nombreProducto} height="350px" width="350px" style={{ borderRadius: '8px', marginLeft:'70px' }} />
          <p className="text-justify mt-5">
            ⚠️ Lea la lista de ingredientes antes de comprar uno de nuestros productos, si tiene alergias a alguno de nuestros ingredientes o si es sensible a las fragancias y propenso a tener migrañas, evite comprar el producto ⚠️
          </p>
        </div>
        <div className="col-md-6" style={{ paddingRight: '15px' }}>
          <h1 className="display-5 text-uppercase">{producto.nombreProducto}|</h1>
          <h3 className="text-uppercase text-black-100">|{producto.nombreColeccion}</h3>
          <p className="lead fw-bolder">
            Rating {producto.ratingProducto} <i className="fa fa-star"></i>
          </p>
          <h5 className="display-6 fw-semibold my-4">Desde S/.{producto.precioProducto}</h5>
          <p className="lead">{producto.beneficio}</p>
          <div className="d-grid gap-3">
            <button className="btn btn-outline-dark py-3" onClick={() => addProduct(producto)} style={{ backgroundColor: '#FADDE9', border: 'none' }}>
              Agregar al carrito
            </button>
            <NavLink to="/cart" className="btn btn-dark py-3">
              Ir al carrito
            </NavLink>
          </div>
        </div>
      </>
    );
  };

  return (
    <div>
      <div className="container py-5">
        <div className="row py-4">
          {loading ? <Loading /> : <ShowProduct />}
        </div>
      </div>
    </div>
  );
};

export default Product;
