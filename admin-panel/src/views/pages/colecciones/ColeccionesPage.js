// ColeccionesPage.js
import React from 'react';
import {
  CContainer,
  CRow,
  CCol,
  CCard,
  CCardBody,
  CCardHeader
} from '@coreui/react';
import ColeccionesList from '../../../components/ColeccionesList';

const ColeccionesPage = () => {
  return (
    <CContainer>
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              <h3>Colecciones</h3>
            </CCardHeader>
            <CCardBody>
              <ColeccionesList />
            </CCardBody>
          </CCard>
        </CCol>
      </CRow>
    </CContainer>
  );
};

export default ColeccionesPage;
