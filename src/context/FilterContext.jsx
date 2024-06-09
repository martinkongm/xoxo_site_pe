import React, { createContext, useState } from 'react';

export const FilterContext = createContext();

export const FilterProvider = ({ children }) => {
    // Establecer la primera categoría como valor inicial del filtro
    const [filter, setFilter] = useState('Body-splash'); 

    return (
        <FilterContext.Provider value={{ filter, setFilter }}>
            {children}
        </FilterContext.Provider>
    );
};
