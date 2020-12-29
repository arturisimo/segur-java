var usuario = {
		/**
		 *  persistir usuario, llamada persistir cliente
		 *
		 *  @method usuario.save
		 *
		 *  @param {object} usuarioForm : formulario datos usuario
		 *  @param {object} clienteForm : formulario datos cliente
		 *  @param {dom object} $table : tabla de clientes
		 */
		save : function(usuarioForm, clienteForm, $table) {
			 console.log("peticion AJAX POST " + usuarioForm);
			 $.ajax({
			        type: "POST",
			        contentType: "application/json",
			        url: urlUsuario,
			        data: usuarioForm,
			        dataType: 'json',
			        success: function (data) {
			        	clienteForm.idUsuario = data.id;
			        	clientes.save(JSON.stringify(clienteForm), $table);
			        },
			        error: function (e) {
			        	clientes.error(e);
			        }
			    });
			 
		},
		/**
		 *  Eliminar usuario
		 *
		 *  @method usuario.deleteUser
		 *
		 *  @param {integer} id : id usuario
		 *  @param {dom object} $contForm : contenedor formulario de registro
		 */
		deleteUser : function(id, $contForm) {
			 console.log("peticion AJAX DELETE " + id);
			 $.ajax({
			        type: "DELETE",
			        url: urlBase+"usuario/"+id,
			        success: function (data) {
			        	var e = {"status": "KO", "statusText":"Fallo en el registro "+data};
			        	clientes.error(e);
			        },
			        error: function (e) {
			        	clientes.error(e);
			        }
			    });
			 
		}
}
var clientes = {
		
		/**
		 *  Listado de clientes
		 *
		 *  @method clientes.list
		 *
		 *  @param {dom object} $table : tabla de clientes
		 */
		list : function($table, $contForm) {
				$table.find("tbody").html("");
				console.log("peticion AJAX GET " +  urlClientes);
				$.get(urlClientes, function(data, status){
					var body = "";
					$.each(data,function(i,cliente) {
						body += "<tr id='cliente_"+cliente.id +"'>";
						body += "<td>"+cliente.nombre+"</td><td>"+cliente.email+"</td><td>"+cliente.dni+"</td><td>"+cliente.cuenta+"</td><td>"+cliente.direccion+"</td>";
						body += "<td>"+cliente.policia+"</td><td><a id='cliente_"+cliente.id +"' href='#' class='alarmasAction'>ver alarmas</a></td>";
						body += "<td><a href='#' class='editarAction'>editar</a></td>";
						body += "</tr>";
					});
					$table.find("tbody").html(body);
					clientes.addEvents($table, $contForm);
				}).fail(function(e) {
					clientes.error(e);
				});
				
		},
		/**
		 *  eventos del listado de clientes
		 *
		 *  @method clientes.addEvents
		 *
		 *  @param {dom object} $table : tabla de clientes
		 */
		addEvents : function($table, $contForm) {
			$table.find(".alarmasAction").click(function(){
				//var idCliente = this.id.split("_")[1];
				var $trCliente = $(this).parents("tr");
				var idCliente = $trCliente.attr("id").split("_")[1];
				sensor.alarmas(idCliente, $trCliente);
			});
			$table.find(".editarAction").click(function(){
				//var idCliente = this.id.split("_")[1];
				var $trCliente = $(this).parents("tr");
				var idCliente = $trCliente.attr("id").split("_")[1];
				$table.parents("div#containerListCliente").hide();
				clientes.edit(idCliente, $contForm);
			});
			
		},
		edit : function(idCliente, $contForm) {
			$.get(urlClientes+"/"+idCliente, function(data, status){
				$contForm.find(".noedit").hide();
				$contForm.find("#idCliente").val(data.id);
				$contForm.find("#nombre").val(data.nombre);
				$contForm.find("#email").val(data.email);
				$contForm.find("#dni").val(data.dni);
				$contForm.find("#cuenta").val(data.cuenta);
				$contForm.find("#direccion").val(data.direccion);
				$contForm.find("#direccion").val(data.direccion);
				if (data.policia)
					$contForm.find("#policia").attr("checked","checked");
				$contForm.show();
			}).fail(function(e) {
				clientes.error(e);
			});
		},
		/**
		 *  persistir cliente
		 *
		 *  @method clientes.save
		 *
		 *  @param {object} clienteForm : formulario datos cliente
		 */
		save : function(clienteForm, $table) {
			 console.log("peticion AJAX POST " + urlClientes);
			 
			 $.ajax({
				 	type: "POST",
			        contentType: "application/json",
			        url: urlClientes+"/",
			        data: clienteForm,
			        dataType: 'json',
			        success: function (feedback) {
			        	feedback.message = "Registro finalizado. Ahora pueden entrar en tu zona de cliente."
			        	clientes.confirm(feedback);
			        	clientes.list($table);
			        },
			        error: function (e) {
			        	var idUsuario = JSON.parse(clienteForm).idUsuario;
						usuario.deleteUser(idUsuario, $contForm);
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
		/**
		 *  Listado de sensores y alarmas por cliente
		 *
		 *  @method clientes.alarmas
		 *
		 *  @param {int} idCliente : id del cliente
		 *  @param {dom object} $trCliente : fila del cliente
		 */
		alarmas : function(idCliente, $trCliente) {
			if ($trCliente.next().hasClass("sensorCliente")) {
				$trCliente.next().remove();
			} else {
				$.get(urlSensores + "/sensores-json/"+ idCliente, function(data,status){
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
	
	 var $contList = $("#containerListCliente");
	 var $table = $contList.find("table");
	 var $contForm = $("#containerFormCliente");
	 var $form = $contForm.find("form");
	 
	 clientes.list($table, $contForm);
	 
	 $("#formClienteAction").click(function(){
		 $contList.hide();
		 $contForm.find(".noedit").show();
		 $.each($contForm.find("input"),function(i,input) {
			 $(input).val("");
		 });
		 $contForm.show();
	 });
	 $("#listClienteAction").click(function(){
		 $contList.show();
		 $contForm.hide();
	 });
	 
	 $("#saveClienteAction").click(function(){
			$("div.alert").addClass("hidden");
			
			var usuarioForm = {
					"usuario": $form.find("#usuario").val(),
					"password": $form.find("#password").val(),
					"enabled" : true
			};
			
			var clienteForm = {
					"id" : 0,
					"nombre": $form.find("#nombre").val(),
					"email": $form.find("#email").val(),
					"dni": $form.find("#dni").val(),
					"cuenta": $form.find("#cuenta").val(),
					"direccion": $form.find("#direccion").val(),
					"estado": true,
					"policia": $form.find("#policia").prop("checked"),
			}
			
			usuario.save(JSON.stringify(usuarioForm), clienteForm, $table);
			$form[0].reset();
			$contForm.hide();
			$contList.show();
			
	 }); 
	
});