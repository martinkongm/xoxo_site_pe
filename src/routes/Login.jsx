import React, { useState } from 'react';
import { Container, Row, Col, Form, Button, Modal } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { logIn } from '../services/Auth';
import NavBar from '../components/navbar/Navbar';

const Login = () => {
  const [correoUsuario, setCorreoUsuario] = useState('');
  const [contrasenaUsuario, setContrasenaUsuario] = useState('');
  const [error, setError] = useState('');
  const [showSuccessModal, setShowSuccessModal] = useState(false);
  const navigate = useNavigate(); // Hook para redirigir

  const clearForm = () => {
    setCorreoUsuario('');
    setContrasenaUsuario('');
    setError('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const userData = {
        email: correoUsuario,
        password: contrasenaUsuario,
      };
      const response = await logIn(userData);
      setShowSuccessModal(true); // Mostrar el modal de éxito
      clearForm(); // Limpiar el formulario
      // Guarda el token JWT en el localStorage
      localStorage.setItem('token', response.data.accessToken);
      // Redirige al usuario al componente Account
      navigate('/account');
    } catch (error) {
      console.error('There was an error logging in the user', error);
      setError('Usuario o contraseña inválida.');
    }
  };

  return (
    <>
      <NavBar />
      <div style={{ backgroundColor: '#FADDE9', minHeight: '100vh', paddingTop: '25px' }}>
        <Container fluid>
          <Row>
            <Col md={6} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', paddingLeft: '50px' }}>
              <div style={{ width: '100%', maxWidth: '500px' }}>
                <Form style={{ width: '100%' }} onSubmit={handleSubmit}>
                  <h2 style={{ marginBottom: '20px' }}>Login</h2>
                  <p style={{ marginBottom: '20px' }}>Por favor, ingresa tus credenciales</p>

                  <Form.Group style={{ marginBottom: '15px' }}>
                    <Form.Label htmlFor="correoUsuario">Email</Form.Label>
                    <Form.Control 
                      type="email" 
                      id="correoUsuario" 
                      name="correoUsuario"
                      value={correoUsuario} 
                      onChange={(e) => setCorreoUsuario(e.target.value)} 
                      required 
                      style={{ padding: '10px' }} 
                    />
                  </Form.Group>

                  <Form.Group style={{ marginBottom: '15px' }}>
                    <Form.Label htmlFor="contrasenaUsuario">Contraseña</Form.Label>
                    <Form.Control 
                      type="password" 
                      id="contrasenaUsuario" 
                      name="contrasenaUsuario"
                      value={contrasenaUsuario} 
                      onChange={(e) => setContrasenaUsuario(e.target.value)} 
                      required 
                      style={{ padding: '10px' }} 
                    />
                  </Form.Group>

                  <Button variant="primary" type="submit" style={{ backgroundColor: '#F4056A', border: 'none', padding: '12px 60px', fontSize: '1.2rem' }}>
                    Iniciar sesión
                  </Button>

                  <p style={{ marginTop: '20px' }}>
                    ¿No tienes una cuenta? <Link to="/signup" style={{ color: '#F4056A' }}>Regístrate aquí</Link>
                  </p>
                </Form>
                {error && <p style={{ color: 'red' }}>{error}</p>}
              </div>
            </Col>
            <Col md={6} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', paddingRight: '50px' }}>
              <img
                src="/src/components/image/logo2.jpg"
                alt="imagen_grande"
                style={{ maxWidth: '100%', maxHeight: '80%', objectFit: 'cover' }}
              />
            </Col>
          </Row>
        </Container>

        <Modal show={showSuccessModal} onHide={() => setShowSuccessModal(false)}>
          <Modal.Header closeButton>
            <Modal.Title>Login Exitoso</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>El usuario ha iniciado sesión con éxito.</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="primary" onClick={() => setShowSuccessModal(false)}>
              Cerrar
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    </>
  );
};

export default Login;
