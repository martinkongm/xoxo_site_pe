import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080', // Cambia esto a tu base URL
});

export default instance;
