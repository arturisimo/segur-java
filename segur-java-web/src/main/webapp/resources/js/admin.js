var usuario = {
		save : function(usuarioForm, clienteForm) {
			 console.log("peticion AJAX POST " + usuarioForm);
			 $.ajax({
			        type: "POST",
			        contentType: "application/json",
			        url: urlUsuario,
			        data: usuarioForm,
			        dataType: 'json',
			        success: function (data) {
			        	clienteForm.idUsuario = data.id;
			        	clientes.save(JSON.stringify(clienteForm));
			        },
			        error: function (e) {
			        	clientes.error(e);
			        }
			    });
			 
		}
}
var clientes = {
		list : function($table) {
				console.log("peticion AJAX GET " +  urlClientes);
				$.get(urlClientes, function(data,status){
					var body = "";
					$.each(data,function(i,cliente) {
						body += "<tr><td>"+cliente.nombre+"</td><td>"+cliente.email+"</td><td>"+cliente.dni+"</td><td>"+cliente.cuenta+"</td><td>"+cliente.direccion+"</td>";
						body += "<td>"+cliente.policia+"</td><td><a id='cliente_"+cliente.id +"' href='#' class='alarmasAction'>ver alarmas</a></td></tr>";
					});
					$table.find("tbody").html(body);
					clientes.addEvents($table);
				}).fail(function(e) {
					clientes.error(e);
				});
				
		},
		addEvents : function($table) {
			$table.find(".alarmasAction").click(function(){
				var idCliente = this.id.split("_")[1];
				var $trCliente = $(this).parents("tr");
				sensor.getAlarmas(idCliente, $trCliente);
			});
			
		},
		save : function(clienteForm) {
			 console.log("peticion AJAX POST " + clienteForm);
			 $.ajax({
			        type: "POST",
			        contentType: "application/json",
			        url: urlClientes,
			        data: clienteForm,
			        dataType: 'json',
			        success: function (data) {
			        	 clientes.list();
			        },
			        error: function (e) {
			        	clientes.error(e);
			        }
			    });
			 
		 },
		 confirm : function(data) {
			 $("div#messageOK").removeClass("hidden");
			 $("span#message").html(data.message)
	     },
		 error : function(e) {
	        console.log(e);
	        $("divmessageKO").removeClass("hidden");
			$("span#errorMessage").html("Error: " + e.status + " " + e.statusText);
	     }

};


var sensor = {
		getAlarmas : function(idCliente, $trCliente) {
			debugger;
			if ($trCliente.next().hasClass("sensorCliente")) {
				$trCliente.next().remove();
			} else {
				$.get(urlSensores + "-json/"+ idCliente, function(data,status){
					var body = "";
					$.each(data,function(i,sensor) {
						body += "<tr class='sensorCliente' id='sensor_"+idCliente+"'><td><strong>zona:</strong></td><td>"+sensor.zona+"</td><td><strong>alarmas:</strong></td>";
						body += "<td colspan='3'><ul class='alarmas'>";
						$.each(sensor.alarmas,function(i,alarma) {
							body += "<li class='alarma_"+sensor.id+"'>"+alarma.fecha+"</li>";
						});
						body += "</ul>";
						body += "</td></tr>";
					});
					$trCliente.after(body);	
				}).fail(function(e) {
					clientes.error(e);
				});
			}
		}
};

$(function(){
		
	 clientes.list($("table"));
	 
	 $("#formClienteAction").click(function(){
		 $("#containerListCliente").hide();
		 $("#containerFormCliente").show();
	 });
	 $("#listClienteAction").click(function(){
		 $("#containerListCliente").show();
		 $("#containerFormCliente").hide();
	 });
	 
	 $("#saveClienteAction").click(function(){
			$("div.alert").addClass("hidden");
			
			var usuarioForm = {
					"usuario": $("#usuario").val(),
					"password": $("#password").val(),
					"enabled" : true
			};
			
			var clienteForm = {
					"id" : 0,
					"nombre": $("#nombre").val(),
					"email": $("#email").val(),
					"dni": $("#dni").val(),
					"cuenta": $("#cuenta").val(),
					"direccion": $("#direccion").val(),
					"estado": true,
					"policia": $("#policia").prop("checked"),
			}
			
			usuario.save(JSON.stringify(usuarioForm), clienteForm);
			$("#containerFormCliente").find("form")[0].reset();
			$("#containerFormCliente").hide();
			$("#containerListCliente").show();
			
			//cliente.save(JSON.stringify(clienteForm));
			
	 }); 
	
});