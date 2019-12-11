### segurJava


**Schema de BBDD**

script
	
	CREATE SCHEMA segur_java DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
	

**Configuracion BBDD para la aplicacion segur-java-web**

context.xml
  
    <ResourceLink type="javax.sql.DataSource" name="refSGJ" global="jdbc/alertasds"/>
    
server.xml
  
    <Resource driverClassName="com.mysql.jdbc.Driver" name="jdbc/alertasds" password="root" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/segur_java" username="root"/>
