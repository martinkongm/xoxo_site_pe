import React from 'react';
import { Container, Row, Col, Carousel, Button } from 'react-bootstrap';

import BootstrapCardDeck from '../carrusel_prod/BootstrapCardDeck';

const CarouselCards2 = () => {
  return (
    <section className="pt-5 pb-5">
      <Container fluid>
        <Row>
          <Col md={1}></Col>
          <Col md={10} className="mb-4">
            {/* Ajusta el texto "PRODUCTOS POPULARES" para que esté a la izquierda con un margen */}
            <h3 className="mb-3" style={{ color: '#E45F9A', marginLeft: '50px' }}>NUEVO LANZAMIENTO: ¡XOXO BODY SPLASHES! 🍭</h3>
            {/* Ajusta el texto para que esté al mismo nivel de "NUEVO LANZAMIENTO" y justificado */}
            <p style={{ marginLeft: '50px', textAlign: 'justify' }}>
              ¡Una chica XOXO es esa chica segura, con olor y con la piel más suave del mundo! Y para completar tu rutina de mujer empoderada, creamos nuestros BODY SPLASHES, ¡son perfectos para usarlos durante todo el día! 🍭🧁🍉🍓🍑
            </p>
            <BootstrapCardDeck carouselId="carouselExampleIndicators1" style={{ width: '100%', margin: '0 auto' }} />
          </Col>
          <Col md={1}></Col>
        </Row>
      </Container>
    </section>
  );
};

export default CarouselCards2;
