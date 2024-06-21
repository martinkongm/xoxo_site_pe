// services/Auth.js

import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

export const signUp = async (userData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/auth/sign-up`, userData);
    return response.data;
  } catch (error) {
    throw error.response.data;
  }
};

export const logIn = async (userData) => {
  const response = await axios.post(`${API_BASE_URL}/auth/log-in`, userData);
  const { token, userId } = response.data; // Suponiendo que la respuesta contiene un token y un userId
  localStorage.setItem('token', token);
  localStorage.setItem('userId', userId);
  return response.data;
};

const getAuthHeaders = () => {
  const token = localStorage.getItem('token');
  return token ? { Authorization: `Bearer ${token}` } : {};
};

export const getUserData = async () => {
  const userId = localStorage.getItem('userId');
  if (!userId) {
    throw new Error('User ID not found');
  }

  try {
    const response = await axios.get(`${API_BASE_URL}/usuario/${userId}`, { headers: getAuthHeaders() });
    return response.data.object; 
  } catch (error) {
    throw error;
  }
};

export const logOut = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('userId');
  // Opcionalmente, puedes eliminar otros datos del usuario del localStorage si es necesario
};
