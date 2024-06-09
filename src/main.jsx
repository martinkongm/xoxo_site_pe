import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import './index.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Login from './routes/Login.jsx';
import ProtectedRoute from './routes/ProtectedRoute.jsx';
import Descuento from './routes/Descuento.jsx';
import { AuthProvider } from './Auth/AuthProvider.tsx';
import Product from './components/product/Product.jsx';
import { Provider } from 'react-redux';
import store from './redux/store.js';
import Singnup from './routes/SignUp.jsx';
import Products from './components/product/Products.jsx';
import SignUp from './routes/SignUp.jsx';
import Cart from './components/Cart/Cart.jsx';
import SearchResults from './components/SearchResults.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "descuento",
        element: <ProtectedRoute />,
        children: [
          {
            path: "",
            element: <Descuento />,
          },
        ],
      },
      {
        path: "products/:idProducto",
        element: <Product />,
      },
      {
        path: "products",
        element: <Products />, // Add this route to show all products
      },
      {
        path: "/cart",
        element: <Cart />,
      },
    ],
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/signup",
    element: <SignUp />,
  },
  {
    path: "/search",
    element: <SearchResults />,
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Provider store={store}>
      <AuthProvider>
        <RouterProvider router={router} />
      </AuthProvider>
    </Provider>
  </React.StrictMode>,
);
