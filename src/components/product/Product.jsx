import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { NavLink } from 'react-router-dom';
import Skeleton from 'react-loading-skeleton';
import { useDispatch } from 'react-redux';
import { addCart } from '../../redux/action';
import { fetchProductById } from '../../api/Api';

const Product = () => {
  const { idProducto } = useParams();
  const [producto, setProducto] = useState({});
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();

  const addProduct = (producto) => {
    dispatch(addCart(producto));
  }

  useEffect(() => {
    const getProduct = async () => {
      setLoading(true);
      const product = await fetchProductById(idProducto);

      // Modifica la URL de la imagen aquí
      const modifiedImageUrl = product.imagenProducto.replace("static", "");
      const fullImageUrl = `http://localhost:8080${modifiedImageUrl}`;

      setProducto({ ...product, imagenProducto: fullImageUrl });
      setLoading(false);
    };
    getProduct();
  }, [idProducto]);

  const Loading = () => {
    return (
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
  };

  const ShowProduct = () => {
    return (
      <>
        <div className="col-md-6">
          <img src={producto.imagenProducto} alt={producto.nombreProducto} height="400px" width="400px" style={{ borderRadius: '10px', marginLeft: '100px' }} />
          <p className="lead" style={{ textAlign: 'justify', marginTop: '30px' }}>
          ⚠️ Lea la lista de ingredientes antes de comprar uno de nuestros productos, si tiene alergias a alguno de nuestros 
          ingredientes o si es sensible a las fragancias y propenso a tener migrañas, evite comprar el producto. ⚠️
          </p>
        </div>
        <div className="col-md-6">
          <h4 className="text-uppercase text-black-80" style={{ fontSize: '2rem' }}>
            {producto.nombreColeccion} |
          </h4>
          <h1 className="display-5 text-uppercase text-black-100 ">
          |{producto.nombreProducto} 
          </h1>
          <p className="lead fw-bolder">
            Rating {producto.rating && producto.rating.rate}
            <i className="fa fa-star"></i>
          </p>
          <h3 className="my-4" style={{ fontSize: '1.5rem' }}>
            De S/. {producto.precioProducto}
          </h3>
          <p className="lead">{producto.beneficiosProducto}</p>
          <button className="btn btn-outline-dark mb-2" onClick={() => addProduct(producto)} style={{ width: '60%', color: '#F1AEC0' }}>
            Add to Cart
          </button>
          <NavLink to="/cart" className="btn mb-2 px-3 py-2" style={{ width: '60%', backgroundColor: '#F1AEC0', borderColor: '#F1AEC0' }}>
            Go to Cart
          </NavLink>
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
