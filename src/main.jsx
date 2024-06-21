import React from 'react';
import ReactDOM from 'react-dom';
import App from './App.jsx';
import './index.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Login from './routes/Login.jsx';
import Product from './components/product/Product.jsx';
import { AuthProvider } from './Auth/AuthProvider.tsx';
import { Provider } from 'react-redux';
import store from './redux/store.js';
import { createRoot } from 'react-dom/client'; 
import SignUp from './routes/SignUp.jsx';
import Products from './components/product/Products.jsx';
import Cart from './components/Cart/Cart.jsx';
import SearchResults from './components/SearchResults.jsx';
import Account from './routes/Account';
import Checkout from './components/checkout/Checkout.jsx';

// Configuración del enrutador con createBrowserRouter
const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: 'products/:idProducto',
        element: <Product />,
      },
      {
        path: 'products',
        element: <Products />,
      },
      {
        path: 'cart',
        element: <Cart />,
      },
    ],
  },
  {
    path: '/login',
    element: <Login />,
  
  },
  {
    path: '/account',
    element: <Account />, // Agrega la ruta del componente Account
  },
  {
    path: '/signup',
    element: <SignUp />,
  },
  {
    path: '/search',
    element: <SearchResults />,
  },
  {
    path: '/checkout',
    element: <Checkout />,
  },


]);

// Renderizar la aplicación
// Utiliza createRoot en lugar de ReactDOM.render
createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Provider store={store}>
      <RouterProvider router={router}>
        <AuthProvider>
          <App />
        </AuthProvider>
      </RouterProvider>
    </Provider>
  </React.StrictMode>
);
