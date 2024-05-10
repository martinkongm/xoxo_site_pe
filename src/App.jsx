import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/navbar/Navbar';
import Footer from './components/footer/Footer'; 
import CarouselCards from './components/carrusel_prod/CarouselCards';
import Blog from './components/navegacion/paginas/Blog';
import ProductoDetalle from './components/detalle_prod/ProductoDetalle';
import CarouselCards2 from './components/carrusel_prod/CarouselCards2';

function App() {
  return (
    <div className='App'>
      <Router>
        <Navbar />
        <Routes>
            <Route path='/blog' element={<Blog />} />
            <Route path='/productodetalle' element={<ProductoDetalle />} />
        </Routes>
        <CarouselCards />
        <CarouselCards2 />
        <Footer /> 
      </Router>
    </div>
  );
}

export default App;
