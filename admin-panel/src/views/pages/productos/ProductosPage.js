// ProductosPage.js
import React from 'react';
import {
  CContainer,
  CRow,
  CCol,
  CCard,
  CCardBody,
  CCardHeader
} from '@coreui/react';
import ProductosList from '../../../components/ProductosList'; // Asegúrate de que la ruta de importación sea correcta

const ProductosPage = () => {
  // Suponiendo que tienes alguna lógica para obtener idColeccion, por ejemplo, desde la URL
  const idColeccion = obtenerIdColeccion(); // Debes implementar esta función según tu lógica de enrutamiento

  return (
    <CContainer>
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              <h3>Productos de la Colección</h3>
            </CCardHeader>
            <CCardBody>
              <ProductosList idColeccion={idColeccion} />
            </CCardBody>
          </CCard>
        </CCol>
      </CRow>
    </CContainer>
  );
};

export default ProductosPage;
