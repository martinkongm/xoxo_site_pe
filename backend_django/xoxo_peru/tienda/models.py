from django.db import models
from django.contrib.auth.hashers import make_password
from django.contrib.auth.models import AbstractBaseUser

# Create your models here.
class Coleccion(models.Model):
    nombre = models.CharField(max_length=150)

    def __str__(self):
        return self.nombre
    
    class Meta:
        db_table = 'tienda_coleccion'
        verbose_name = 'Coleccion'
        verbose_name_plural = 'Colecciones'
    
class Producto(models.Model):
    OPCIONES_STATUS = [
        ('ACTIVO', 'ACTIVO'),
        ('ARCHIVADO', 'ARCHIVADO'),
    ]

    nombre = models.CharField(max_length=200)
    status = models.CharField(max_length=20, choices=OPCIONES_STATUS)
    stock = models.IntegerField(default=0)
    categoria = models.ForeignKey('Categoria', on_delete=models.CASCADE)
    proveedor = models.ForeignKey('Proveedor', on_delete=models.CASCADE)
    coleccion = models.ForeignKey('Coleccion', on_delete=models.SET_NULL, null=True)

    def __str__(self):
        return self.nombre + " | Colección: " + self.coleccion.nombre + " | Estatus: " + self.status + " | Proveedor: " + self.proveedor.nombre

class Categoria(models.Model):
    nombre = models.CharField(max_length=100)

    def __str__(self):
        return self.nombre

class Proveedor(models.Model):
    nombre = models.CharField(max_length=100)

    def __str__(self):
        return self.nombre
    
    class Meta:
        db_table = 'tienda_proveedor'
        verbose_name = 'Proveedor'
        verbose_name_plural = 'Proveedores'

class Cliente(models.Model):
    nombre = models.CharField(max_length=100)
    correo = models.EmailField()
    
    def calcular_gasto_total(self):
        return sum(pedido.total for pedido in self.pedidos.all())

    @property
    def gasto_total(self):
        return self.calcular_gasto_total()

    def __str__(self):
        return self.nombre

class Pedido(models.Model):
    OPCIONES_ESTATUS_PAGO = [
        ('pagado', 'Pagado'),
        ('no_pagado', 'No pagado'),
    ]

    OPCIONES_ESTATUS_PEDIDO = [
        ('procesado', 'Procesado'),
        ('no_procesado', 'No procesado'),
    ]

    OPCIONES_ESTATUS_ENTREGA = [
        ('entregado', 'Entregado'),
        ('no_entregado', 'No entregado'),
    ]

    OPCIONES_FORMA_ENTREGA = [
        ('correosPac', 'Correos PAC'),
        ('pedidosYa', 'Pedidos Ya'),
    ]

    codigo = models.AutoField(primary_key=True)
    fecha = models.DateTimeField(auto_now_add=True)
    cliente = models.ForeignKey(Cliente, related_name='pedidos', on_delete=models.CASCADE)
    total = models.DecimalField(max_digits=10, decimal_places=2)
    estatus_pago = models.CharField(max_length=20, choices=OPCIONES_ESTATUS_PAGO)
    estatus_pedido = models.CharField(max_length=20, choices=OPCIONES_ESTATUS_PEDIDO)
    items = models.IntegerField()
    estatus_entrega = models.CharField(max_length=20, choices=OPCIONES_ESTATUS_ENTREGA)
    forma_entrega = models.CharField(max_length=100, choices=OPCIONES_FORMA_ENTREGA)

    def __str__(self):
        return f"Pedido #{self.codigo} - Cliente: {self.cliente.nombre}"
    
class Usuario(AbstractBaseUser):
    nombre = models.CharField(max_length=100)
    apellido = models.CharField(max_length=100)
    email = models.EmailField(unique=True)

    def set_password(self, raw_password):
        self.password = make_password(raw_password)
    
    def __str__(self):
        return f"{self.nombre} {self.apellido} - {self.email}"