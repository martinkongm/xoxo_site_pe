import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import VerColecciones from '../pages/VerColecciones';
import AñadirColeccion from '../pages/AñadirColeccion';
import DetalleColeccion from '../pages/DetalleColeccion';
import ModificarColeccion from '../pages/ModificarColeccion';
import VerProductos from '../pages/VerProductos';
import AñadirProducto from '../pages/AñadirProducto';
import DetalleProducto from '../pages/DetalleProducto';
import ModificarProducto from '../pages/ModificarProducto';


const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/colecciones" element={<VerColecciones />} />
        <Route exact path="/colecciones/nueva" element={<AñadirColeccion />} />
        <Route exact path="/colecciones/:id" element={<DetalleColeccion />} />
        <Route exact path="/colecciones/:id/modificar" element={<ModificarColeccion />} />
        <Route exact path="/productos" element={<VerProductos />} />
        <Route exact path="/productos/nuevo" element={<AñadirProducto />} />
        <Route exact path="/productos/:id" element={<DetalleProducto />} />
        <Route exact path="/productos/:id/modificar" element={<ModificarProducto />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
