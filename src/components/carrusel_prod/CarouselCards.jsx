import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import BootstrapCardDeck from '../carrusel_prod/BootstrapCardDeck';



const CarouselCards = () => {
  return (
    <section className="pt-5 pb-5">
      <Container fluid>
        <Row>
          <Col md={1} />
          <Col md={10} className="mb-4">
            {/* Ajusta el texto "PRODUCTOS POPULARES" para que esté a la izquierda con un margen */}
            <h3 className="mb-3" style={{ color: '#E45F9A', marginLeft: '30px' }}>PRODUCTOS POPULARES 💖</h3>
            {/* Ajusta el tamaño del carrusel para que ocupe más espacio */}
            <BootstrapCardDeck carouselId="carouselExampleIndicators1" style={{ width: '100%', margin: '5 auto' }} />
          </Col>
          <Col md={1} />
        </Row>
      </Container>
    </section>
  );
};

export default CarouselCards;
