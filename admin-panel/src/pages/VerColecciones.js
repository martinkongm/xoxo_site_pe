import React, { useEffect, useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const VerColecciones = () => {
  const [colecciones, setColecciones] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/colecciones')
      .then(response => {
        if (response.data.object && Array.isArray(response.data.object)) {
          setColecciones(response.data.object);
        } else {
          setColecciones([]);
        }
      })
      .catch(error => {
        console.error('Error fetching colecciones:', error);
        setColecciones([]);
      });
  }, []);

  const eliminarColeccion = (id) => {
    axios.delete(`http://localhost:8080/api/v1/coleccion/${id}`)
      .then(() => {
        alert('Colección eliminada');
        setColecciones(colecciones.filter(coleccion => coleccion.idColeccion !== id));
      })
      .catch(error => {
        console.error('Error deleting coleccion:', error);
      });
  };

  return (
    <div>
      <h1>Colecciones</h1>
      <Button onClick={() => navigate('/colecciones/nueva')}>Añadir Colección</Button>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Productos</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {colecciones.length > 0 ? (
            colecciones.map(coleccion => (
              <tr key={coleccion.idColeccion}>
              
                <td>{coleccion.nombreColeccion}</td>
                <td>{coleccion.productosColeccion.join(', ')}</td>
                <td>
                  <Link to={`/colecciones/${coleccion.idColeccion}`}>Ver</Link>{' '}
                  <Link to={`/colecciones/${coleccion.idColeccion}/modificar`}>Modificar</Link>{' '}
                  <Button variant="danger" onClick={() => eliminarColeccion(coleccion.idColeccion)}>Eliminar</Button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">No hay colecciones disponibles</td>
            </tr>
          )}
        </tbody>
      </Table>
    </div>
  );
};

export default VerColecciones;
