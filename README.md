# Prueba Tecnica

Este es un proyecto de ejemplo utilizando Spring Boot.

## Requisitos Previos

- Java 11 o superior
- Maven 3.6.3 o superior
- Spring Tool Suite 4 o VSCode o similar

## Objetivo de la prueba

- Puede que haya errores en el proyecto, corregirlos
- Agregar un identificador unico a cada usuario, hacer todas las modificaciones necesarias para que el servicio por defecto sea capaz de devolver el dato
- Crear un nuevo endpoint para poder obtener los datos de un usuario segun su identificador
- Crear un nuevo endpoint capaz de actualizar la ciudad de cada usuario
- El servicio actual devuelve los datos tal como estan en el json, pero deben ser ordenados por edad de menor a mayor.
- Documentar todo lo que falte por documentar en el codigo y en este propio fichero README.
- Aplicar buenas practicas en todo el proyecto. 

## Objetivo de la prueba (Extra)

- Crea los testcase necesarios para validar todos los endpoints.
- Autenticación y autorización, implementa un sistema utilizando spring security. usando JWT (json web tokens) , que cada peticion de todas las anteriores necesiten un token valido en las llamadas (documentar como debe verse la llamada en postman o programa similar, agregar capturas al proyecto)
- Crea un endpoint capaz de eliminar del fichero json uno de los usuarios , manteniendo un backup del fichero anterior, aunque no sea accesible.

## Sugerencias



## Estructura del Proyecto

- com.technical.test.controller.rest -> Servicios rest
- com.technical.test.DTO -> objetos 
- com.technical.test.json -> clases de ayuda para tratamiento de json


## Instalación

1. Crea tu proyecto en Spring Tool Suite 4
** https://cdn.spring.io/spring-tools/release/STS4/4.25.0.RELEASE/dist/e4.33/spring-tool-suite-4-4.25.0.RELEASE-e4.33.0-win32.win32.x86_64.zip **

2. Ejecuta el siguiente comando para descargar todas las dependencias y construir el proyecto (realizar dentro de la carpeta del proyecto):
**  ./mvnw clean install **
    

## Ejecución del Servidor

Para iniciar el servidor, ejecuta el siguiente comando:
** ./mvnw spring-boot:run **

El servidor se iniciará en http://localhost:7896.

## Uso
### Endpoint de Ejemplo
GET /init/v1/getUsers
Este endpoint devuelve un objeto JSON de ejemplo.

curl -X GET http://localhost:7896/init/v1/getUsers

## Configuración
Archivo application.properties
El archivo application.properties contiene la configuración de la aplicación. Puedes agregar tus propias configuraciones según sea necesario.

## Dependencias
Las dependencias del proyecto están gestionadas en el archivo pom.xml. Asegúrate de revisar y actualizar las versiones según tus necesidades.



<br> <br> <br> <br> 

# Código entregado por Manuel Yáñez



- Se han completado los 7 objetivos de la prueba
- Se han completado 2 de los 3 objetivos extra de la prueba

Si en vez de usar como origen de datos el json se hubiese utilizado una bbdd embebida el resultado hubiese sido mejor.

Se ha utilizado el json como origen de datos porque he pensado que eso es lo que se Alejandro Villergas hubiese preferido, por la conversación que tuve el jueves por la mañana. El me comentó que quería que se comprobara el manejo de json s.

Intente implementar el objetivo de JWT, pero como vi que me iba a requerir más tiempo he preferido entregaros ya lo que hice el jueves (21/11/2024).
( Con spring boot 3 y java17, creo que me hubiese sido mas sencillo, para JWT hay mas documentación )

El codigo esta en github:
git clone https://github.com/manuelyt/technicaltest.git

En la raiz del proyecto hay 2 archivos de documentacion, un doc y un pdf con informacion para la ejecucion del proyecto y algunos comentarios.

<br> <br> 