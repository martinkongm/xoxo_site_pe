import React from 'react';
import './index.css';
import { Outlet, useLocation } from 'react-router-dom';
import Navbar from './components/navbar/Navbar';
import Footer from './components/footer/Footer';
import { FilterProvider } from './context/FilterContext';
import Home from './routes/Home';
import CarouselCards from './components/carrusel_prod/CarouselCards';

function App() {
  const location = useLocation();
  const isHomePage = location.pathname === "/";
  const isAuthPage = location.pathname === "/login" || location.pathname === "/signup";

  return (
    <FilterProvider>
      <div className='App'>
        {!isAuthPage && <Navbar />}
        <main>
          <Outlet />
        </main>
        {isHomePage && <Home />}
        {!isAuthPage && <Footer />}
        <CarouselCards/>
      </div>
    </FilterProvider>
  );
}

export default App;
