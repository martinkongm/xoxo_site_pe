# tienda/serializers.py

from rest_framework import serializers
from .models import Coleccion, Producto

class ColeccionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Coleccion
        fields = '__all__'

class ProductoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Producto
        fields = '__all__'
