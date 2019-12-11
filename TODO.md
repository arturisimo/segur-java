## Proyecto para la gestión y control de alarmas

**Motivación**

La empresa Segurjava se dedica a la instalación de alarmas en domicilios y oficinas y quiere ofrecer una aplicación Web a sus clientes que les permita visualizar en tiempo real el estado de los sensores de alarma de su local, así como activar y desactivar los mismos a distancia.
Cada sensor dispone de una electrónica que se comunica con un microservicio del que obtiene información sobre si dicho sensor debe estar o no activado. Además, cuando el sensor detecta un salto de alarma, enviará al microservicio una petición indicando esta circunstancia, a fin de que el servicio registre en una base de datos remota dicho salto. El salto de alarma registra la fecha y hora en la que se ha producido, además de la identificación del sensor.

La base de datos refleja en cada momento si el sensor está o no activado y, en caso de estarlo, si se encuentra en modo detección (se ha producido un salto de alarma) o en modo normal (no se ha producido salto de alarma).

**Objetivo**

Se deberá desarrollar una aplicación Web que permita gestionar el sistema de alarmas, tanto a los clientes como a los administradores de la empresa. En este sentido, además del microservicio de gestión del sensor, se deberán desarrollar dos módulos: cliente y administrador.

**Módulo cliente**

Cuando el cliente accede a la aplicación, lo primero que tendrá que hacer es autenticarse en la misma. Una vez hecho, le aparecerá el panel de control con las opciones disponibles, que son:

* Visualización del sistema. Le aparecerá una página en la que se mostrará el estado actual de cada sensor (activo o inactivo) y desde dicha página el usuario podrá cambiar el estado de cualquiera de ellos. Aquellos sensores que estén activos mostrarán en tiempo real la situación de detección, de modo que si mientras el usuario está conectado a la página se produce una detección de salto de alarma en un sensor, se le informará visualmente al usuario.
* Contratar nuevo sensor/quitar sensor. Mediante esta opción el usuario solicitará la instalación de un nuevo sensor en su vivienda, se le pedirá al usuario la parte de su vivienda/oficina donde quiera que se instale el sensor y desde ese momento ya contará con él. También existirá la opción de quitar un sensor.
* Baja del sistema. Mediante esta opción el usuario se dará de baja en el sistema. La baja no borra los datos del cliente, simplemente lo inhabilita.

**Módulo administrador**

* Alta de clientes. El administrador podrá dar de alta nuevos clientes en el sistema., aunque si el cliente ya estuvo antes dado de alta, simplemente lo habilitará. Además de los datos personales del cliente (nombre, dirección, email, dni, número de cuenta bancaria, usuario y contraseña), se introducirán en el sistema los datos de los sensores contratados. Opcionalmente, los clientes pueden optar por contratar "aviso policía", que generará un aviso a la policía cuando un sensor detecte salto de alarma. Las contraseñas deben almacenarse encriptadas en la base de datos .

* Informes. El administrador podrá obtener del sistema dos tipos de informes: Informe de actividad de usuario, que a partir del dni mostrará los saltos de alarma que han tenido lugar para ese usuario. Y, por otro lado, el informe de actividad temporal, que a partir de un rango de fechas nos muestra todos los saltos de alarma producidas durante este espacio de tiempo (nombre cliente, fecha, dirección y sensor).


**Funcionamiento aviso a policía.**

En el servidor de la policía existe un microservicio encargado de recibir los avisos desde los clientes. Este servicio recibe un objeto JSON con el siguiente formato:
	
	{"direccion":"direccion_cliente", "fecha":"10-03-2013 16:20"}

Este servicio simplemente lleva un registro de log de las alarmas producidas.

**Documentación final**

Se realizará una breve memoria explicativa del funcionamiento de la aplicación y las tecnologías utilizadas en su implementación, así como un diagrama de clases UML