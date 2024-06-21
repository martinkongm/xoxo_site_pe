import React, { useEffect, useState } from 'react';
import { Table, Button, Container } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import './VerColecciones.css';

const VerColecciones = () => {
  const [colecciones, setColecciones] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/colecciones')
      .then(response => {
        setColecciones(response.data.object);
      })
      .catch(error => {
        console.error('Error fetching colecciones:', error);
      });
  }, []);

  const handleEliminarColeccion = (idColeccion) => {
    axios.delete(`http://localhost:8080/api/v1/coleccion/${idColeccion}`)
      .then(() => {
        setColecciones(colecciones.filter(coleccion => coleccion.idColeccion !== idColeccion));
        alert('Colección eliminada');
      })
      .catch(error => {
        console.error('Error eliminando la colección:', error);
      });
  };

  return (
    <Container>
      <h1>Colecciones</h1>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {colecciones.map(coleccion => (
            <tr key={coleccion.idColeccion}>
              <td>{coleccion.nombreColeccion}</td>
              <td>
                <Button variant="primary" onClick={() => navigate(`/colecciones/${coleccion.idColeccion}`)}>Ver</Button>
                <Button variant="warning" className="btn-mod ml-2" onClick={() => navigate(`/colecciones/${coleccion.idColeccion}/modificar`)}>Modificar</Button>
                <Button variant="danger" className="btn-del ml-2" onClick={() => handleEliminarColeccion(coleccion.idColeccion)}>Eliminar</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default VerColecciones;
