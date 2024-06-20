package com.xoxo.backend.backendspringboot.presentation.controller;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.xoxo.backend.backendspringboot.persistence.entity.Carrito;
import com.xoxo.backend.backendspringboot.persistence.entity.DetalleCarrito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class MercadoPagoController {

    @Value("${codigo.mercadoLibre}")
    private String mercadoLibreToken;

    @PostMapping("/mp")
    public String getList (@RequestBody Carrito carrito) {
        if (carrito == null) {
            return "Error, carrito vacío...";
        }

        List<DetalleCarrito> detallesCarrito = carrito.getDetallesCarritos();

        try {
            MercadoPagoConfig.setAccessToken(mercadoLibreToken);

            //Preferencia de venta
            List<PreferenceItemRequest> items = new ArrayList<>();

            PreferenceItemRequest itemRequest;

            for (DetalleCarrito dc : detallesCarrito) {
                itemRequest = PreferenceItemRequest.builder()
                        .title(dc.getProducto().getNombreProducto())
                        .quantity(dc.getCantidad())
                        .unitPrice(BigDecimal.valueOf(dc.getPrecioTotal()))
                        .currencyId("PEN")
                        .build();

                items.add(itemRequest);
            }

            //Preferencia de control de sucesos
            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("")
                    .pending("")
                    .failure("")
                    .build();

            //Crea una preferencia que contendrá todas las preferencias que haya creado
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .build();

            //Crear un objeto tipo cliente para comunicarse con MP
            PreferenceClient client = new PreferenceClient();

            //Crear una nueva preferencia que será igual a la respuesta que nuestro cliente nos creará a partir de la información que le enviamos
            Preference preference = client.create(preferenceRequest);

            //Retornamos esa preferencia a nuestro FRONTEND
            return preference.getId();
        } catch (MPException | MPApiException e) {
            throw new RuntimeException(e);
        }
    }
}
