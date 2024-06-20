import React, { useEffect, useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const ModificarColeccion = () => {
  const { id } = useParams();
  const [nombre, setNombre] = useState('');
  const [productos, setProductos] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/v1/coleccion/${id}`)
      .then(response => {
        setNombre(response.data.object.nombreColeccion);
        setProductos(response.data.object.productosColeccion)
      })
      .catch(error => {
        console.error('Error fetching coleccion:', error);
      });
  }, [id]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const productosColeccion = productos.map(producto => ({ 
      idProducto: producto.idProducto,
      nombreProducto: producto.nombreProducto,
      precioProducto: producto.precioProducto,
      tamanoProducto: producto.tamanoProducto,
      beneficiosProducto: producto.beneficiosProducto,
      nombreColeccion: producto.nombreColeccion,
      stockProducto: producto.stockProducto,
      imagenProducto: producto.imagenProducto
    
    }));
    axios.put(`http://localhost:8080/api/v1/coleccion/${id}`, { idColeccion: id, nombreColeccion: nombre, productosColeccion: productosColeccion })
      .then(() => {
        alert('Colección modificada');
        navigate('/colecciones');
      })
      .catch(error => {
        console.error('Error updating coleccion:', error);
      });
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group controlId="nombre">
        <Form.Label>Nombre de la Colección</Form.Label>
        <Form.Control
          type="text"
          value={nombre}
          onChange={e => setNombre(e.target.value)}
        />
      </Form.Group>
      <Button type="submit">Modificar Colección</Button>
    </Form>
  );
};

export default ModificarColeccion;
