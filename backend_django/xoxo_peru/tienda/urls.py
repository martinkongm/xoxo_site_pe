from .views import ConsumeApiView
from django.urls import path

urlpatterns = [
    path('consume-api/', ConsumeApiView.as_view(), name='consume_api'),
]