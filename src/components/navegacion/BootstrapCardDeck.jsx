import React from 'react';
import { Container, Row, Col, Card, Carousel, Button } from 'react-bootstrap';
import { FaStar, FaRegStar } from 'react-icons/fa'; // Importamos los íconos de estrellas
import { Link } from 'react-router-dom'; // Importamos Link desde react-router-dom

const cardsData = [
  {
    carouselId: "carouselExampleIndicators1",
    images: [
      "/src/components/navegacion/TAMANHOS_22_3d6685f9-8106-4eb2-be30-2d4004fb7762.jpg",
      "https://picsum.photos/200/300?random=2",
      "https://picsum.photos/200/300?random=3"
    ],
    title: "ALGODÃO DOCE | Esfoliante Slime | Esfoliante Corporal de Açúcar",
    text: "S/. 10.00",
    rating: 4,
  },
  {
    carouselId: "carouselExampleIndicators2",
    images: [
      "/src/components/navegacion/TAMANHOS_22_3d6685f9-8106-4eb2-be30-2d4004fb7762.jpg",
      "https://picsum.photos/200/300?random=5",
      "https://picsum.photos/200/300?random=6"
    ],
    title: "ALGODÃO DOCE | Esfoliante Slime | Esfoliante Corporal de Açúcar",
    text: "S/. 10.00",
    rating: 3,
  },
  {
    carouselId: "carouselExampleIndicators3",
    images: [
      "/src/components/navegacion/TAMANHOS_22_3d6685f9-8106-4eb2-be30-2d4004fb7762.jpg",
      "https://picsum.photos/200/300?random=8",
      "https://picsum.photos/200/300?random=9"
    ],
    title: "ALGODÃO DOCE | Esfoliante Slime | Esfoliante Corporal de Açúcar",
    text: "S/. 10.00",
    rating: 5,
  },
  {
    carouselId: "carouselExampleIndicators4",
    images: [
      "/src/components/navegacion/TAMANHOS_22_3d6685f9-8106-4eb2-be30-2d4004fb7762.jpg",
      "https://picsum.photos/200/300?random=11",
      "https://picsum.photos/200/300?random=12"
    ],
    title: "ALGODÃO DOCE | Esfoliante Slime | Esfoliante Corporal de Açúcar",
    text: "S/. 10.00",
    rating: 4,
  }
];

const BootstrapCardDeck = () => {
  return (
    <Container>
      <Row>
        {cardsData.map((card, index) => (
          <Col key={index} lg={3} md={6} className="mb-4">
            <Card style={{ width: '18rem', height: '25rem' }}>
              <Carousel id={card.carouselId} interval={null}>
                {card.images.map((image, idx) => (
                  <Carousel.Item key={idx}>
                    <img className="d-block w-100" src={image} alt={`Slide ${idx}`} style={{ maxHeight: '200px' }} />
                  </Carousel.Item>
                ))}
              </Carousel>
              <Card.Body>
                <Card.Title>{card.title}</Card.Title>
                <Card.Text>{card.text}</Card.Text>
                {/* Estrellitas de calificación */}
                <div>
                  {[...Array(card.rating)].map((_, i) => (
                    <FaStar key={i} />
                  ))}
                  {[...Array(5 - card.rating)].map((_, i) => (
                    <FaRegStar key={i} />
                  ))}
                </div>
                <Link to='/detalle_producto'>
                  <Button variant="primary" style={{ width: '100%', marginTop: '10px', marginBottom: '10px' }}>Ver detalles</Button>
                </Link>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default BootstrapCardDeck;
