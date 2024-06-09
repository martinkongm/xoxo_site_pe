import React, { useState } from 'react';
import axios from 'axios';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Login = () => {
  const [correoUsuario, setCorreoUsuario] = useState('');
  const [contrasenaUsuario, setContrasenaUsuario] = useState('');
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/auth/log-in', {
        correoUsuario,
        contrasenaUsuario,
      });
      console.log('User logged in successfully', response.data);
      localStorage.setItem('jwt', response.data.jwt); // Guardar el token JWT en el localStorage
      setMessage('User logged in successfully');
    } catch (error) {
      console.error('There was an error logging in the user', error);
      setError('There was an error logging in the user');
    }
  };

  return (
    <div style={{ backgroundColor: '#FADDE9', minHeight: '100vh', paddingTop: '80px' }}>
      <Container fluid>
        <Row>
          <Col md={6} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', paddingRight: '10px' }}>
            <img
              src="/src/components/image/logo2.jpg"
              alt="imagen_grande"
              style={{ maxWidth: '100%', maxHeight: '80%', objectFit: 'cover' }}
            />
          </Col>
          <Col md={6} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', paddingLeft: '70px' }}>
            <div style={{ width: '100%', maxWidth: '500px' }}>
              <Form style={{ width: '100%' }} onSubmit={handleSubmit}>
                <h2 style={{ marginBottom: '20px' }}>Login</h2>
                <p style={{ marginBottom: '20px' }}>¡Bienvenido! Por favor, ingresa a tu cuenta</p>
                
                <Form.Group controlId="formBasicEmail" style={{ marginBottom: '15px' }}>
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    value={correoUsuario}
                    onChange={(e) => setCorreoUsuario(e.target.value)}
                    required
                    style={{ padding: '10px' }}
                  />
                </Form.Group>
                
                <Form.Group controlId="formBasicPassword" style={{ marginBottom: '15px' }}>
                  <Form.Label>Contraseña</Form.Label>
                  <Form.Control
                    type="password"
                    value={contrasenaUsuario}
                    onChange={(e) => setContrasenaUsuario(e.target.value)}
                    required
                    style={{ padding: '10px' }}
                  />
                </Form.Group>
                
                <Form.Group controlId="formBasicCheckbox" style={{ marginTop: '20px' }}>
                  <Form.Check type="checkbox" label="Guardar mis credenciales" />
                </Form.Group>
                
                <Button
                  variant="primary"
                  type="submit"
                  style={{
                    marginTop: '20px',
                    backgroundColor: '#F4056A',
                    border: 'none',
                    padding: '12px 60px',
                    fontSize: '1.2rem',
                  }}
                >
                  Ingresar
                </Button>
                
                <p style={{ marginTop: '20px' }}>
                  ¿Nuevo usuario? <Link to="/signup" style={{ color: '#F4056A' }}>Registrarse</Link>
                </p>
                
                <p>
                  <a href="#" style={{ color: '#F4056A' }}>¿Olvidaste tu contraseña?</a>
                </p>
                
                {error && <p style={{ color: 'red' }}>{error}</p>}
                {message && <p>{message}</p>}
              </Form>
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Login;
