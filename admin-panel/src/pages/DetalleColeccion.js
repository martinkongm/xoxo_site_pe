import React, { useEffect, useState } from 'react';
import { Table, Button, Container } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const DetalleColeccion = () => {
  const { id } = useParams();
  const [coleccion, setColeccion] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/v1/coleccion/${id}`)
      .then(response => {
        if (response.data.object) {
          setColeccion(response.data.object);
        } else {
          setColeccion(null);
        }
      })
      .catch(error => {
        console.error('Error fetching coleccion:', error);
        setColeccion(null);
      });
  }, [id]);

  if (!coleccion) {
    return <div>Cargando...</div>;
  }

  return (
    <Container>
      <h1>Detalle de la Colección</h1>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Productos</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{coleccion.idColeccion}</td>
            <td>{coleccion.nombreColeccion}</td>
            <td>{coleccion.productosColeccion.join(', ')}</td>
          </tr>
        </tbody>
      </Table>
      <Button onClick={() => navigate(`/colecciones/${id}/modificar`)}>Modificar</Button>
      <Button variant="secondary" onClick={() => navigate('/colecciones')} className="ml-2">Volver</Button>
    </Container>
  );
};

export default DetalleColeccion;
