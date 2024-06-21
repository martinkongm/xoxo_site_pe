import React, { useState } from 'react';
import { Form, Button, Container } from 'react-bootstrap';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const AñadirColeccion = () => {
  const [nombre, setNombre] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('http://localhost:8080/api/v1/coleccion', { nombreColeccion: nombre })
      .then(() => {
        alert('Colección añadida');
      })
      .catch(error => {
        console.error('Error adding coleccion:', error);
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
        <Button type="submit" className="mt-3">Añadir Colección</Button>
      </Form>
    </Container>
  );
};

export default AñadirColeccion;
