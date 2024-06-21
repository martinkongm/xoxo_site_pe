import React, { useState, useEffect } from 'react';
import { Navbar, Nav, NavDropdown, Form, Container } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faUserPlus, faShoppingCart, faSearch, faUserCircle } from '@fortawesome/free-solid-svg-icons';
import './NavbarStyles.css';
import logo from '../image/logo2.jpg';
import { useSelector } from 'react-redux';

export default function NavBar({ children }) {
    const cart = useSelector((state) => state.cart.cart || []);
    const itemCount = cart.reduce((total, product) => total + product.qty, 0);

    const [searchTerm, setSearchTerm] = useState('');
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            setIsAuthenticated(true);
        }
    }, []);

    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        navigate(`/search?query=${searchTerm}`);
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        setIsAuthenticated(false);
        navigate('/login');
    };

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
                        <Nav className="me-auto my-2 my-lg-0 navbar-nav-scroll" style={{ maxHeight: '100px', marginLeft: '10px', marginRight: 'auto', marginBottom: '10px' }} navbarScroll>
                            <Nav.Link as={Link} to='/' className="nav-link active hoverable-icon">Home</Nav.Link>
                            <NavDropdown title="Cremas corporales" id="navbarScrollingDropdown">
                                <NavDropdown.Item as={Link} to="#">Manteca Corporal</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="#">Manteca Emulsionada</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item as={Link} to="#">Tradicionales</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="#">Emulsionadas</NavDropdown.Item>
                            </NavDropdown>
                            <NavDropdown title="Exfoliantes" id="navbarScrollingDropdown">
                                <NavDropdown.Item as={Link} to="#">Body Splash</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="#">Exfoliante Corporal</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item as={Link} to="#">Slime</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="#">De semillas</NavDropdown.Item>
                            </NavDropdown>
                            <Nav.Link as={Link} to='/blog' className="nav-link active hoverable-icon">Q & A Preguntas frecuentes</Nav.Link>
                        </Nav>
                        <Form onSubmit={handleSearchSubmit} className="d-flex align-items-center">
                            <div className="input-group">
                                <Form.Control
                                    type="text"
                                    placeholder="Buscar"
                                    aria-label="Search"
                                    value={searchTerm}
                                    onChange={handleSearchChange}
                                    style={{
                                        borderRadius: '20px 0 0 20px',
                                        backgroundColor: 'rgba(169,169,169,0.3)',
                                        color: 'rgba(0,0,0,0.5)',
                                        flex: '1 1 auto',
                                        minWidth: '110px', // Ajusta el ancho mínimo del input
                                        marginRight: '0' // Elimina el espacio entre el input y el botón
                                    }}
                                />
                                <button
                                    className="btn btn-outline-success hoverable-icon"
                                    type="submit"
                                    style={{
                                        borderRadius: '0 20px 20px 0',
                                        backgroundColor: '#F18FB8',
                                        borderColor: '#F18FB8',
                                        paddingLeft: '10px',
                                        paddingRight: '10px', // Reducir el padding horizontal
                                        marginLeft: '-5px' // Ajusta el espacio entre el input y el botón
                                    }}
                                >
                                    <FontAwesomeIcon icon={faSearch} style={{ fontSize: '18px', color: '#FFFFFF' }} />
                                </button>
                            </div>
                        </Form>

                        <Nav className="ms-auto">
                            {!isAuthenticated && (
                                <>
                                    <Nav.Link as={Link} to="/signup" className="nav-link hoverable-icon" style={{ marginRight: '5px' }}>
                                        <FontAwesomeIcon icon={faUserPlus} />
                                        Registrarse
                                    </Nav.Link>
                                    <Nav.Link as={Link} to="/login" className="nav-link hoverable-icon" style={{ marginRight: '5px' }}>
                                        <FontAwesomeIcon icon={faUser} />
                                        Iniciar sesión
                                    </Nav.Link>
                                </>
                            )}
                            {isAuthenticated && (
                                <>
                                    <Nav.Link as={Link} to="/account" className="nav-link hoverable-icon" style={{ marginRight: '5px' }}>
                                        <FontAwesomeIcon icon={faUserCircle} />
                                        Mi cuenta
                                    </Nav.Link>
                                    <Nav.Link className="nav-link hoverable-icon" style={{ marginRight: '5px' }} onClick={handleLogout}>
                                        Cerrar sesión
                                    </Nav.Link>
                                </>
                            )}
                            <div style={{ borderLeft: '1px solid #000000', height: '20px', marginRight: '5px', marginLeft: '5px', marginTop: '7px' }}></div>
                            <Nav.Link as={Link} to='/cart' className="nav-link hoverable-icon">
                                <FontAwesomeIcon icon={faShoppingCart} />
                                {itemCount > 0 && <span className="badge badge-pill badge-danger">{itemCount}</span>}
                                {cart.length > 0 && <span>{cart.length}</span>}
                            </Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
            <main>{children}</main>
        </div>
    );
}
