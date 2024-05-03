import React from 'react';
import { Container, Row, Col, Card, Carousel } from 'react-bootstrap';

// Importar BootstrapCardDeck
import BootstrapCardDeck from './BootstrapCardDeck';

const CarouselCards = () => {
  return (
    <section className="pt-5 pb-5">
      <Container>
        <Row>
          <Col md={12} className="mb-4">
            <h3 className="mb-3" style={{ color: '#E45F9A' }}>PRODUCTOS POPULARES 💖</h3>
          </Col>
          <Col md={6} className="text-right">
            <a className="btn btn-primary mb-3 mr-1" href="#carouselExampleIndicators2" role="button" data-slide="prev">
              <i className="fa fa-arrow-left"></i>
            </a>
            <a className="btn btn-primary mb-3" href="#carouselExampleIndicators2" role="button" data-slide="next">
              <i className="fa fa-arrow-right"></i>
            </a>
          </Col>
          <Col md={12}>
            <Carousel id="carouselExampleIndicators2" interval={null}>
              { }
              <Carousel.Item>
                <Row>
                  <Col md={12} className="mb-3">
                    <BootstrapCardDeck />
                  </Col>
                </Row>
              </Carousel.Item>
            </Carousel>
          </Col>
        </Row>
      </Container>
    </section>
  );
};

export default CarouselCards;
