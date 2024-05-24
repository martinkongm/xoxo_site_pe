package com.xoxo.backend.backendspringboot.controllers;

import com.xoxo.backend.backendspringboot.models.dto.producto.ProductoCreateDto;
import com.xoxo.backend.backendspringboot.models.dto.producto.ProductoResponseDto;
import com.xoxo.backend.backendspringboot.models.dto.producto.ProductoUpdateDto;
import com.xoxo.backend.backendspringboot.models.entities.Producto;
import com.xoxo.backend.backendspringboot.models.payload.MensajeResponse;
import com.xoxo.backend.backendspringboot.services.IProductoService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public ResponseEntity<?> showAll() {
        List<Producto> productos = productoService.listAll();
        if (productos == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No existen registros.")
                    .object(null)
                    .build(), HttpStatus.OK);
        }
        List<ProductoResponseDto> response = productos.stream()
                .map(producto -> ProductoResponseDto.builder()
                        .idProducto(producto.getIdProducto())
                        .nombreProducto(producto.getNombreProducto())
                        .precioProducto(producto.getPrecioProducto())
                        .tamanoProducto(producto.getTamanoProducto())
                        .beneficiosProducto(producto.getBeneficiosProducto())
                        .imagenProducto(producto.getImagenProducto())
                        .stockProducto(producto.getStockProducto())
                        .nombreColeccion(producto.getColeccion().getNombreColeccion())
                        .build())
                .toList();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Listando registros.")
                .object(response)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        if (producto == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intentas buscar no existe.")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        ProductoResponseDto response = ProductoResponseDto.builder()
                .idProducto(producto.getIdProducto())
                .nombreProducto(producto.getNombreProducto())
                .precioProducto(producto.getPrecioProducto())
                .tamanoProducto(producto.getTamanoProducto())
                .beneficiosProducto(producto.getBeneficiosProducto())
                .imagenProducto(producto.getImagenProducto())
                .stockProducto(producto.getStockProducto())
                .nombreColeccion(producto.getColeccion().getNombreColeccion())
                .build();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta exitosa.")
                .object(response)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/producto")
    public ResponseEntity<?> create(@RequestBody ProductoCreateDto productoCreateDto) {
        try {
            Producto productoSave = productoService.save(productoCreateDto);
            ProductoResponseDto response = ProductoResponseDto.builder()
                    .idProducto(productoSave.getIdProducto())
                    .nombreProducto(productoSave.getNombreProducto())
                    .precioProducto(productoSave.getPrecioProducto())
                    .tamanoProducto(productoSave.getTamanoProducto())
                    .beneficiosProducto(productoSave.getBeneficiosProducto())
                    .imagenProducto(productoSave.getImagenProducto())
                    .stockProducto(productoSave.getStockProducto())
                    .nombreColeccion(productoSave.getColeccion().getNombreColeccion())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(response)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<?> update(@RequestBody ProductoUpdateDto productoUpdateDto, @PathVariable Long id) {
        try {
            if (!productoService.existsById(id)) {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
            productoUpdateDto.setIdProducto(id);
            Producto productoUpdate = productoService.update(productoUpdateDto);
            ProductoResponseDto response = ProductoResponseDto.builder()
                    .idProducto(productoUpdate.getIdProducto())
                    .nombreProducto(productoUpdate.getNombreProducto())
                    .precioProducto(productoUpdate.getPrecioProducto())
                    .tamanoProducto(productoUpdate.getTamanoProducto())
                    .beneficiosProducto(productoUpdate.getBeneficiosProducto())
                    .imagenProducto(productoUpdate.getImagenProducto())
                    .stockProducto(productoUpdate.getStockProducto())
                    .nombreColeccion(productoUpdate.getColeccion().getNombreColeccion())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Modificado correctamente")
                    .object(response)
                    .build(), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Producto producto = productoService.findById(id);
            if (producto == null) {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta eliminar no se encuentra en la base de datos.")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
            productoService.delete(producto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Eliminado correctamente")
                    .object(null)
                    .build(), HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
