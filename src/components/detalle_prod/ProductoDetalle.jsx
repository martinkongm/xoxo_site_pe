import React, { useState } from 'react';
import { Container, Row, Col, Image, Form, Button } from 'react-bootstrap';

const ProductoDetalle = () => {
  const [cantidad, setCantidad] = useState(1);

  const handleCantidadChange = (e) => {
    setCantidad(parseInt(e.target.value));
  };

  const handleAgregarCarrito = () => {
    // Lógica para agregar al carrito
    console.log(`Agregado al carrito: ${cantidad} productos`);
  };

  const handleComprarAhora = () => {
    // Lógica para comprar ahora
    console.log(`Comprar ahora: ${cantidad} productos`);
  };

  return (
    <Container>
      <Row>
        <Col md={6}>
          <h2>Nombre del Producto</h2>
          <p>Descripción del producto.</p>
          <div>
            <span>Rating: </span>
            <i className="bi bi-star-fill"></i>
            <i className="bi bi-star-fill"></i>
            <i className="bi bi-star-fill"></i>
            <i className="bi bi-star-fill"></i>
            <i className="bi bi-star-half"></i>
          </div>
          <p>Precio: $99.99</p>
          <Form.Group controlId="cantidad">
            <Form.Label>Cantidad:</Form.Label>
            <Form.Control
              type="number"
              min="1"
              value={cantidad}
              onChange={handleCantidadChange}
            />
          </Form.Group>
          <Button variant="primary" onClick={handleAgregarCarrito}>Agregar al Carrito</Button>
        </Col>
        <Col md={6}>
          <Image src="../detalle_prod/TAMANHOS_22_3d6685f9-8106-4eb2-be30-2d4004fb7762.jpg" fluid alt="Imagen del Producto" />
        </Col>
      </Row>
      <Row className="mt-3">
        <Col>
          <Button variant="success" onClick={handleComprarAhora}>Comprar Ahora</Button>
        </Col>
      </Row>
    </Container>
  );
};

export default ProductoDetalle;
