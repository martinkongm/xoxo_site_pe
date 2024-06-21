import React, { useState, useEffect } from "react";
import { Container, Row, Col } from 'react-bootstrap';
import Loading from './components/Loading';
import ShowProducts from '../product/components/ShowProducts';
import Skeleton from "react-loading-skeleton"; 
import ProductSection from "./ProductSection";

import { fetchProducts } from '../../api/Api';
import { filterProducto } from '../../utils/filter';

const Products = () => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const getProducts = async () => {
            setLoading(true);
            try {
                const products = await fetchProducts();
                setData(products);
            } catch (error) {
                console.error("Error fetching products:", error);
            } finally {
                setLoading(false);
            }
        };

        getProducts();
    }, []);

    return (
        <section className="pt-5 pb-5">
            <Container fluid>
                <Row>
                    <Col md={1} />
                    <Col md={10} className="mb-4">
                        <h3 className="mb-3 text-center" style={{ color: '#E45F9A', textAlign: 'justify', marginBottom: '20px', fontWeight: 'normal' }}>
                            BIENVENIDOS A NUESTRO MUNDO DE COSMÉTICA <br />DE INSPIRACIÓN DULCE 🍭🎀
                        </h3>
                        <hr />
                        {loading ? <Skeleton count={4} height={350} /> : (
                            <>
                                <ProductSection title="NUEVO LANZAMIENTO: ¡XOXO BODY SPLASHES! 🍭" data={filterProducto(data, 'Body-splash')}>
                                    <p>A SOLUÇÃO PARA PELE SECA CHEGOU! A sua vida terá um ANTES e DEPOIS com as nossas body butters, PELE HIDRATADA, MACIA e CHEIROSA GARANTIDA! 🍭🧁🍉🍓🥭</p>
                                </ProductSection>
                                <ProductSection title="Exfoliantes Corporales" data={filterProducto(data, 'Exfoliante corporal')}>
                                    <p>Contenido específico para esta sección.</p>
                                </ProductSection>
                                <ProductSection title="Mantecas Corporales" data={filterProducto(data, 'Manteca corporal')}>
                                    <p>Contenido específico para esta sección.</p>
                                </ProductSection>
                                <ProductSection title="Mantecas Emulsionadas" data={filterProducto(data, 'Manteca emulsionada')}>
                                    <p>Contenido específico para esta sección.</p>
                                </ProductSection>
                            </>
                        )}
                    </Col>
                    <Col md={1} />
                </Row>
            </Container>
        </section>
    );
};

export default Products;
