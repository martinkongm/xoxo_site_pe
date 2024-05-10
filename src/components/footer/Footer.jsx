import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import { FaEnvelope, FaWhatsapp, FaFacebook, FaInstagram, FaTiktok, FaYoutube, FaRegCopyright } from 'react-icons/fa';

const Footer = () => {
  return (
    <div style={{ backgroundColor: '#FADAE7', padding: '50px 0', color: '#000000' }}>
      <Container>
        <Row>
          <Col md={3}>
            <h5 style={{ fontWeight: 'bold' }}>XOXO</h5>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor, magna a vestibulum interdum.</p>
            <a href="jhennifer.guevara@tecsup.edu.pe" style={{ color: '#000000' }}>
              <FaEnvelope style={{ marginRight: '5px' }} /> xoxo@tecsup.edu.pe
            </a>
            <br />
            <a href="https://wa.me/5193123438" style={{ color: '#000000', marginTop: '20px' }}>
              <FaWhatsapp style={{ marginRight: '5px' }} /> +51 931 123 438
            </a>
            <Row style={{ marginTop: '20px' }}>
              <Col>
                <a href="#" style={{ color: '#000000', marginRight: '20px' }}><FaFacebook /></a>
                <a href="#" style={{ color: '#000000', marginRight: '20px' }}><FaInstagram /></a>
                <a href="#" style={{ color: '#000000', marginRight: '20px' }}><FaTiktok /></a>
                <a href="#" style={{ color: '#000000' }}><FaYoutube /></a>
              </Col>
            </Row>
          </Col>
          <Col md={3}>
            <h5 style={{ fontWeight: 'bold' }}>Nuestras Políticas y Más</h5>
            <ul style={{ listStyleType: 'none', padding: 0 }}>
              <li style={{ marginTop: '10px' }}><a href="#" style={{ color: '#000000' }}>NUESTRA HISTORIA</a></li>
              <li style={{ marginTop: '10px' }}><a href="#" style={{ color: '#000000' }}>POLÍTICA DE REEMBOLSO</a></li>
              <li style={{ marginTop: '10px' }}><a href="#" style={{ color: '#000000' }}>POLÍTICA DE PRIVACIDAD</a></li>
              <li style={{ marginTop: '10px' }}><a href="#" style={{ color: '#000000' }}>TÉRMINOS DE USO</a></li>
              <li style={{ marginTop: '10px' }}><a href="#" style={{ color: '#000000' }}>POLÍTICA DE ENVÍO</a></li>
            </ul>
          </Col>
          <Col md={3} style={{ paddingTop: '20px' }}>
            <ul style={{ listStyleType: 'none', padding: 0 }}>
              <li style={{ marginTop: '10px' }}><a href="#" style={{ color: '#000000' }}>PREGUNTAS FRECUENTES</a></li>
              <li style={{ marginTop: '10px' }}><a href="#" style={{ color: '#000000' }}>EXENCIONES DE RESPONSABILIDADES DEL PRODUCTO</a></li>
              <li style={{ marginTop: '10px' }}><a href="#" style={{ color: '#000000' }}>TÉRMINOS DE SERVICIO</a></li>
              <li style={{ marginTop: '10px' }}><a href="#" style={{ color: '#000000' }}>POLÍTICA DE REEMBOLSO</a></li>
            </ul>
          </Col>
          <Col md={3}>
            <Row>
              <Col>
                <img src="/src/components/footer/logo2.jpg" alt="Logo" style={{ width: '150px', marginTop: '8px' }} />
                <Row>
                  <Col style={{ marginTop: '55px' }}>
                    <img src="icon1.png" alt="Icon" style={{ width: '30px', marginRight: '5px' }} />
                    <img src="icon2.png" alt="Icon" style={{ width: '30px', marginRight: '5px' }} />
                    <img src="icon3.png" alt="Icon" style={{ width: '30px', marginRight: '5px' }} />
                    <img src="icon4.png" alt="Icon" style={{ width: '30px' }} />
                  </Col>
                </Row>
              </Col>
            </Row>
          </Col>
        </Row>
        <Row>
          <Col style={{ textAlign: 'right', marginTop: '20px' }}>
            <p style={{ color: '#000000' }}><FaRegCopyright /> 2024, XOXO Skincare</p>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Footer;
