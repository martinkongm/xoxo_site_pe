# Generated by Django 5.0.4 on 2024-04-14 22:17

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('tienda', '0003_alter_proveedor_table'),
    ]

    operations = [
        migrations.AlterModelOptions(
            name='proveedor',
            options={'verbose_name': 'Proveedor', 'verbose_name_plural': 'Proveedores'},
        ),
        migrations.AlterModelTable(
            name='proveedor',
            table='tienda_proveedor',
        ),
    ]