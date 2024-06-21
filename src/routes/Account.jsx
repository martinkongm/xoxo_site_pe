// Account.jsx

import React, { useEffect, useState } from 'react';
import { getUserData, logOut } from '../services/Auth';
import { useNavigate } from 'react-router-dom';
import { Navbar } from 'react-bootstrap';
import NavBar from '../components/navbar/Navbar';

const Account = () => {
  const [userData, setUserData] = useState(null);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userDataResponse = await getUserData();
        setUserData(userDataResponse);
      } catch (error) {
        console.error('Error fetching authenticated data', error);
        setError('Error fetching authenticated data');
      }
    };

    fetchData();
  }, []);

  const handleLogout = () => {
    logOut();
    navigate('/login'); // Redirige al usuario a la página de inicio de sesión
  };

  return (
    <>
    <NavBar />
    <div>
      <h1>Protected Data</h1>
      {error && <p>{error}</p>}
      {userData ? (
        <div>
          <p>Email: {userData.correoUsuario}</p>
          <p>Nombre: {userData.nombreUsuario}</p>
          <p>Fecha de Registro: {userData.fechaRegistro}</p>
          {/* Mostrar otros datos del usuario si es necesario */}
          <button onClick={handleLogout}>Logout</button>
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
    </>
    
  );
};

export default Account;
