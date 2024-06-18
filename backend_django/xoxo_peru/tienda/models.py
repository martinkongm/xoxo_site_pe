from django.db import models

class Coleccion(models.Model):
    id_coleccion = models.IntegerField(primary_key=True)
    nombre_coleccion = models.CharField(max_length=255)

    def __str__(self):
        return self.nombre_coleccion

class Producto(models.Model):
    id_producto = models.IntegerField(primary_key=True)
    nombre_producto = models.CharField(max_length=255)
    precio_producto = models.FloatField(default=0.0)
    tamano_producto = models.IntegerField(default=0)
    beneficios_producto = models.TextField(default="")
    imagen_producto = models.URLField(default="")
    stock_producto = models.IntegerField(default=0)
    coleccion = models.ForeignKey(Coleccion, related_name='productos', on_delete=models.CASCADE)

    def __str__(self):
        return self.nombre_producto
