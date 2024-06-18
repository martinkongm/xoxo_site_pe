# tienda/urls.py

from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import ColeccionViewSet, ProductoViewSet

router = DefaultRouter()
router.register(r'colecciones', ColeccionViewSet, basename='coleccion')
router.register(r'productos', ProductoViewSet, basename='producto')

urlpatterns = [
    path('', include(router.urls)),
]
