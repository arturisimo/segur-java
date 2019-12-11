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
		list : function($table) {
				$table.find("tbody").html("");
				console.log("peticion AJAX GET " +  urlClientes);
				$.get(urlClientes, function(data, status){
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
		/**
		 *  eventos del listado de clientes
		 *
		 *  @method clientes.addEvents
		 *
		 *  @param {dom object} $table : tabla de clientes
		 */
		addEvents : function($table) {
			$table.find(".alarmasAction").click(function(){
				var idCliente = this.id.split("_")[1];
				var $trCliente = $(this).parents("tr");
				sensor.alarmas(idCliente, $trCliente);
			});
			
		},
		/**
		 *  persistir cliente
		 *
		 *  @method clientes.save
		 *
		 *  @param {object} clienteForm : formulario datos cliente
		 *  @param {dom object} $table : tabla de clientes
		 */
		save : function(clienteForm, $table) {
			 console.log("peticion AJAX POST " + clienteForm);
			 $.ajax({
			        type: "POST",
			        contentType: "application/json",
			        url: urlClientes,
			        data: clienteForm,
			        dataType: 'json',
			        success: function (data) {
			        	 clientes.list($table);
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
	
	 var $contList = $("#containerListCliente");
	 var $table = $contList.find("table");
	 var $contForm = $("#containerFormCliente");
	 var $form = $contForm.find("form");
	 
	 clientes.list($table);
	 
	 $("#formClienteAction").click(function(){
		 $contList.hide();
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