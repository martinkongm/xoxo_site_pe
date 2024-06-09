import React, { useState } from 'react';
import axios from 'axios';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const SignUp = () => {
  const [nombreUsuario, setNombreUsuario] = useState('');
  const [apellidoUsuario, setApellidoUsuario] = useState('');
  const [correoUsuario, setCorreoUsuario] = useState('');
  const [contrasenaUsuario, setContrasenaUsuario] = useState('');
  const [confirmContrasena, setConfirmContrasena] = useState('');
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [passwordConfirmError, setPasswordConfirmError] = useState('');

  const validatePassword = (password) => {
    const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordRegex.test(password);
  };

  const handlePasswordChange = (e) => {
    const { value } = e.target;
    setContrasenaUsuario(value);
    if (!validatePassword(value)) {
      setPasswordError('La contraseña debe tener al menos 8 caracteres, una letra mayúscula, un número y un carácter especial.');
    } else {
      setPasswordError('');
    }
  };

  const handleConfirmPasswordChange = (e) => {
    const { value } = e.target;
    setConfirmContrasena(value);
    if (value !== contrasenaUsuario) {
      setPasswordConfirmError('Las contraseñas no coinciden.');
    } else {
      setPasswordConfirmError('');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validatePassword(contrasenaUsuario)) {
      setError('La contraseña debe tener al menos 8 caracteres, una letra mayúscula, un número y un carácter especial.');
      return;
    }
    if (contrasenaUsuario !== confirmContrasena) {
      setError('Las contraseñas no coinciden.');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/auth/sign-up', {
        nombreUsuario,
        apellidoUsuario,
        correoUsuario,
        contrasenaUsuario,
      });
      console.log('User registered successfully', response.data);
      setMessage('User registered successfully');
    } catch (error) {
      console.error('There was an error registering the user', error);
      setError('There was an error registering the user');
    }
  };

  return (
    <div style={{ backgroundColor: '#FADDE9', minHeight: '100vh', paddingTop: '25px' }}>
      <Container fluid>
        <Row>
          <Col md={6} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', paddingLeft: '50px' }}>
            <div style={{ width: '100%', maxWidth: '500px' }}>
              <Form style={{ width: '100%' }} onSubmit={handleSubmit}>
                <h2 style={{ marginBottom: '20px' }}>Signup</h2>
                <p style={{ marginBottom: '20px' }}>¡Bienvenido! Por favor, crea tu cuenta</p>

                <Form.Group style={{ marginBottom: '15px' }}>
                  <Form.Label htmlFor="nombreUsuario">Nombre</Form.Label>
                  <Form.Control 
                    type="text" 
                    id="nombreUsuario" 
                    name="nombreUsuario"
                    value={nombreUsuario} 
                    onChange={(e) => setNombreUsuario(e.target.value)} 
                    required 
                    style={{ padding: '10px' }} 
                  />
                </Form.Group>

                <Form.Group style={{ marginBottom: '15px' }}>
                  <Form.Label htmlFor="apellidoUsuario">Apellido</Form.Label>
                  <Form.Control 
                    type="text" 
                    id="apellidoUsuario" 
                    name="apellidoUsuario"
                    value={apellidoUsuario} 
                    onChange={(e) => setApellidoUsuario(e.target.value)} 
                    required 
                    style={{ padding: '10px' }} 
                  />
                </Form.Group>

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
                    onChange={handlePasswordChange} 
                    required 
                    style={{ padding: '10px' }} 
                  />
                  {passwordError && <p style={{ color: 'red' }}>{passwordError}</p>}
                </Form.Group>

                <Form.Group style={{ marginBottom: '20px' }}>
                  <Form.Label htmlFor="confirmContrasena">Confirmar Contraseña</Form.Label>
                  <Form.Control 
                    type="password" 
                    id="confirmContrasena" 
                    name="confirmContrasena"
                    value={confirmContrasena} 
                    onChange={handleConfirmPasswordChange} 
                    required 
                    style={{ padding: '10px' }} 
                  />
                  {passwordConfirmError && <p style={{ color: 'red' }}>{passwordConfirmError}</p>}
                </Form.Group>

                <Button variant="primary" type="submit" style={{ backgroundColor: '#F4056A', border: 'none', padding: '12px 60px', fontSize: '1.2rem' }}>
                  Crear
                </Button>

                <p style={{ marginTop: '20px' }}>
                  ¿Ya tienes una cuenta? <Link to="/login" style={{ color: '#F4056A' }}>Ingresa aquí</Link>
                </p>
              </Form>
              {error && <p style={{ color: 'red' }}>{error}</p>}
              {message && <p>{message}</p>}
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
    </div>
  );
};

export default SignUp;
