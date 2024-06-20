import { CNavGroup, CNavItem } from '@coreui/react';

const Sidebar = () => {
  return (
    <>
      <CNavGroup toggler="Colecciones">
        <CNavItem href="/colecciones">Ver Colecciones</CNavItem>
        <CNavItem href="/colecciones/nueva">Añadir Colección</CNavItem>
      </CNavGroup>
      <CNavGroup toggler="Productos">
        <CNavItem href="/productos">Ver Productos</CNavItem>
        <CNavItem href="/productos/nuevo">Añadir Producto</CNavItem>
      </CNavGroup>
    </>
  );
};

export default Sidebar;
