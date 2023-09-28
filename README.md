# Challenge_foro_alura
Foro Alura

INSTRUCCIONES DE USO - Repositorio README del Proyecto Foro Alura
Este depósito alberga el código fuente de un proyecto backend que replica el funcionamiento del Foro Alura. La esencia del proyecto radica en la creación de una API REST empleando el framework Spring. A través de esta interfaz, los usuarios pueden efectuar operaciones de creación, lectura, actualización y eliminación de tópicos. En el argot informático, este conjunto de operaciones se conoce como CRUD (Create, Read, Update, Delete).
Descripción del Proyecto
El Foro Alura se erige como un espacio donde los estudiantes de la plataforma Alura pueden plantear interrogantes y debatir asuntos vinculados a los cursos ofrecidos. El foro impulsa la cooperación entre estudiantes, profesores y moderadores, facilitando así el intercambio de conocimientos y la resolución de dudas.
El propósito subyacente de este proyecto es replicar el funcionamiento del foro en lo que respecta al backend. Esto engloba la confección de una API REST que administrará la creación, recuperación, actualización y eliminación de tópicos. Asimismo, se aplicarán validaciones siguiendo las reglas de negocio y se empleará una base de datos para la persistencia de la información.
Tecnologías Utilizadas
• Spring Framework: Este proyecto recurre al framework Spring para el desarrollo de la API REST. Spring brinda una variada panoplia de herramientas y bibliotecas que simplifican el desarrollo de aplicaciones Java. • Base de Datos: Se hace uso de una base de datos en mysql para el almacenamiento y la gestión de los datos relativos a tópicos y usuarios. • Insomniac: Para efectuar consultas HTTP mediante la API, se ha incorporado la biblioteca Insomniac, que agiliza la comunicación con el backend.
Funcionalidades
La API REST concebida en este proyecto pone a disposición de los usuarios las siguientes funcionalidades:
1.	Creación de un nuevo tópico: Los usuarios tienen la capacidad de generar nuevos tópicos, indicando aspectos tales como el título, el contenido y el autor.
2.	Listado de todos los tópicos creados: Se brinda acceso a una lista que abarca todos los tópicos existentes en el sistema.
3.	Visualización de un tópico específico: Los usuarios tienen la posibilidad de obtener detalles minuciosos sobre un tópico concreto al proporcionar su identificador único.
4.	Actualización de un tópico: Se permite modificar un tópico preexistente, incluyendo cambios en el título y el contenido.
5.	Eliminación de un tópico: Los usuarios pueden suprimir un tópico que ya exista en el sistema.
Configuración y Uso
A continuación, se detallan los pasos fundamentales para configurar y emplear este proyecto:
1.	Clonar el Repositorio: Clone este repositorio en su máquina local utilizando el siguiente comando:
bashCopy code
git clone https://github.com/tu-usuario/Challenge_foro_alura.git 
2.	Configurar la Base de Datos: En primer lugar, ejecute el programa para crear la base de datos y las tablas correspondientes. Una vez creada la base de datos, acceda a ella a través de su administrador de bases de datos preferido y diríjase a la tabla de usuarios. A continuación, deberá crear un usuario mediante la opción de inserción:

•	Elija un nombre de usuario a su gusto; en mi caso, empleé "Marco".
•	Para la contraseña, utilice el sitio web https://www.browserling.com/tools/bcrypt para encriptarla. Ingrese su contraseña en el campo "Password" y haga clic en "Bcrypt" para generar el resultado encriptado. Luego, copie dicho resultado y péguelo en el campo de contraseña de la tabla de usuarios que está creando.
 
En el campo de correo electrónico, puede proporcionar el que prefiera, y en el campo "activo", ingrese el número 1 para indicar que el usuario está activo.
 

3.	Ejecutar la Aplicación: Inicie la aplicación Spring para poner en marcha la API REST. 

4.	Realizar Consultas HTTP: Utilice Insomniac u otra herramienta similar para llevar a cabo consultas HTTP en las rutas implementadas por la API.


A modo de ejemplo, creemos un curso, un tópico y una respuesta. En primer lugar, descargue el archivo de Insomniac que contiene las consultas HTTP y, a continuación, impórtelo en Insomniac.
Ahora, realicemos una consulta de inicio de sesión de usuario y complete el JSON con su nombre de usuario y contraseña sin encriptar. Luego, haga clic en "Send" para obtener un token, que deberá copiar. Este token deberá pegarse en cada solicitud que desee realizar, en la sección "Auth" bajo "Bearer Token".
 
A continuación, en el JSON, podemos crear un curso indicando el nombre y la categoría. Haga clic en el botón "Send" y debería obtener una respuesta con el código 201.




Ese token lo deberas pegar en cada petición que se quiera hacer, en la parte de Auth, Bearer Token y pegar el token en “Token”
 
 

Si examinamos la base de datos, veremos la lista de cursos en el servidor. Si lo desea, puede crear más cursos a su discreción.
Finalmente, procedamos a crear un tópico en el curso de C++.



Primero deberás descargar el archivo de insomiac que contendrá las consultas http
Importarlo en Insomiac y listo.
 
Dar click en botón send y debería aparecer algo asi con el código 201
 




Si revisamos la base de datos:
 
Vamos a listar los cursos en el servidor, si es de tu preferencia puedes crear más cursos
 

Ahora vamos a crear un tópico en el curso de java
 
Esto concluye la demostración. Si desea explorar todas las peticiones posibles, puede hacerlo siguiendo el mismo procedimiento. Le invitamos a crear una respuesta para un tópico y a explorar los datos mediante los métodos GET, PUT, DELETE y POST.

Contribución
Si desea contribuir al desarrollo de este proyecto, lo animamos a enviar solicitudes de extracción (pull requests) y a participar en el proceso de desarrollo.

