from django.shortcuts import render

# Create your views here.
import requests
from django.http import JsonResponse
from django.views import View

class ConsumeApiView(View):
    def get(self, request):
        response = requests.get('http://localhost:8080/api/v1/clientes')
        data = response.json()
        return JsonResponse(data)
