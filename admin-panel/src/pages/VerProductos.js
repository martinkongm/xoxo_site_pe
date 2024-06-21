import React, { useEffect, useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const VerProductos = () => {
  const [productos, setProductos] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/productos')
      .then(response => {
        setProductos(response.data.object);
      })
      .catch(error => {
        console.error('Error fetching productos:', error);
      });
  }, []);

  const eliminarProducto = (id) => {
    axios.delete(`http://localhost:8080/api/v1/producto/${id}`)
      .then(() => {
        alert('Producto eliminado');
        setProductos(productos.filter(producto => producto.idProducto !== id));
      })
      .catch(error => {
        console.error('Error deleting producto:', error);
      });
  };

  return (
    <div>
      <h1>Productos</h1>
      <Button onClick={() => navigate('/productos/nuevo')}>Añadir Producto</Button>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {productos.map(producto => (
            <tr key={producto.idProducto}>
              <td>{producto.nombreProducto}</td>
              <td>{producto.precioProducto}</td>
              <td>
                <Link to={`/productos/${producto.idProducto}`}>Ver</Link>{' '}
                <Link to={`/productos/${producto.idProducto}/modificar`}>Modificar</Link>{' '}
                <Button variant="danger" onClick={() => eliminarProducto(producto.idProducto)}>Eliminar</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default VerProductos;
