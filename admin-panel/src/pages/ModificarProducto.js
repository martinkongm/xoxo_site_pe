import React, { useEffect, useState } from 'react';
import { Form, Button, Container } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const ModificarProducto = () => {
  const { id } = useParams();
  const [nombre, setNombre] = useState('');
  const [precio, setPrecio] = useState('');
  const [tamano, setTamano] = useState('');
  const [beneficios, setBeneficios] = useState('');
  const [imagen, setImagen] = useState('');
  const [stock, setStock] = useState('');
  const [coleccionId, setColeccionId] = useState('');
  const [colecciones, setColecciones] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/v1/producto/${id}`)
      .then(response => {
        const producto = response.data.object;
        setNombre(producto.nombreProducto);
        setPrecio(producto.precioProducto);
        setTamano(producto.tamanoProducto);
        setBeneficios(producto.beneficiosProducto);
        setImagen(producto.imagenProducto);
        setStock(producto.stockProducto);
        // setColeccionId(producto.idColeccion);
      })
      .catch(error => {
        console.error('Error fetching producto:', error);
      });

    axios.get('http://localhost:8080/api/v1/colecciones')
      .then(response => {
        setColecciones(response.data.object);
      })
      .catch(error => {
        console.error('Error fetching colecciones:', error);
      });
  }, [id]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const productoModificado = {
      idProducto: id,
      nombreProducto: nombre,
      precioProducto: parseFloat(precio),
      tamanoProducto: parseInt(tamano),
      beneficiosProducto: beneficios,
      imagenProducto: imagen,
      stockProducto: parseInt(stock),
      // idColeccion: coleccionId,
    };
    axios.put(`http://localhost:8080/api/v1/producto/${id}`, productoModificado)
      .then(() => {
        alert('Producto modificado');
        navigate('/productos');
      })
      .catch(error => {
        console.error('Error updating producto:', error);
      });
  };

  return (
    <Container>
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
        <Button type="submit" className="mt-3">Modificar Producto</Button>
      </Form>
    </Container>
  );
};

export default ModificarProducto;
