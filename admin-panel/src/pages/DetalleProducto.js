import React, { useEffect, useState } from 'react';
import { Card, Button } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const DetalleProducto = () => {
  const { id } = useParams();
  const [producto, setProducto] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/v1/producto/${id}`)
      .then(response => {
        setProducto(response.data.object);
      })
      .catch(error => {
        console.error('Error fetching producto:', error);
      });
  }, [id]);

  if (!producto) {
    return <div>Cargando...</div>;
  }

  return (
    <Card>
      <Card.Img variant="top" src={`http://localhost:8080${producto.imagenProducto}`} width={150} />
      <Card.Body>
        <Card.Title>{producto.nombreProducto}</Card.Title>
        <Card.Text>Precio: {producto.precioProducto}</Card.Text>
        <Card.Text>Tamaño: {producto.tamanoProducto}</Card.Text>
        <Card.Text>Beneficios: {producto.beneficiosProducto}</Card.Text>
        <Card.Text>Stock: {producto.stockProducto}</Card.Text>
        <Button onClick={() => navigate('/productos')}>Volver</Button>
      </Card.Body>
    </Card>
  );
};

export default DetalleProducto;
