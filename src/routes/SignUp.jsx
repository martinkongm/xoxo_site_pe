import React, { useState } from 'react';
import { Container, Row, Col, Form, Button, Modal } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { signUp } from '../services/Auth';
import NavBar from '../components/navbar/Navbar';

const SignUp = () => {
  const [nombreUsuario, setNombreUsuario] = useState('');
  const [apellidoUsuario, setApellidoUsuario] = useState('');
  const [correoUsuario, setCorreoUsuario] = useState('');
  const [contrasenaUsuario, setContrasenaUsuario] = useState('');
  const [confirmContrasena, setConfirmContrasena] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [passwordConfirmError, setPasswordConfirmError] = useState('');
  const [error, setError] = useState('');
  const [showSuccessModal, setShowSuccessModal] = useState(false);

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

  const clearForm = () => {
    setNombreUsuario('');
    setApellidoUsuario('');
    setCorreoUsuario('');
    setContrasenaUsuario('');
    setConfirmContrasena('');
    setPasswordError('');
    setPasswordConfirmError('');
    setError('');
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
      const userData = {
        nombre: nombreUsuario,
        apellido: apellidoUsuario,
        correo: correoUsuario,
        password: contrasenaUsuario,
        roleRequest: { roleListName: ["USER"] }
      };
      const response = await signUp(userData);
      setShowSuccessModal(true); // Mostrar el modal de éxito
      clearForm(); // Limpiar el formulario
    } catch (error) {
      console.error('There was an error registering the user', error);
      setError('There was an error registering the user');
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
            <Modal.Title>Registro Exitoso</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>El usuario ha sido registrado con éxito.</p>
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

export default SignUp;
