export const filterProducto = (data, cat) => {
    if (cat === 'All') {
        return data;
    }
    return data.filter((x) => x.nombreColeccion === cat);
};
