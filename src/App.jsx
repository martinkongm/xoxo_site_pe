import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/navegacion/Navbar';
import Footer from './components/navegacion/Footer'; 
import CarouselCards from './components/navegacion/CarouselCards';
import Detalle_producto from './components/navegacion/paginas/Detalle_producto';
import Blog from './components/navegacion/paginas/Blog';
import Inicio from './components/navegacion/paginas/Inicio';
function App() {
  return (
    <div className='App'>
      <Router>
        <Navbar />
        <Routes>
          <Route path='/' element={<Inicio />} />
          <Route path='/blog' element={<Blog />} />
        </Routes>
        <CarouselCards />
        <Routes>
          <Route path='/detalle_producto' element={<Detalle_producto />} />
        </Routes>
        <Footer /> 
      </Router>
    </div>
  );
}

export default App;
