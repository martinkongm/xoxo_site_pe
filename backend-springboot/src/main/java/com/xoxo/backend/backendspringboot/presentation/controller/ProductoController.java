package com.xoxo.backend.backendspringboot.presentation.controller;

import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;
import com.xoxo.backend.backendspringboot.presentation.dto.producto.ProductoCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.producto.ProductoResponseDto;
import com.xoxo.backend.backendspringboot.presentation.dto.producto.ProductoUpdateDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Producto;
import com.xoxo.backend.backendspringboot.presentation.payload.MensajeResponse;
import com.xoxo.backend.backendspringboot.service.interfaces.ColeccionService;
import com.xoxo.backend.backendspringboot.service.interfaces.ProductoService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ProductoController {

    private final ProductoService productoService;
    private final ColeccionService coleccionService;

    public ProductoController(ProductoService productoService, ColeccionService coleccionService) {
        this.productoService = productoService;
        this.coleccionService = coleccionService;
    }

    @GetMapping("/productos")
    public ResponseEntity<?> showAll() {
        List<Producto> productos = productoService.listAll();
        if (productos == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No existen registros.")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
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

    @GetMapping("/productos/{coleccion}")
    public ResponseEntity<?> showByCollecion(@PathVariable String coleccion) {
        Coleccion miColeccion = coleccionService.getColeccionByName(coleccion);
        if (miColeccion == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("La colección no existe.")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        List<Producto> productos = productoService.findProductosByColeccion(miColeccion);

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

    @GetMapping("/productos/buscar")
    public ResponseEntity<?> buscarProductos(@RequestParam(name = "nombre") String nombre) {
        List<ProductoResponseDto> response = productoService.buscarProductos(nombre)
                .stream()
                .map(p -> new ProductoResponseDto(p.getIdProducto(), p.getNombreProducto(), p.getPrecioProducto(), p.getTamanoProducto(), p.getBeneficiosProducto(), p.getColeccion().getNombreColeccion(), p.getStockProducto(), p.getImagenProducto())).toList();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Listando registros.")
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
