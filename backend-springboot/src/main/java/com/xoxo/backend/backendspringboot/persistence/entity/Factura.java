package com.xoxo.backend.backendspringboot.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long idFactura;

    @Column(name = "numero_factura", nullable = false, unique = true)
    private String numeroFactura;

    @Column(name = "fecha_emision", nullable = false)
    private Date fechaEmision;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @Column(name = "descuento")
    private Double descuento;

    @Column(name = "impuestos")
    private Double impuestos;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @OneToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido", unique = true)
    private Pedido pedido;
}
