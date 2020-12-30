### segurJava


**Schema de BBDD**

script
	
	CREATE SCHEMA segur_java DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
	

**Configuracion BBDD para la aplicacion segur-java-web**

context.xml
  
    <ResourceLink type="javax.sql.DataSource" name="refSGJ" global="jdbc/alertasds"/>
    
server.xml
  
    <Resource driverClassName="com.mysql.jdbc.Driver" name="jdbc/alertasds" password="root" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/segur_java" username="root"/>


http://localhost:8888/servidor-eureka/default
    

	sudo docker run -p 8888:8888 --name config-server --network="segur_java_network" segur-java/config-server:0.0.1-SNAPSHOT
	sudo docker run -p 8761:8761 --name servidor-eureka --network="segur_java_network" segur-java/eureka-server:0.0.1-SNAPSHOT
	    
	
	sudo docker run --name mssql.local --network="segur_java_network" -e MYSQL_ROOT_PASSWORD=popeye00  -p 52000 mysql
	sudo docker exec -it mssql.local mysql -p
	show databases;
	create databases;
	    
	sudo docker run -p 9001:9001 --name microservicio-policia  --network="segur_java_network" segur-java/microservicio-policia:0.0.1-SNAPSHOT 	    
	sudo docker run -p 8100:8100 --name microservicio-sensores --network="segur_java_network" segur-java/microservicio-sensores:0.0.1-SNAPSHOT
	sudo docker run -p 9002:9002 --name microservicio-clientes --network="segur_java_network" segur-java/microservicio-clientes:0.0.1-SNAPSHOT
	sudo docker run -p 7000:7000 --name zuul-server --network="segur_java_network" segur-java/zuul-server:0.0.1-SNAPSHOT
	
	sudo docker run -p 8080:8080 --name segur-java --network="segur_java_network" segur-java/segur-java-web:0.0.1-SNAPSHOT
	
	sudo docker run -it --publish 8080:8080 -v "/home/arturo/desarrollo/JAVA/Servers/Tomcat v8.5 Server at localhost-config/server.xml":"/usr/local/tomcat/conf/server.xml" -v "/home/arturo/desarrollo/JAVA/Servers/Tomcat v8.5 Server at localhost-config/context.xml":"/usr/local/tomcat/conf/context.xml" --name segur-java --network="segur_java_network" segur-java/segur-java-web:0.0.1-SNAPSHOT
	
	
**Kafka docker**	
	
	# descargar imagen
	docker pull confluent/kafka
	
	# Start Zookeeper and expose port 2181 for use by the host machine
	docker run -d --name zookeeper -p 2181:2181 confluent/zookeeper

	# Start Kafka and expose port 9092 for use by the host machine
	docker run -d --name kafka -p 9092:9092 --link zookeeper:zookeeper confluent/kafka
	
	# nuevo topìc
	docker exec -it zookeeper kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic alarmas

	# listar topis
	docker exec -it zookeeper kafka-topics --list --zookeeper localhost:2181

