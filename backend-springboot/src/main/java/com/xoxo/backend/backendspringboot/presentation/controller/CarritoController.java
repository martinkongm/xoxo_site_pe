package com.xoxo.backend.backendspringboot.presentation.controller;

import com.xoxo.backend.backendspringboot.persistence.entity.Carrito;
import com.xoxo.backend.backendspringboot.persistence.entity.DetalleCarrito;
import com.xoxo.backend.backendspringboot.presentation.dto.carrito.CarritoCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.carrito.CarritoResponseDto;
import com.xoxo.backend.backendspringboot.presentation.dto.carrito.CarritoUpdateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.carrito.DetalleResponseDto;
import com.xoxo.backend.backendspringboot.presentation.payload.MensajeResponse;
import com.xoxo.backend.backendspringboot.service.implementation.CarritoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class CarritoController {

    @Autowired
    private CarritoServiceImpl carritoService;

    @GetMapping("/carritos")
    public ResponseEntity<?> getAllCarritos() {
        List<CarritoResponseDto> carritos = carritoService.getAllCarritos().stream().map(c -> new CarritoResponseDto(
                c.getId(),
                c.getEmailComprador(),
                c.getDetallesCarritos().stream().mapToDouble(DetalleCarrito::getPrecioTotal).sum(),
                c.getDetallesCarritos().stream().map(d -> new DetalleResponseDto(d.getId(), d.getCantidad(), d.getPrecioTotal(), d.getProducto().getNombreProducto())).toList()
        )).toList();
        if (carritos.isEmpty()) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No existen registros.")
                    .object(null)
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Listando registros.")
                .object(carritos)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/carrito/{id}")
    public ResponseEntity<?> getCarritoById(@PathVariable(value = "id") Long id) {
        Optional<Carrito> carritoOpt = carritoService.getCarritoById(id);
        if (carritoOpt.isEmpty()) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intentas buscar no existe.")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        Carrito carrito = carritoOpt.get();
        CarritoResponseDto response = CarritoResponseDto.builder()
                .id(carrito.getId())
                .emailComprador(carrito.getEmailComprador())
                .detalles(carrito.getDetallesCarritos().stream().map(
                        d -> new DetalleResponseDto(
                                d.getId(), d.getCantidad(), d.getPrecioTotal(), d.getProducto().getNombreProducto()
                        )).toList())
                .build();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta exitosa.")
                .object(response)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/carrito")
    public ResponseEntity<?> createCarrito(@RequestBody CarritoCreateDto carrito) {
        try {
            Carrito carritoSave = carritoService.createCarrito(carrito);
            CarritoResponseDto response = CarritoResponseDto.builder()
                    .id(carritoSave.getId())
                    .emailComprador(carritoSave.getEmailComprador())
                    //.usuario(carritoSave.getUsuario().getCorreo())
                    .detalles(null)
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(response)
                    .build(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/carrito/{id}")
    public ResponseEntity<?> updateCarrito(@PathVariable(value = "id") Long id, @RequestBody CarritoUpdateDto carritoDetails) {
        try {
            System.out.println("Updating carrito with id: " + id);
            System.out.println("Carrito details: " + carritoDetails);

            if (carritoService.existsById(id)) {
                Carrito updatedCarrito = carritoService.updateCarrito(id, carritoDetails);
                CarritoResponseDto response = CarritoResponseDto.builder()
                        .id(updatedCarrito.getId())
                        .emailComprador(updatedCarrito.getEmailComprador())
                        //.usuario(updatedCarrito.getUsuario() != null ? updatedCarrito.getUsuario().getCorreo() : null)
                        .detalles(updatedCarrito.getDetallesCarritos().stream().map(d ->
                                new DetalleResponseDto(d.getId(), d.getCantidad(), d.getPrecioTotal(), d.getProducto().getNombreProducto())
                        ).collect(Collectors.toList()))
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Modificado correctamente")
                        .object(response)
                        .build(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/carrito/{id}")
    public ResponseEntity<?> deleteCarrito(@PathVariable(value = "id") Long id) {
        try {
            carritoService.deleteCarrito(id);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Eliminado correctamente.")
                    .object(null)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping("/carrito/{carritoId}/productos/{productoId}")
    public ResponseEntity<?> addProductToCarrito(@PathVariable(value = "carritoId") Long carritoId, @PathVariable(value = "productoId") Long productoId, @RequestParam int cantidad, @RequestParam int precioTotal) {
        try {
            DetalleCarrito detalleCarrito = carritoService.addProductToCarrito(carritoId, productoId, cantidad, precioTotal);
            DetalleResponseDto response = DetalleResponseDto.builder()
                    .id(detalleCarrito.getId())
                    .cantidad(detalleCarrito.getCantidad())
                    .precioTotal(detalleCarrito.getPrecioTotal())
                    .nombreProducto(detalleCarrito.getProducto().getNombreProducto())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Producto agregado al carrito correctamente")
                    .object(response)
                    .build(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
