sensor = {
			urlClientes : "http://localhost:9000/clientes/clientes",
			urlSensor : "http://localhost:8100/alarmas/sensores",
			urlUsuario : "http://localhost:8080/segur-java-web/usuario",
			getCliente : function(nombreUsuario) {
				var url = sensor.urlUsuario+"/"+encodeURIComponent(nombreUsuario);
				console.log("peticion AJAX GET " +  url);
				$.get(url, function(usuario,status){
					console.log("peticion AJAX GET " +  sensor.urlClientes+"/usuario/"+usuario.id);
					$.get(sensor.urlClientes+"/usuario/"+usuario.id, function(cliente,status){
						sensor.listarSensores(cliente.id);
					}).fail(function(e) {
						sensor.error(e);
					});
				}).fail(function(e) {
					sensor.error(e);
				});
			},
			listarSensores : function(id) {
					console.log("peticion AJAX GET " +  sensor.urlSensor+"/"+id);
					$.get(sensor.urlSensor+"/"+id, function(data,status){
						var body = "";
						$.each(data,function(i,sensor) {
							body += "<tr><td>"+sensor.zona+"</td><td>"+sensor.estadoBean.descripcion+"</td>";
						});
						$("table tbody").html(body)
					}).fail(function(e) {
						sensor.error(e);
					});
			},
			editCliente : function(clienteForm) {
				 console.log("peticion AJAX POST " + clienteForm);
				 $.ajax({
					 	type: "POST",
				        contentType: "application/json",
				        url: sensor.urlClientes,
				        data: clienteForm,
				        dataType: 'json',
				        success: function (data) {
				        	sensor.confirm(data);
				        },
				        error: function (e) {
				        	sensor.error(e);
				        }
				    });
				 
			},
			confirm : function(data) {
				 $("div#messageOK").removeClass("hidden");
				 $("span#message").html(data.message)
		     },
			 error : function(e) {
		        console.log(e);
		        $("div#messageKO").removeClass("hidden");
				$("span#errorMessage").html("Error: " + e.status + " " + e.statusText);
		     }

};

$(function(){

	 sensor.getCliente(nombreUsuario); 
	
	 //sensor.listarSensores(cliente.id);
	 
	 $("#editClienteLink").click(function() {
		  $("#containerEditCliente").toggleClass("hidden");
	 });
	 
	 $("#editClienteAction").click(function(){
			$("div.alert").addClass("hidden");
			
			`id`, `nombre`, `email`, `dni`, `cuenta`, `direccion`, `estado`, `policia`
			
			var clienteForm = {
					"id" : $("#id").val(),
					"nombre": $("#nombre").val(),
					"email": $("#email").val(),
					"cuenta": $("#email").val(),
					"direccion": $("#email").val(),
					"estado": true,
					"policia": $("#policia").val(),
			}
			sensor.editCliente(JSON.stringify(clienteForm));
			
	});
	 
	 
	
});