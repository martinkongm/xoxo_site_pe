package com.xoxo.backend.backendspringboot.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "usuarios")
public class Usuario implements Serializable {
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @Column(name = "apellido_usuario", nullable = false)
    private String apellidoUsuario;

    @Column(name = "correo_usuario", nullable = false)
    private String correoUsuario;

    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;

    @Column(name = "contrasena_usuario", length = 12, nullable = false)
    @JsonIgnore
    private String contrasenaUsuario;

    @OneToMany(mappedBy = "reviewUsuario", cascade = CascadeType.ALL)
    private List<Review> reviewsUsuario;

    @OneToMany(mappedBy = "usuarioPedido", cascade = CascadeType.ALL)
    private List<Pedido> pedidosUsuario;
}
