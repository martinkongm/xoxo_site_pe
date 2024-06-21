import React, { useEffect, useState } from 'react';
import { Form, Button, Container } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const ModificarColeccion = () => {
  const { id } = useParams();
  const [nombre, setNombre] = useState('');
  const [productos, setProductos] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/v1/coleccion/${id}`)
      .then(response => {
        setNombre(response.data.object.nombreColeccion);
        setProductos(response.data.object.productosColeccion); //Arreglo de string de productos
      })
      .catch(error => {
        console.error('Error fetching coleccion:', error);
      });
  }, [id]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const productosColeccion = productos.map(producto => ({ idProducto: producto.idProducto }));
    axios.put(`http://localhost:8080/api/v1/coleccion/${id}`, { idColeccion: id, nombreColeccion: nombre, productosColeccion })
      .then(() => {
        alert('Colección modificada');
        navigate('/colecciones');
      })
      .catch(error => {
        console.error('Error updating coleccion:', error);
      });
  };

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="nombre">
          <Form.Label>Nombre de la Colección</Form.Label>
          <Form.Control
            type="text"
            value={nombre}
            onChange={e => setNombre(e.target.value)}
          />
        </Form.Group>
        <Button type="submit" className="mt-3">Modificar Colección</Button>
      </Form>
    </Container>
  );
};

export default ModificarColeccion;
