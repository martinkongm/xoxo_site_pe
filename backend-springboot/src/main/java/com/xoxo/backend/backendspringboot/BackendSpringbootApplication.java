package com.xoxo.backend.backendspringboot;

import com.xoxo.backend.backendspringboot.persistence.entity.*;
import com.xoxo.backend.backendspringboot.persistence.repository.ColeccionRepository;
import com.xoxo.backend.backendspringboot.persistence.repository.ProductoRepository;
import com.xoxo.backend.backendspringboot.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BackendSpringbootApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BackendSpringbootApplication.class, args);
	}


	@Bean
	CommandLineRunner init(UsuarioRepository userRepository, ColeccionRepository coleccionRepository, ProductoRepository productoRepository) {
		return args -> {
			//CREATE COLLECTIONS
			Coleccion bodySplash = Coleccion.builder()
					.nombreColeccion("Body-splash")
					.build();

			Coleccion exfolianteCorporal = Coleccion.builder()
					.nombreColeccion("Exfoliante corporal")
					.build();

			Coleccion mantecaCorporal = Coleccion.builder()
					.nombreColeccion("Manteca corporal")
					.build();

			Coleccion mantecaEmulsionada = Coleccion.builder()
					.nombreColeccion("Manteca emulsionada")
					.build();

			coleccionRepository.saveAll(List.of(bodySplash, exfolianteCorporal, mantecaCorporal, mantecaEmulsionada));
			//CREATE PRODUCTS
			Producto algodonDulceBodySplash = Producto.builder()
					.nombreProducto("Algodón dulce")
					.precioProducto(200.00)
					.tamanoProducto(100)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/body-splash/algodon-dulce-100.png")
					.stockProducto(50)
					.coleccion(bodySplash)
					.reviewsProducto(null)
					.build();

			Producto chicleSandiaBodySplash = Producto.builder()
					.nombreProducto("Chicle de sandía")
					.precioProducto(200.00)
					.tamanoProducto(100)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/body-splash/chicle-sandia-100.png")
					.stockProducto(50)
					.coleccion(bodySplash)
					.reviewsProducto(null)
					.build();

			Producto cupcakeVainillaBodySplash = Producto.builder()
					.nombreProducto("Cupcake de vainilla")
					.precioProducto(200.00)
					.tamanoProducto(100)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/body-splash/cupcake-vainilla-100.png")
					.stockProducto(50)
					.coleccion(bodySplash)
					.reviewsProducto(null)
					.build();

			Producto milkshakeFresaBodySplash = Producto.builder()
					.nombreProducto("Milkshake de fresa")
					.precioProducto(200.00)
					.tamanoProducto(100)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/body-splash/milkshake-fresa-100.png")
					.stockProducto(50)
					.coleccion(bodySplash)
					.reviewsProducto(null)
					.build();

			Producto algodonDulceExfolianteCorporal = Producto.builder()
					.nombreProducto("Algodón dulce")
					.precioProducto(200.00)
					.tamanoProducto(300)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/exfoliante/corporal/algodon-dulce-300.png")
					.stockProducto(50)
					.coleccion(exfolianteCorporal)
					.reviewsProducto(null)
					.build();

			Producto chicleSandiaExfolianteCorporal = Producto.builder()
					.nombreProducto("Chicle de sandía")
					.precioProducto(200.00)
					.tamanoProducto(300)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/exfoliante/corporal/chicle-sandia-300.png")
					.stockProducto(50)
					.coleccion(exfolianteCorporal)
					.reviewsProducto(null)
					.build();

			Producto mangoTropicalExfolianteCorporal = Producto.builder()
					.nombreProducto("Mango tropical")
					.precioProducto(200.00)
					.tamanoProducto(300)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/exfoliante/corporal/mango-tropical-300.png")
					.stockProducto(50)
					.coleccion(exfolianteCorporal)
					.reviewsProducto(null)
					.build();

			Producto milkshakeFresaExfolianteCorporal = Producto.builder()
					.nombreProducto("Milkshake de fresa")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/exfoliante/corporal/milkshake-fresa-200.png")
					.stockProducto(50)
					.coleccion(exfolianteCorporal)
					.reviewsProducto(null)
					.build();

			Producto milkshakeFresaExfolianteCorporal2 = Producto.builder()
					.nombreProducto("Milkshake de fresa")
					.precioProducto(200.00)
					.tamanoProducto(300)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/exfoliante/corporal/milkshake-fresa-300.png")
					.stockProducto(50)
					.coleccion(exfolianteCorporal)
					.reviewsProducto(null)
					.build();

			Producto algodonDulceMantecaCorporal = Producto.builder()
					.nombreProducto("Algodón dulce")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/corporal/algodon-dulce-200-1.png")
					.stockProducto(50)
					.coleccion(mantecaCorporal)
					.reviewsProducto(null)
					.build();

			Producto algodonDulceMantecaCorporal2 = Producto.builder()
					.nombreProducto("Algodón dulce")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/corporal/algodon-dulce-200-2.png")
					.stockProducto(50)
					.coleccion(mantecaCorporal)
					.reviewsProducto(null)
					.build();

			Producto chicleSandiaMantecaCorporal = Producto.builder()
					.nombreProducto("Chicle de sandía")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/corporal/chicle-sandia-200-1.png")
					.stockProducto(50)
					.coleccion(mantecaCorporal)
					.reviewsProducto(null)
					.build();

			Producto chicleSandiaMantecaCorporal2 = Producto.builder()
					.nombreProducto("Algodón dulce")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/corporal/chicle-sandia-200-2.png")
					.stockProducto(50)
					.coleccion(mantecaCorporal)
					.reviewsProducto(null)
					.build();

			Producto cupcakeVainillaMantecaCorporal = Producto.builder()
					.nombreProducto("Cupcake de vainilla")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/corporal/cupcake-vainilla-200-1.png")
					.stockProducto(50)
					.coleccion(mantecaCorporal)
					.reviewsProducto(null)
					.build();

			Producto cupcakeVainillaMantecaCorporal2 = Producto.builder()
					.nombreProducto("Algodón dulce")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/corporal/cupcake-vainilla-200-2.png")
					.stockProducto(50)
					.coleccion(mantecaCorporal)
					.reviewsProducto(null)
					.build();

			Producto milkshakeFresaMantecaCorporal = Producto.builder()
					.nombreProducto("Milkshake de fresa")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/corporal/milkshake-fresa-200-1.png")
					.stockProducto(50)
					.coleccion(mantecaCorporal)
					.reviewsProducto(null)
					.build();

			Producto milkshakeFresaMantecaCorporal2 = Producto.builder()
					.nombreProducto("Algodón dulce")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/corporal/milkshake-fresa-200-2.png")
					.stockProducto(50)
					.coleccion(mantecaCorporal)
					.reviewsProducto(null)
					.build();

			Producto algodonDulceMantecaEmulsionada = Producto.builder()
					.nombreProducto("Algodón dulce")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/emulsionada/algodon-dulce-200.png")
					.stockProducto(50)
					.coleccion(mantecaEmulsionada)
					.reviewsProducto(null)
					.build();

			Producto chicleSandiaMantecaEmulsionada = Producto.builder()
					.nombreProducto("Chicle de sandía")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/emulsionada/chicle-sandia-200.png")
					.stockProducto(50)
					.coleccion(mantecaEmulsionada)
					.reviewsProducto(null)
					.build();

			Producto cupcakeVainillaMantecaEmulsionada = Producto.builder()
					.nombreProducto("Algodón dulce")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/emulsionada/cupcake-vainilla-200.png")
					.stockProducto(50)
					.coleccion(mantecaEmulsionada)
					.reviewsProducto(null)
					.build();

			Producto milkshakeFresaMantecaEmulsionada = Producto.builder()
					.nombreProducto("Algodón dulce")
					.precioProducto(200.00)
					.tamanoProducto(200)
					.beneficiosProducto("Muchos beneficios")
					.imagenProducto("/img/coleccion/manteca/emulsionada/milkshake-fresa-200.png")
					.stockProducto(50)
					.coleccion(mantecaEmulsionada)
					.reviewsProducto(null)
					.build();

			productoRepository.saveAll(List.of(algodonDulceBodySplash,
					chicleSandiaBodySplash,
					cupcakeVainillaBodySplash,
					milkshakeFresaBodySplash,
					algodonDulceExfolianteCorporal,
					chicleSandiaExfolianteCorporal,
					mangoTropicalExfolianteCorporal,
					milkshakeFresaExfolianteCorporal,
					milkshakeFresaExfolianteCorporal2,
					algodonDulceMantecaCorporal,
					algodonDulceMantecaCorporal2,
					chicleSandiaMantecaCorporal,
					chicleSandiaMantecaCorporal2,
					cupcakeVainillaMantecaCorporal,
					cupcakeVainillaMantecaCorporal2,
					milkshakeFresaMantecaCorporal,
					milkshakeFresaMantecaCorporal2,
					algodonDulceMantecaEmulsionada,
					chicleSandiaMantecaEmulsionada,
					cupcakeVainillaMantecaEmulsionada,
					milkshakeFresaMantecaEmulsionada));

			//CREATE PERMISSIONS
			Permiso createPermission = Permiso.builder()
					.nombre("CREATE")
					.build();

			Permiso readPermission = Permiso.builder()
					.nombre("READ")
					.build();

			Permiso updatePermission = Permiso.builder()
					.nombre("UPDATE")
					.build();

			Permiso deletePermission = Permiso.builder()
					.nombre("DELETE")
					.build();

			Permiso refactorPermission = Permiso.builder()
					.nombre("REFACTOR")
					.build();

			//CREATE ROLES
			Rol roleAdmin = Rol.builder()
					.rolEnum(RolEnum.ADMIN)
					.listaPermisos(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			Rol roleUser = Rol.builder()
					.rolEnum(RolEnum.USER)
					.listaPermisos(Set.of(createPermission, readPermission))
					.build();
			/*
			Rol roleInvited = Rol.builder()
					.rolEnum(RolEnum.INVITED)
					.listaPermisos(Set.of(readPermission))
					.build();
			*/
			Rol roleDeveloper = Rol.builder()
					.rolEnum(RolEnum.DEVELOPER)
					.listaPermisos(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			//CREATE USERS
			Usuario userMartin = Usuario.builder()
					.nombre("Martin")
					.apellido("Kong")
					.correo("martin@correo.com")
					.fechaRegistro(new Date())
					.contrasenaUsuario(passwordEncoder.encode("supermartin123"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			Usuario userJhennifer = Usuario.builder()
					.nombre("Jhennifer")
					.apellido("Guevara")
					.correo("jhennifer@correo.com")
					.fechaRegistro(new Date())
					.contrasenaUsuario(passwordEncoder.encode("superjhenni123"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			Usuario userDaniel = Usuario.builder()
					.nombre("Daniel")
					.apellido("Ramírez")
					.correo("daniel@correo.com")
					.fechaRegistro(new Date())
					.contrasenaUsuario(passwordEncoder.encode("superdaniel123"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			Usuario userJorge = Usuario.builder()
					.nombre("Jorge")
					.apellido("Angulo")
					.correo("jorge@correo.com")
					.fechaRegistro(new Date())
					.contrasenaUsuario(passwordEncoder.encode("superjorge123"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			Usuario userJacobo = Usuario.builder()
					.nombre("Jefferson")
					.apellido("Jacobo")
					.correo("jacobo@correo.com")
					.fechaRegistro(new Date())
					.contrasenaUsuario(passwordEncoder.encode("superjacobo123"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			userRepository.saveAll(List.of(userMartin, userJhennifer, userDaniel, userJorge, userJacobo));
		};
	}
}
