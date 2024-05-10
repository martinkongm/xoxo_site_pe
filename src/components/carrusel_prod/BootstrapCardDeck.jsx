import React from 'react';
import { Button } from 'react-bootstrap';
import { FaChevronLeft, FaChevronRight } from 'react-icons/fa';

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

const CarouselExampleControls = () => {
  const cardWidth = 100 / Math.min(cardsData.length, 4); // Calcula el ancho de las tarjetas

  return (
    <div id="carouselExampleControls" className="carousel">
      <div className="carousel-inner">
        {cardsData.map((card, index) => (
          <div className={`carousel-item ${index === 0 ? 'active' : ''}`} key={index}>
            <div className="card" style={{ width: `${cardWidth}%`, margin: '0 0.5em', boxShadow: '2px 6px 8px 0 rgba(22, 22, 26, 0.18)', border: 'none' }}>
              <div className="img-wrapper">
                <img src={card.image} className="d-block w-100" alt={`Slide ${index}`} />
              </div>
              <div className="card-body">
                <h5 className="card-title">{card.title}</h5>
                <p className="card-text">{card.text}</p>
                <Button variant="primary">Go somewhere</Button>
              </div>
            </div>
          </div>
        ))}
      </div>
      <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
        <span className="carousel-control-prev-icon" aria-hidden="true"><FaChevronLeft /></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
        <span className="carousel-control-next-icon" aria-hidden="true"><FaChevronRight /></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
};

export default CarouselExampleControls;
