import requests

BASE_URL = 'http://localhost:8080/api/v1'

def fetch_colecciones_from_api():
    url = f'{BASE_URL}/colecciones'
    response = requests.get(url)
    if response.status_code == 200:
        return response.json().get('object', [])
    return []

def fetch_productos_from_api():
    url = f'{BASE_URL}/productos'
    response = requests.get(url)
    if response.status_code == 200:
        return response.json().get('object', [])
    return []
