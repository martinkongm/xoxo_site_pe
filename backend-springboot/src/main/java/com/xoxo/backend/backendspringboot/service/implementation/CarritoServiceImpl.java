package com.xoxo.backend.backendspringboot.service.implementation;

import com.xoxo.backend.backendspringboot.persistence.entity.Carrito;
import com.xoxo.backend.backendspringboot.persistence.entity.DetalleCarrito;
import com.xoxo.backend.backendspringboot.persistence.entity.Producto;
import com.xoxo.backend.backendspringboot.persistence.entity.Usuario;
import com.xoxo.backend.backendspringboot.persistence.repository.CarritoRepository;
import com.xoxo.backend.backendspringboot.persistence.repository.DetalleCarritoRepository;
import com.xoxo.backend.backendspringboot.persistence.repository.ProductoRepository;
import com.xoxo.backend.backendspringboot.persistence.repository.UsuarioRepository;
import com.xoxo.backend.backendspringboot.presentation.dto.carrito.CarritoCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.carrito.CarritoUpdateDto;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarritoServiceImpl {
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Carrito> getAllCarritos() {
        return carritoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Carrito> getCarritoById(Long id) {
        return carritoRepository.findById(id);
    }

    @Transactional
    public Carrito createCarrito(CarritoCreateDto carritoCreateDto) {
        Carrito carrito = Carrito.builder()
                .emailComprador(carritoCreateDto.getEmailComprador())
                .detallesCarritos(null)
                .build();
        return carritoRepository.save(carrito);
    }


    @Transactional
    public Carrito updateCarrito(Long id, CarritoUpdateDto carritoDetails) {
        //Optional<Usuario> usuarioOptional = usuarioRepository.findById(carritoDetails.getIdUsuario());
        //Usuario usuario = usuarioOptional.orElse(null);

        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carrito not found for this id :: " + id));

        //carrito.setUsuario(usuario);

        // Obtener los detalles existentes
        List<DetalleCarrito> existingDetalles = carrito.getDetallesCarritos();

        // Mapear los nuevos detalles del DTO
        List<DetalleCarrito> newDetalles = carritoDetails.getDetalles().stream().map(d -> {
            Producto producto = productoRepository.findById(d.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found for this id :: " + d.getProductoId()));
            return new DetalleCarrito(d.getId(), d.getCantidad(), d.getPrecioTotal(), carrito, producto);
        }).collect(Collectors.toList());

        // Eliminar detalles que no están en los nuevos detalles
        for (DetalleCarrito existingDetalle : existingDetalles) {
            if (newDetalles.stream().noneMatch(d -> d.getId().equals(existingDetalle.getId()))) {
                detalleCarritoRepository.delete(existingDetalle);
            }
        }

        // Agregar o actualizar detalles
        for (DetalleCarrito newDetalle : newDetalles) {
            if (newDetalle.getId() == null) {
                detalleCarritoRepository.save(newDetalle); // Nuevo detalle
            } else {
                // Actualizar detalle existente
                DetalleCarrito existingDetalle = detalleCarritoRepository.findById(newDetalle.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Detalle not found for this id :: " + newDetalle.getId()));
                existingDetalle.setCantidad(newDetalle.getCantidad());
                existingDetalle.setPrecioTotal(newDetalle.getPrecioTotal());
                existingDetalle.setProducto(newDetalle.getProducto());
                detalleCarritoRepository.save(existingDetalle);
            }
        }

        carrito.setDetallesCarritos(newDetalles);

        return carritoRepository.save(carrito);
    }

    @Transactional
    public void deleteCarrito(Long id) {
        Carrito carrito = carritoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Carrito not found for this id :: " + id));
        List<DetalleCarrito> detalles = carrito.getDetallesCarritos();
        for (DetalleCarrito d : detalles) detalleCarritoRepository.delete(d);
        carritoRepository.delete(carrito);
    }

    @Transactional
    public DetalleCarrito addProductToCarrito(Long carritoId, Long productoId, int cantidad, double precioTotal) {
        Carrito carrito = carritoRepository.findById(carritoId).orElseThrow(() -> new ResourceNotFoundException("Carrito not found for this id :: " + carritoId));
        Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new ResourceNotFoundException("Producto not found for this id :: " + productoId));

        DetalleCarrito detalleCarrito = new DetalleCarrito();
        detalleCarrito.setCarrito(carrito);
        detalleCarrito.setProducto(producto);
        detalleCarrito.setCantidad(cantidad);
        detalleCarrito.setPrecioTotal(precioTotal);
        return detalleCarritoRepository.save(detalleCarrito);
    }

    public boolean existsById(Long id) {
        return carritoRepository.existsById(id);
    }
}
