import React from 'react';
import { Navbar, Nav, NavDropdown, Form, Container } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faShoppingCart, faSearch } from '@fortawesome/free-solid-svg-icons';
import logo from './logo2.jpg';
import './NavbarStyles.css'; // Importa el archivo CSS aquí

const NavbarExample = () => {
  return (
    <div style={{ backgroundColor: '#FADDE9', paddingTop: '90px', paddingLeft: '20px', paddingRight: '20px' }}>
      <div style={{ textAlign: 'center', backgroundColor: '#FADDE9', position: 'absolute', top: '0', left: '50%', transform: 'translateX(-50%)', zIndex: '2', width: '100%', height: '300px' }}>
        <img
          src={logo}
          height="300"
          className="d-inline-block align-top"
          alt="Logo"
          style={{ backgroundColor: '#FADDE9' }}
        />
      </div>
      <Navbar expand="lg" style={{ backgroundColor: 'transparent', zIndex: '1', marginTop: '200px' }}>
        <Container fluid>
          <Navbar.Brand as={Link} to='/'></Navbar.Brand>
          <Navbar.Toggle aria-controls="navbarScroll" />
          <Navbar.Collapse id="navbarScroll">
            <Nav className="me-auto my-2 my-lg-0 navbar-nav-scroll" style={{ maxHeight: '100px', marginLeft: 'auto', marginRight: 'auto', marginBottom: '10px' }} navbarScroll>
              <Nav.Link as={Link} to='/' className="nav-link active hoverable-icon">
                Home
              </Nav.Link>
              <NavDropdown title="Cremas corporales" id="navbarScrollingDropdown">
                <NavDropdown.Item as={Link} to="#" className="hoverable-icon">Tradicionales</NavDropdown.Item>
                <NavDropdown.Item as={Link} to="#" className="hoverable-icon">Emulsionadas</NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="Exfoliantes" id="navbarScrollingDropdown">
                <NavDropdown.Item as={Link} to="#" className="hoverable-icon">Slime</NavDropdown.Item>
                <NavDropdown.Item as={Link} to="#" className="hoverable-icon">De semillas</NavDropdown.Item>
              </NavDropdown>
              <Nav.Link as={Link} to='/blog' className="nav-link active hoverable-icon">
                Q & A Preguntas frecuentes
              </Nav.Link>
            </Nav>
            <Form className="d-flex" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
              <div className="input-group" style={{ width: '250px' }}>
                <Form.Control type="search" placeholder="Buscar" aria-label="Search" style={{ borderRadius: '20px 0px 0px 20px', borderRight: 'none', backgroundColor: 'rgba(169,169,169,0.3)', color: 'rgba(0,0,0,0.5)' }} />
                <button className="btn btn-outline-success hoverable-icon" type="submit" style={{ borderRadius: '0px 20px 20px 0px', borderLeft: 'none', backgroundColor: '#F18FB8', borderBlockColor: '#F18FB8' }}>
                  <FontAwesomeIcon icon={faSearch} style={{ color: '#FFFFFF' }} />
                </button>
              </div>
            </Form>
            <Nav className="ms-auto" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
              <Nav.Link as={Link} to="#" className="nav-link hoverable-icon" style={{ marginRight: '5px' }}>
                <FontAwesomeIcon icon={faUser} />
                Iniciar sesión
              </Nav.Link>
              <div style={{ borderLeft: '1px solid #000000', height: '20px', marginRight: '5px', marginLeft: '5px', marginTop: '7px' }}></div>
              <Nav.Link as={Link} to="#" className="nav-link hoverable-icon" style={{ marginRight: '5px' }}>
                <FontAwesomeIcon icon={faShoppingCart} />
              </Nav.Link>
            </Nav>

          </Navbar.Collapse>
        </Container>
      </Navbar>
    </div>
  );
};

export default NavbarExample;
