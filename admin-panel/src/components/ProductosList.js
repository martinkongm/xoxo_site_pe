// ProductosList.js
import React, { useEffect, useState } from 'react';
import {
  CCard,
  CCardBody,
  CCardHeader,
  CRow,
  CCol
} from '@coreui/react';
import apiClient from '../api/api'; // Asegúrate de importar tu cliente API configurado

const ProductosList = ({ idColeccion }) => {
  const [productos, setProductos] = useState([]);

  useEffect(() => {
    const fetchProductos = async () => {
      try {
        const response = await apiClient.get(`/productos/${idColeccion}`);
        setProductos(response.data);
      } catch (error) {
        console.error('Error al obtener los productos:', error);
      }
    };

    fetchProductos();
  }, [idColeccion]);

  return (
    <CRow>
      {productos.map(producto => (
        <CCol key={producto.idProducto} sm="6" lg="4">
          <CCard>
            <CCardHeader>{producto.nombreProducto}</CCardHeader>
            <CCardBody>
              <p>Precio: {producto.precioProducto}</p>
              <p>Tamaño: {producto.tamanoProducto}</p>
              <p>Beneficios: {producto.beneficiosProducto}</p>
              <p>Stock: {producto.stockProducto}</p>
              <img src={producto.imagenProducto} alt={producto.nombreProducto} />
            </CCardBody>
          </CCard>
        </CCol>
      ))}
    </CRow>
  );
};

export default ProductosList;
