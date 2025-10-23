from django.db import models

class User(models.Model):
    id_user = models.IntegerField(primary_key=True),
    email = models.EmailField(max_length=254, unique=True),
    nome = models.CharField(max_length=255),
    password = models.CharField(max_length=255),
    is_active = models.BooleanField(),
    is_staff = models.BooleanField()