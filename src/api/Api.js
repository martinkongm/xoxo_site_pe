export const fetchProducts = async () => {
    try {
        const response = await fetch("http://localhost:8080/api/v1/productos");
        const jsonResponse = await response.json();
        return jsonResponse.object;
    } catch (error) {
        console.error("Error fetching products:", error);
        return [];
    }
};

export const fetchProductById = async (idProducto) => {
    try {
        const response = await fetch(`http://localhost:8080/api/v1/producto/${idProducto}`);
        const data = await response.json();
        return data.object;
    } catch (error) {
        console.error("Error fetching product:", error);
        return null;
    }
};
