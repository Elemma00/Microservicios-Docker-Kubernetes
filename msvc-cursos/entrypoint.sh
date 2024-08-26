#!/bin/sh
# Encuentra el archivo JAR que comienza con 'msvc-cursos' y lo ejecuta
exec java -jar $(ls msvc-cursos*.jar)