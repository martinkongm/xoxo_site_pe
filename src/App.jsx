// src/App.jsx
import React from 'react';
import './index.css';
import { Outlet, useLocation } from 'react-router-dom';
import Navbar from './components/navbar/Navbar';
import Footer from './components/footer/Footer';
import Home from './routes/Home';
import CarouselCards from './components/carrusel_prod/CarouselCards';

function App() {
  const location = useLocation();
  const isHomePage = location.pathname === "/";
  const isAuthPage = location.pathname === "/login" || location.pathname === "/signup";

  return (
    <div className='App'>
      {!isAuthPage && <Navbar />}
      <main>
        <Outlet />
      </main>
      {isHomePage && <Home />}
      {!isAuthPage && <Footer />}
    </div>
  );
}

export default App;
