import React from 'react';
import {
  CDropdown,
  CDropdownToggle,
  CDropdownMenu,
  CDropdownItem,
} from '@coreui/react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Sidebar.css';

const Sidebar = () => {
  return (
    <div className='container'>
      <h1 className='titulo'>XOXO Admin</h1>
      <CDropdown className='coleccionDropdown'>
        <CDropdownToggle color="primary">
          Colecciones
        </CDropdownToggle>
        <CDropdownMenu>
          <CDropdownItem href="/colecciones">Ver Colecciones</CDropdownItem>
          <CDropdownItem href="/colecciones/nueva">Añadir Colección</CDropdownItem>
        </CDropdownMenu>
      </CDropdown>
      <CDropdown className='productoDropdown'>
        <CDropdownToggle color="primary">
          Productos
        </CDropdownToggle>
        <CDropdownMenu>
          <CDropdownItem href="/productos">Ver Productos</CDropdownItem>
          <CDropdownItem href="/productos/nuevo">Añadir Producto</CDropdownItem>
        </CDropdownMenu>
      </CDropdown>
    </div>
  );
};

export default Sidebar;
