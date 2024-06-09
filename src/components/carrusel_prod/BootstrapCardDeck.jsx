import React from 'react';
import { useDispatch } from 'react-redux';
import { Container, Row, Col, Card, Carousel, Button } from 'react-bootstrap';
import { FaStar, FaRegStar, FaChevronLeft, FaChevronRight } from 'react-icons/fa'; 
import { Link } from 'react-router-dom'; 

const cardsData = [
  {
    carouselId: "carouselExampleIndicators1",
    images: [
      "/src/components/carrusel_prod/TAMANHOS_22_3d6685f9-8106-4eb2-be30-2d4004fb7762.jpg",
      "https://picsum.photos/200/300?random=2",
      "https://picsum.photos/200/300?random=3"
    ],
    title: "ALGODÃO DOCE | Esfoliante Slime | Esfoliante Corporal de Açúcar",
    text: "S/. 10.00",
    rating: 4,
  },
  {
    carouselId: "carouselExampleIndicators1",
    images: [
      "/src/components/carrusel_prod/TAMANHOS_22_3d6685f9-8106-4eb2-be30-2d4004fb7762.jpg",
      "https://picsum.photos/200/300?random=2",
      "https://picsum.photos/200/300?random=3"
    ],
    title: "ALGODÃO DOCE | Esfoliante Slime | Esfoliante Corporal de Açúcar",
    text: "S/. 10.00",
    rating: 4,
  },
  {
    carouselId: "carouselExampleIndicators1",
    images: [
      "/src/components/carrusel_prod/TAMANHOS_22_3d6685f9-8106-4eb2-be30-2d4004fb7762.jpg",
      "https://picsum.photos/200/300?random=2",
      "https://picsum.photos/200/300?random=3"
    ],
    title: "ALGODÃO DOCE | Esfoliante Slime | Esfoliante Corporal de Açúcar",
    text: "S/. 10.00",
    rating: 4,
  },
  {
    carouselId: "carouselExampleIndicators1",
    images: [
      "/src/components/carrusel_prod/TAMANHOS_22_3d6685f9-8106-4eb2-be30-2d4004fb7762.jpg",
      "https://picsum.photos/200/300?random=2",
      "https://picsum.photos/200/300?random=3"
    ],
    title: "ALGODÃO DOCE | Esfoliante Slime | Esfoliante Corporal de Açúcar",
    text: "S/. 10.00",
    rating: 4,
  },
  {
    carouselId: "carouselExampleIndicators1",
    images: [
      "/src/components/carrusel_prod/TAMANHOS_22_3d6685f9-8106-4eb2-be30-2d4004fb7762.jpg",
      "https://picsum.photos/200/300?random=2",
      "https://picsum.photos/200/300?random=3"
    ],
    title: "ALGODÃO DOCE | Esfoliante Slime | Esfoliante Corporal de Açúcar",
    text: "S/. 10.00",
    rating: 4,
  },

];

const BootstrapCardDeck = ({ carouselId }) => {
  // Función para repetir el arreglo de tarjetas de forma infinita
  const getInfiniteCardsData = () => {
    const result = [];
    while (result.length < 100) {
      result.push(...cardsData);
    }
    return result;
  };

  // Obtener las tarjetas de manera infinita
  const infiniteCardsData = getInfiniteCardsData();

  // Función para dividir el arreglo en grupos de 4 tarjetas
  const chunkArray = (arr, size) => {
    return Array.from({ length: Math.ceil(arr.length / size) }, (_, index) => {
      return arr.slice(index * size, index * size + size);
    });
  };

  // Dividir las tarjetas en grupos de 4
  const cardRows = chunkArray(infiniteCardsData, 4);

  return (
    <Carousel id={carouselId} interval={null} style={{ maxWidth: '1000px', margin: '0 auto' }}>
      {cardRows.map((row, rowIndex) => (
        <Carousel.Item key={rowIndex}>
          <Container>
            <Row>
              {row.map((card, cardIndex) => (
                <Col lg={3} md={6} key={rowIndex * 4 + cardIndex} className="mb-4">
                  <Card style={{ width: '100%', height: '30rem', margin: '0 auto' }}>
                    <Carousel id={carouselId} interval={null}>
                      {card.images.map((image, idx) => (
                        <Carousel.Item key={idx}>
                          <img className="d-block w-100" src={image} alt={`Slide ${idx}`} style={{ maxHeight: '200px' }} />
                        </Carousel.Item>
                      ))}
                    </Carousel>
                    <Card.Body>
                      <Card.Title>{card.title}</Card.Title>
                      <Card.Text style={{ textAlign: 'center' }}>{card.text}</Card.Text>
                      <div style={{ display: 'flex', justifyContent: 'center' }}>
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
        </Carousel.Item>
      ))}
      {/* Flecha izquierda */}
      <a className="carousel-control-prev" href={`#${carouselId}`} role="button" data-slide="prev" style={{ backgroundColor: '#e1e1e1', width: '6vh', height: '6vh', borderRadius: '50%', top: '50%', transform: 'translateY(-50%)', padding: '1em' }}>
        <FaChevronLeft style={{ fontSize: '1.5em' }} />
      </a>
      {/* Flecha derecha */}
      <a className="carousel-control-next" href={`#${carouselId}`} role="button" data-slide="next" style={{ backgroundColor: '#e1e1e1', width: '6vh', height: '6vh', borderRadius: '50%', top: '50%', transform: 'translateY(-50%)', padding: '1em' }}>
        <FaChevronRight style={{ fontSize: '1.5em' }} />
      </a>
    </Carousel>
  );
};

export default BootstrapCardDeck;
