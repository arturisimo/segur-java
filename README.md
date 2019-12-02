### segurJava


context.xml
  
    <ResourceLink type="javax.sql.DataSource" name="refSGJ" global="jdbc/alertasds"/>
    
server.xml
  
    <Resource driverClassName="com.mysql.jdbc.Driver" name="jdbc/alertasds" password="root" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/segur_java" username="root"/>
