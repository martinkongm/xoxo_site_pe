package com.xoxo.backend.backendspringboot.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

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
    private String nombre;

    @Column(name = "apellido_usuario", nullable = false)
    private String apellido;

    @Column(name = "correo_usuario", nullable = false)
    private String correo;

    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;

    @Column(name = "contrasena_usuario", length = 100, nullable = false)
    @JsonIgnore
    private String contrasenaUsuario;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_no_expired")
    private boolean accountNoExpired;

    @Column(name = "account_no_locked")
    private boolean accountNoLocked;

    @Column(name = "credentials_no_expired")
    private boolean credentialsNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<Rol> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_direcciones",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_direccion")
    )
    private List<Direccion> direcciones = new ArrayList<>();

    @OneToMany(mappedBy = "reviewUsuario", cascade = CascadeType.ALL)
    private List<Review> reviewsUsuario;

    @OneToMany(mappedBy = "usuarioPedido", cascade = CascadeType.ALL)
    private List<Pedido> pedidosUsuario;

    @OneToMany(mappedBy = "usuario")
    private List<Carrito> carritoUsuario;
}
