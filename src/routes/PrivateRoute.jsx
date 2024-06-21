// PrivateRoute.jsx (ejemplo)
import React from 'react';
import { Route, Navigate } from 'react-router-dom';
import { useAuth } from '../Auth/AuthProvider';

const PrivateRoute = ({ element, ...rest }) => {
  const { accessToken } = useAuth();

  return (
    <Route {...rest} element={accessToken ? element : <Navigate to="/login" />} />
  );
};

export default PrivateRoute;
