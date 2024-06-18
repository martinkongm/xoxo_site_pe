# tienda/views.py

from django.shortcuts import get_object_or_404
from rest_framework import viewsets, status
from rest_framework.response import Response
from .models import Coleccion, Producto
from .serializers import ColeccionSerializer, ProductoSerializer
import requests

BASE_URL = 'http://localhost:8080/api/v1'

class ColeccionViewSet(viewsets.ViewSet):
    def list(self, request):
        response = requests.get(f'{BASE_URL}/colecciones')
        colecciones = response.json().get('object', [])
        for coleccion_data in colecciones:
            coleccion, created = Coleccion.objects.update_or_create(
                id_coleccion=coleccion_data['idColeccion'],
                defaults={'nombre_coleccion': coleccion_data['nombreColeccion']}
            )
        serializer = ColeccionSerializer(Coleccion.objects.all(), many=True)
        return Response(serializer.data)

    def create(self, request):
        response = requests.post(f'{BASE_URL}/coleccion', json=request.data)
        if response.status_code == 201:
            data = response.json().get('object', {})
            coleccion = Coleccion.objects.create(
                id_coleccion=data['idColeccion'],
                nombre_coleccion=data['nombreColeccion']
            )
            serializer = ColeccionSerializer(coleccion)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(response.json(), status=response.status_code)

    def update(self, request, pk=None):
        coleccion = get_object_or_404(Coleccion, pk=pk)
        response = requests.put(f'{BASE_URL}/coleccion/{pk}', json=request.data)
        if response.status_code == 200:
            data = response.json().get('object', {})
            coleccion.nombre_coleccion = data['nombreColeccion']
            coleccion.save()
            serializer = ColeccionSerializer(coleccion)
            return Response(serializer.data)
        return Response(response.json(), status=response.status_code)

    def destroy(self, request, pk=None):
        coleccion = get_object_or_404(Coleccion, pk=pk)
        response = requests.delete(f'{BASE_URL}/coleccion/{pk}')
        if response.status_code == 204:
            coleccion.delete()
            return Response(status=status.HTTP_204_NO_CONTENT)
        return Response(response.json(), status=response.status_code)

class ProductoViewSet(viewsets.ViewSet):
    def list(self, request):
        response = requests.get(f'{BASE_URL}/productos')
        productos = response.json().get('object', [])
        for producto_data in productos:
            coleccion = Coleccion.objects.get(nombre_coleccion=producto_data['nombreColeccion'])
            Producto.objects.update_or_create(
                id_producto=producto_data['idProducto'],
                defaults={
                    'nombre_producto': producto_data['nombreProducto'],
                    'precio_producto': producto_data['precioProducto'],
                    'tamano_producto': producto_data['tamanoProducto'],
                    'beneficios_producto': producto_data['beneficiosProducto'],
                    'imagen_producto': producto_data['imagenProducto'],
                    'stock_producto': producto_data['stockProducto'],
                    'coleccion': coleccion
                }
            )
        serializer = ProductoSerializer(Producto.objects.all(), many=True)
        return Response(serializer.data)

    def create(self, request):
        response = requests.post(f'{BASE_URL}/producto', json=request.data)
        if response.status_code == 201:
            data = response.json().get('object', {})
            coleccion = Coleccion.objects.get(nombre_coleccion=data['nombreColeccion'])
            producto = Producto.objects.create(
                id_producto=data['idProducto'],
                nombre_producto=data['nombreProducto'],
                precio_producto=data['precioProducto'],
                tamano_producto=data['tamanoProducto'],
                beneficios_producto=data['beneficiosProducto'],
                imagen_producto=data['imagenProducto'],
                stock_producto=data['stockProducto'],
                coleccion=coleccion
            )
            serializer = ProductoSerializer(producto)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(response.json(), status=response.status_code)

    def update(self, request, pk=None):
        producto = get_object_or_404(Producto, pk=pk)
        response = requests.put(f'{BASE_URL}/producto/{pk}', json=request.data)
        if response.status_code == 200:
            data = response.json().get('object', {})
            producto.nombre_producto = data['nombreProducto']
            producto.precio_producto = data['precioProducto']
            producto.tamano_producto = data['tamanoProducto']
            producto.beneficios_producto = data['beneficiosProducto']
            producto.imagen_producto = data['imagenProducto']
            producto.stock_producto = data['stockProducto']
            producto.save()
            serializer = ProductoSerializer(producto)
            return Response(serializer.data)
        return Response(response.json(), status=response.status_code)

    def destroy(self, request, pk=None):
        producto = get_object_or_404(Producto, pk=pk)
        response = requests.delete(f'{BASE_URL}/producto/{pk}')
        if response.status_code == 204:
            producto.delete()
            return Response(status=status.HTTP_204_NO_CONTENT)
        return Response(response.json(), status=response.status_code)
