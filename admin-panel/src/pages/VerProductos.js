import React, { useEffect, useState } from 'react';
import { Table, Button, Container } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import './VerProductos.css';

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

  const handleEliminarProducto = (idProducto) => {
    axios.delete(`http://localhost:8080/api/v1/producto/${idProducto}`)
      .then(() => {
        setProductos(productos.filter(producto => producto.idProducto !== idProducto));
        alert('Producto eliminado');
      })
      .catch(error => {
        console.error('Error eliminando el producto:', error);
      });
  };

  return (
    <Container>
      <h1>Productos</h1>
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
                <Button variant="primary" onClick={() => navigate(`/productos/${producto.idProducto}`)}>Ver</Button>
                <Button variant="warning" className="btn-mod ml-2" onClick={() => navigate(`/productos/${producto.idProducto}/modificar/`)}>Modificar</Button>
                <Button variant="danger" className="btn-del ml-2" onClick={() => handleEliminarProducto(producto.idProducto)}>Eliminar</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default VerProductos;
