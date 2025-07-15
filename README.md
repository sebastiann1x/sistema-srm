# Estructura del Proyecto

## Models
Las clases de este paquete representan los elementos que se guardan en la base de datos y sus atributos.  
Por lo tanto, cada clase tiene muy poca lógica o casi nada. Su propósito principal es modelar los datos para que podamos usarlos dentro del programa.  
Por ejemplo, si quisiéramos actualizar un usuario, consultaríamos la base de datos por ese usuario, reconstruiríamos el objeto `Usuario` en Java con los datos extraídos, lo modificaríamos, y luego lo guardaríamos actualizado nuevamente.

## Repositories
Este paquete contiene las clases que interactúan directamente con los datos (archivos o base de datos).  
Por ejemplo, `UsuarioRepository` se encarga del CRUD (Crear, Leer, Actualizar y Borrar) de usuarios.  
Las validaciones como si el nombre de usuario ya existe no se hacen aquí, sino en las clases de servicios.

## Services
Las clases en este paquete aplican la lógica del negocio.  
Aunque las clases de `Repositories` interactúan directamente con los datos, aquí se hacen las validaciones, reglas de formato (como longitud de nombre de usuario, cédulas, etc.), y procesamiento previo/post.

## Util
Este paquete contiene herramientas auxiliares que no encajan en las demás categorías.  
Por ejemplo, la clase que gestiona la conexión con la base de datos está aquí. Todos los `Repositories` dependen de esta clase para conectarse.

## GUI
Aquí se encuentran todas las clases que componen la interfaz gráfica construida con `Swing` y `.form`.

## Exceptions
Contiene las clases de excepciones personalizadas, para manejar errores definidos por el sistema, como por ejemplo: `AccountException`, `UserException`, etc.
