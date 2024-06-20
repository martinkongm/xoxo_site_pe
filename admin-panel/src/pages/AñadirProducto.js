import React, { useEffect, useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import axios from 'axios';

const AñadirProducto = () => {
  const [nombre, setNombre] = useState('');
  const [precio, setPrecio] = useState('');
  const [tamano, setTamano] = useState('');
  const [beneficios, setBeneficios] = useState('');
  const [imagen, setImagen] = useState('');
  const [stock, setStock] = useState('');
  const [coleccionId, setColeccionId] = useState('');
  const [colecciones, setColecciones] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/colecciones')
      .then(response => {
        setColecciones(response.data.object);
      })
      .catch(error => {
        console.error('Error fetching colecciones:', error);
      });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    const nuevoProducto = {
      nombreProducto: nombre,
      precioProducto: parseFloat(precio),
      tamanoProducto: parseInt(tamano),
      beneficiosProducto: beneficios,
      imagenProducto: imagen,
      stockProducto: parseInt(stock),
      idColeccion: coleccionId,
    };
    axios.post('http://localhost:8080/api/v1/producto', nuevoProducto)
      .then(() => {
        alert('Producto añadido');
      })
      .catch(error => {
        console.error('Error adding producto:', error);
      });
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group controlId="nombre">
        <Form.Label>Nombre del Producto</Form.Label>
        <Form.Control
          type="text"
          value={nombre}
          onChange={e => setNombre(e.target.value)}
        />
      </Form.Group>
      <Form.Group controlId="precio">
        <Form.Label>Precio</Form.Label>
        <Form.Control
          type="number"
          value={precio}
          onChange={e => setPrecio(e.target.value)}
        />
      </Form.Group>
      <Form.Group controlId="tamano">
        <Form.Label>Tamaño</Form.Label>
        <Form.Control
          type="number"
          value={tamano}
          onChange={e => setTamano(e.target.value)}
        />
      </Form.Group>
      <Form.Group controlId="beneficios">
        <Form.Label>Beneficios</Form.Label>
        <Form.Control
          type="text"
          value={beneficios}
          onChange={e => setBeneficios(e.target.value)}
        />
      </Form.Group>
      <Form.Group controlId="imagen">
        <Form.Label>URL de la Imagen</Form.Label>
        <Form.Control
          type="text"
          value={imagen}
          onChange={e => setImagen(e.target.value)}
        />
      </Form.Group>
      <Form.Group controlId="stock">
        <Form.Label>Stock</Form.Label>
        <Form.Control
          type="number"
          value={stock}
          onChange={e => setStock(e.target.value)}
        />
      </Form.Group>
      <Form.Group controlId="coleccionId">
        <Form.Label>Colección</Form.Label>
        <Form.Control
          as="select"
          value={coleccionId}
          onChange={e => setColeccionId(e.target.value)}
        >
          <option value="">Selecciona una colección</option>
          {colecciones.map(coleccion => (
            <option key={coleccion.idColeccion} value={coleccion.idColeccion}>
              {coleccion.nombreColeccion}
            </option>
          ))}
        </Form.Control>
      </Form.Group>
      <Button type="submit">Añadir Producto</Button>
    </Form>
  );
};

export default AñadirProducto;
