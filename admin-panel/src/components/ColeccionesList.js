// ColeccionesList.js
import React, { useEffect, useState } from 'react';
import {
  CCard,
  CCardBody,
  CCardHeader,
  CRow,
  CCol,
  CButton
} from '@coreui/react';
import { Link } from 'react-router-dom'; // Importa Link desde React Router
import apiClient from '../api/api'; // Asegúrate de importar tu cliente API configurado

const ColeccionesList = () => {
  const [colecciones, setColecciones] = useState([]);

  useEffect(() => {
    const fetchColecciones = async () => {
      try {
        const response = await apiClient.get('/colecciones');
        // Suponiendo que response.data.object es un array de objetos con la estructura de ColeccionResponseDto
        setColecciones(response.data.object);
      } catch (error) {
        console.error('Error al obtener las colecciones:', error);
      }
    };

    fetchColecciones();
  }, []);

  return (
    <CRow>
      {colecciones.map(coleccion => (
        <CCol key={coleccion.idColeccion} sm="6" lg="4">
          <CCard>
            <CCardHeader>{coleccion.nombreColeccion}</CCardHeader>
            <CCardBody>
              {/* Utiliza Link para el botón "Ver productos" */}
              <Link to={`/productos/${coleccion.idColeccion}`}>
                <CButton color="primary">Ver productos</CButton>
              </Link>
            </CCardBody>
          </CCard>
        </CCol>
      ))}
    </CRow>
  );
};

export default ColeccionesList;
