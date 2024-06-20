# En myapp/utils.py

import requests

BASE_URL = 'http://localhost:8080/api/v1'

def fetch_colecciones():
    url = f'{BASE_URL}/colecciones'
    response = requests.get(url)
    return response.json()

def fetch_productos():
    url = f'{BASE_URL}/productos'
    response = requests.get(url)
    return response.json()
