*****************************************************************************
*
* Url shortener
* Jul-2020
* Nearsoft Challenge
* Autor: Abdiel Huerta Mancilla
*
*****************************************************************************


Resumen
*****************************************************************************
Se requiere un sistema para  guardar y buscar en memoria cada alias generado.
El alias se construirá dependiendo la URL recibida:
	Si la URL contiene "google" se genera alias de 5 caracteres.
	Si la URL contiene "yahoo" se genera alias de 7 caracteres.
	Para los casos restantes se eliminarán de la URL ingresada los carecteres 
	especiales, números y vocales, los caracteres resultantes conformaran
	el alias.

Funcionalidad
*****************************************************************************
1.-ingresar a la siguiente ruta en el navegador de internet:

http://localhost:8080/javaChallenge/shortener

2.-Escribir la URL en el formulario.

3.- Generar Alias presionando el boton "Generate short URL"

4.- Buscar URL completa escribiendo el alias en el formulario y 
	presionar el boton "Search Url by Alias"


Enfoque de Diseño
*****************************************************************************
Para resolver el requerimiento se enfocó el diseño en una aplicación Web
construída en Java. 
Se generó un servlet que corre en un servidor Tomcat 9. 
Para el manejo de memoria se creó un hashmap que nos permitirá
buscar por clave/valor las URL y Alias generados.



