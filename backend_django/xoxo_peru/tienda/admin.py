from django.contrib import admin

# Register your models here.

from .models import Producto, Categoria, Proveedor, Coleccion, Usuario, Pedido

admin.site.register(Producto)
admin.site.register(Categoria)
admin.site.register(Proveedor)
admin.site.register(Coleccion)
admin.site.register(Usuario)
admin.site.register(Pedido)

#xoxoadmin
#xoxo2024