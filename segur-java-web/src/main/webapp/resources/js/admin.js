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
		save : function(usuarioForm, clienteForm, $contForm, $table) {
			 console.log("peticion AJAX POST " + usuarioForm);
			 $.ajax({
			        type: "POST",
			        contentType: "application/json",
			        url: urlUsuario,
			        data: usuarioForm,
			        dataType: 'json',
			        success: function (data) {
			        	clienteForm.idUsuario = data.id;
			        	clientes.save(JSON.stringify(clienteForm), $contForm, $table);
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
		deleteUser : function(id) {
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
						body += "<tr id='cliente_"+cliente.id +"' class='"+ (cliente.estado ? '': 'disabled') + "' >";
						body += "<td>"+cliente.nombre+"</td><td>"+cliente.email+"</td><td>"+cliente.dni+"</td><td>"+cliente.cuenta+"</td><td>"+cliente.direccion+"</td>";
						body += "<td><span class='btn btn-default disabled glyphicon "+ (cliente.policia ? "glyphicon-ok" : "glyphicon-unchecked") +"'></span></td>"; 
						body += "<td><span class='btn btn-default disabled glyphicon "+ (cliente.estado ? "glyphicon-ok" : "glyphicon-unchecked") +"'></span></td>";
						body += "<td><a id='cliente_"+cliente.id +"' href='#' class='alarmasAction btn btn-default'><span class='glyphicon glyphicon-bell'></span></a></td>";
						body += "<td><a href='#' class='editarAction btn btn-default'><span class='glyphicon glyphicon-pencil'></span></a></td>";
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
				var $trCliente = $(this).parents("tr");
				var idCliente = $trCliente.attr("id").split("_")[1];
				sensor.alarmas(idCliente, $trCliente);
			});
			$table.find(".editarAction").click(function(){
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
				$contForm.find("#idUsuario").val(data.idUsuario);
				if (data.estado)
					$contForm.find("#estado").attr("checked","checked");
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
		save : function(clienteForm, $contForm, $table) {
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
			        	clientes.list($table, $contForm);
			        },
			        error: function (e) {
			        	var idUsuario = JSON.parse(clienteForm).idUsuario;
						usuario.deleteUser(idUsuario);
			        	clientes.error(e);
			        }
			    });
			 
		},
		/**
		 *  persistir cliente
		 *
		 *  @method clientes.save
		 *
		 *  @param {object} clienteForm : formulario datos cliente
		 */
		saveEdit : function(clienteForm, $contForm, $table) {
			 console.log("peticion AJAX POST " + urlClientes);
			 
			 $.ajax({
				 	type: "PUT",
			        contentType: "application/json",
			        url: urlClientes+"/"+clienteForm.id,
			        data: JSON.stringify(clienteForm),
			        dataType: 'json',
			        success: function (feedback) {
			        	feedback.message = "Se ha modificado correctamente."
			        	clientes.confirm(feedback);
			        	clientes.list($table, $contForm);
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
				$trCliente.siblings(".sensorCliente").remove();
			} else {
				$.get(urlSensores + "/sensores-json/"+ idCliente, function(data,status){
					var body = "";
					debugger;
					if (data.length == 0)
						body += "<tr class='sensorCliente'><td colspan='2' >No hay sensores contratados</td>";
					$.each(data, function(i,sensor) {
						body += "<tr class='sensorCliente' id='sensor_"+sensor.id+"'><td><strong>zona:</strong></td><td>"+sensor.zona+"</td><td><strong>alarmas:</strong></td>";
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
			
			var idCliente = $form.find("#idCliente").val();
			var idUsuario = $form.find("#idUsuario").val();
			idCliente = idCliente == "" ? 0 : idCliente;
			idUsuario = idUsuario == "" ? 0 : idUsuario;
			
			var usuarioForm = {
					"id" : idUsuario,
					"usuario": $form.find("#usuario").val(),
					"password": $form.find("#password").val(),
					"enabled" : true
			};
			
			var clienteForm = {
					"id" : idCliente,
					"idUsuario" : idUsuario,
					"nombre": $form.find("#nombre").val(),
					"email": $form.find("#email").val(),
					"dni": $form.find("#dni").val(),
					"cuenta": $form.find("#cuenta").val(),
					"direccion": $form.find("#direccion").val(),
					"estado": $form.find("#estado").prop("checked"),
					"policia": $form.find("#policia").prop("checked"),
			}
			if (idCliente == 0)
				usuario.save(JSON.stringify(usuarioForm), clienteForm, $contForm, $table);
			else
				clientes.saveEdit(clienteForm, $contForm, $table);
			
			$form[0].reset();
			$contForm.hide();
			$contList.show();
			
	 }); 
	
});