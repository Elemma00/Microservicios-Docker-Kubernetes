# Microservicios-Docker-Kubernetes
 Repositorio de ejemplos de microservicios en contenedores de docker, orquestados por kubernetes.

# Comandos para Ejecutar

1. Obtenemos las imagenes desde el dockerhub de la respectivas bases de datos utilizadas por los microservicios.

```sh
docker pull mysql:9
docker pull postgres
```

2. Creamos una red de docker para asegurarnos de tener todos los contenedores indexados.

```sh
docker network create spring
```

3. Iniciamos contenedor para la DB Mysql que utiliza el microservicio de usuarios. Este contendor se expone en el puerto 3307 de nuestra máquina host.

```sh
docker run -d -p 3307:3306 --network spring --name mysql9 -e MYSQL_ROOT_PASSWORD=sasa -e
MYSQL_DATABASE=msvc_usuarios -v data-mysql:/var/lib/mysql --restart=always mysql:9 
```

4. Iniciamos contenedor para la DB Postgres que utiliza el microservicio de cursos. Este contenedor se expone en el puerto 5532 de la máquina local

```sh
docker run -d -p 5532:5432 --network spring --name postgres16 -e POSTGRES_PASSWORD=sasa -e
POSTGRES_DB=msvc_cursos -v data-postgres:/var/lib/postgresql/data --restart=always postgres 
```

5. Nos dirijimos a la carpeta donde se encuentra el msvc-usuarios y creamos la imagen a partir del dockerfile 

```sh
docker build -t usuarios .
```

6. Creamos el contenedor a partir de la imagen (**Ojo: una vez que detengan el contenedor este se borrará ya que hemos usado la flag --rm **)

```sh
docker run -p 8001:8001 -d --rm --name msvc-usuarios --network spring usuarios 
```


7. Nos movemos a la carpeta del msvc-cursos y creamos la imagen apatir del dockerfile

```sh
docker build -t cursos .
```

8. Creamos el contenedor a partir de la imagen (**Ojo: una vez que detengan el contenedor este se borrará ya que hemos usado la flag --rm **)

```sh
docker run -p 8002:8002 -d --rm --name msvc-cursos --network spring cursos 
```

9. Listo ahora se pueden hacer pruebas funcionales a los controladores mediante Postman. 

