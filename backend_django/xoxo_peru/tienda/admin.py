# tienda/admin.py

from django.contrib import admin
from .models import Coleccion, Producto
from .api_consumer import fetch_colecciones_from_api, fetch_productos_from_api

class ColeccionAdmin(admin.ModelAdmin):
    list_display = ('id_coleccion', 'nombre_coleccion', 'productos_list')

    def productos_list(self, obj):
        return ', '.join(obj.productos_coleccion)

    productos_list.short_description = 'Productos'

    def get_queryset(self, request):
        queryset = super().get_queryset(request)
        colecciones_from_api = fetch_colecciones_from_api()
        # Procesar los datos recibidos y actualizar o crear instancias de Coleccion
        for coleccion_data in colecciones_from_api:
            id_coleccion = coleccion_data['idColeccion']
            nombre_coleccion = coleccion_data['nombreColeccion']
            productos_coleccion = coleccion_data['productosColeccion']
            
            # Actualizar o crear la instancia de Coleccion
            coleccion, created = Coleccion.objects.update_or_create(
                id_coleccion=id_coleccion,
                defaults={
                    'nombre_coleccion': nombre_coleccion,
                    'productos_coleccion': productos_coleccion,
                }
            )
        return queryset

class ProductoAdmin(admin.ModelAdmin):
    list_display = ('id_producto', 'nombre_producto', 'precio_producto', 'tamano_producto')

    def get_queryset(self, request):
        queryset = super().get_queryset(request)
        productos_from_api = fetch_productos_from_api()
        # Procesar los datos recibidos y actualizar o crear instancias de Producto
        for producto_data in productos_from_api:
            id_producto = producto_data['idProducto']
            nombre_producto = producto_data['nombreProducto']
            precio_producto = producto_data['precioProducto']
            tamano_producto = producto_data['tamanoProducto']
            nombre_coleccion = producto_data['nombreColeccion']
            # Podrías añadir más campos aquí según tu modelo de Producto
            
            # Actualizar o crear la instancia de Producto
            producto, created = Producto.objects.update_or_create(
                id_producto=id_producto,
                defaults={
                    'nombre_producto': nombre_producto,
                    'precio_producto': precio_producto,
                    'tamano_producto': tamano_producto,
                    'nombre_coleccion': nombre_coleccion,
                }
            )
        return queryset

# Registrar modelos en Django Admin
admin.site.register(Coleccion, ColeccionAdmin)
admin.site.register(Producto, ProductoAdmin)
