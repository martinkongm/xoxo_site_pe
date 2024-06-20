# tienda/services.py
import requests

API_BASE_URL = "http://localhost:8080/api/v1"

def get_all_colecciones():
    response = requests.get(f"{API_BASE_URL}/colecciones")
    return response.json()

def get_coleccion_by_id(coleccion_id):
    response = requests.get(f"{API_BASE_URL}/coleccion/{coleccion_id}")
    return response.json()

def get_all_productos():
    response = requests.get(f"{API_BASE_URL}/productos")
    return response.json()

def get_producto_by_id(producto_id):
    response = requests.get(f"{API_BASE_URL}/producto/{producto_id}")
    return response.json()

def get_productos_by_coleccion(coleccion):
    response = requests.get(f"{API_BASE_URL}/productos/{coleccion}")
    return response.json()
