// components/SearchResults.js
import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { useSelector } from 'react-redux';
import Product from './product/Product';

const SearchResults = () => {
  const [results, setResults] = useState([]);
  const location = useLocation();
  const products = useSelector((state) => state.products);

  useEffect(() => {
    const query = new URLSearchParams(location.search).get('query');
    if (query) {
      const filteredResults = products.filter((product) =>
        product.nombreProducto.toLowerCase().includes(query.toLowerCase())
      );
      setResults(filteredResults);
    }
  }, [location.search, products]);

  return (
    <div>
      {results.length > 0 ? (
        results.map((product) => (
          <Product key={product.idProducto} product={product} />
        ))
      ) : (
        <h2>No se encontraron productos</h2>
      )}
    </div>
  );
};

export default SearchResults;
