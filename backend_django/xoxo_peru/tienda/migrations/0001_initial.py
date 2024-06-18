# Generated by Django 5.0.4 on 2024-06-16 18:26

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Coleccion',
            fields=[
                ('id_coleccion', models.IntegerField(primary_key=True, serialize=False)),
                ('nombre_coleccion', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='Producto',
            fields=[
                ('id_producto', models.IntegerField(primary_key=True, serialize=False)),
                ('nombre_producto', models.CharField(max_length=100)),
                ('precio_producto', models.DecimalField(decimal_places=2, max_digits=10)),
                ('coleccion', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tienda.coleccion')),
            ],
        ),
    ]
