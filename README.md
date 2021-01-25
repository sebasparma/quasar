# Operación fuego de Quasar (trilateración y mensajes)


## Introducción

Este Api Rest provee los servicios requeridos por la inteligencia de la operación Fuego de Quasar para la compilación de los mensajes recibidos por los tres satélites (kenobi, skywalker y sato) y determinar la posición de la fuente de estos mensajes.

## Arquitectura

La siguiente es una descripción breve de la arquitectura del api.

### Paquetes

El siguiente es un listado de los paquetes manejados dentro del api, los cuales distribuyen las clases por sus funcionalidades.

* controller: Clases que reciben peticiones HTTP y exponen los endpoints de los servicios.
* entity: Clases que representan entidades de base de datos.
* exception: Clases que extienden de la clase Exception para manejo de errores.
* model: Clases que representan los objetos de la aplicación.
* repository: Interfaces usadas por JPA para la interacción con bases de datos.
* service: Clases con lógica de negocio.
* service.util: Clases que exponen funciones utilitarias usadas para la lógica de negocios.

* Para el cumplimiento del Nivel 3 de la Operación se optó por almacenar los datos en una base de datos H2 en memoria.

### Despliegue

La siguiente es una vista de los componentes involucrados en la solución:

![Screenshot](https://github.com/sebasparma/quasar/blob/master/images/Despliegue.JPG?raw=true)

El api se encuentra desplegado en un contenedor de Google Cloud app engine, el cual permite desplegar de manera simple las aplicaciones, enmascarando su dirección puerto expuesto por defecto por medio de un proxy.

## Ejecución

Para descargar la Colección de postman puede dirigirse al siguiente enlace: 

* https://www.getpostman.com/collections/71c8a01c63780131ddc1

También puede encontrar este archivo dentro de las fuentes del repositorio: QuasarGCloud.postman_collection.json

La siguiente es la dirección del api expuesto en la plataforma de Google Cloud:

* Nivel 2 POST: https://trilateracionqasar.rj.r.appspot.com/topsecret
* Nivel 3 POST: https://trilateracionqasar.rj.r.appspot.com/topsecret_split/{saellite_name}
* Nivel 3 GET: https://trilateracionqasar.rj.r.appspot.com/topsecret_split

Para mayor documentación del api: https://trilateracionqasar.rj.r.appspot.com/swagger-ui.html

