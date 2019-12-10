### segurJava


script
	
	CREATE SCHEMA segur_java2 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
	
	
	INSERT INTO `usuarios` (`usuario`, `password`, `enabled`) VALUES
	('admin', '$2a$10$CJ5p.7NfVg32n1CQ0aqpZuawZ/7eiTPgP.OUieLRPlNGuJpoWJkFu',  1),



context.xml
  
    <ResourceLink type="javax.sql.DataSource" name="refSGJ" global="jdbc/alertasds"/>
    
server.xml
  
    <Resource driverClassName="com.mysql.jdbc.Driver" name="jdbc/alertasds" password="root" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/segur_java" username="root"/>
