# monolitoBlogLectores

pasos ara poder correr el proyecto
1. crear base de datos en MySQL con el nombre de db_springboot
2. Descargar el proyecto.
3. Cambiar el usuario y contrsenia del archivo (application.properties) que se encuentra ubicado
/spring-boot-data-jpa-monolito/src/main/resources/application.properties del proyecto por las
credenciales que tengan de conexion en su base de datos local.
4. Acualizar el proyecto con maven update, si esta en STS precionar (Alt + F5) para la actualizacion.
5. Ejecutar el proyecto (Spring boot app).
6. el diagrama de base de datos se encuentra en el root del proyecto.
7. usuarios para acceder a la aplicacion en ejecucion son:
usuario: triston
password: 12345
Tipo de usuario: ROLE_USER

usuario: admin
password: 12345
Tipo de usuario: ROLE_USER, ROLE_ADMIN