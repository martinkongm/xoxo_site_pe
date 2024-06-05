package com.xoxo.backend.backendspringboot.persistence.entity;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "pedidos")
public class Pedido implements Serializable{
    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @Column(name = "fecha_pedido")
    private Date fechaPedido;

    @Column(name = "total_pedido")
    private Double totalPedido;

    @Column(name = "nombres_cliente_pedido")
    private String nombresCliente;

    @Column(name = "apellidos_cliente_pedido")
    private String apellidosCliente;

    @Column(name = "email_cliente_pedido")
    private String emailCliente;

    /*
    @Column(name = "pais_cliente_pedido")
    private String pais;
    */

    @Column(name = "telefono_pedido")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuario usuarioPedido;

    @ManyToOne
    @JoinColumn(name = "direccion_pedido")
    private Direccion direccion;

    @ManyToOne
    @JoinColumn(name = "envio_pedido")
    private MetodoEnvio metodoEnvio;

    @ManyToOne
    @JoinColumn(name = "estatus_pedido")
    private EstatusPedido estatusPedido;

    /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
    private List<Producto> productos;*/
}

/*
package com.xoxo.backend.backendspringboot.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "pedido_producto")
public class PedidoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_producto")
    private Long idPedidoProducto;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
}

* */