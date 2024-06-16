package com.xoxo.backend.backendspringboot.presentation.controller;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
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
    public String getList (@RequestBody DetalleCarrito detalleCarrito) {
        if (detalleCarrito == null) {
            return "Error, carrito vacío...";
        }
        //Estos campos debe llamarse igual que en el FRONTEND
        String title = detalleCarrito.getProducto().getNombreProducto();
        int quantity = detalleCarrito.getCantidad();
        double price = detalleCarrito.getPrecioTotal();

        try {
            MercadoPagoConfig.setAccessToken(mercadoLibreToken);

            //Preferencia de venta
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .title(title)
                    .quantity(quantity)
                    .unitPrice(new BigDecimal(price))
                    .currencyId("PEN")
                    .build();

            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

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
