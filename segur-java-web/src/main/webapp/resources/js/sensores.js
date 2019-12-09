var cliente = {
		
	/**
	 *  Datos del cliente y llamada a los sensores del cliente
	 *
	 *  @method client.load
	 *
	 *  @param {string} nombreUsuario : usuario del cliente logado
	 *  @param {dom object} $formCliente : formulario datos cliente
	 *  @param {dom object} $table : tabla de sensores
	 */
	load : function(nombreUsuario, $formCliente, $table) {
		var url = urlUsuario+"/"+encodeURIComponent(nombreUsuario);
		console.log("peticion AJAX GET " +  url);
		$.get(url, function(usuario, status){
			sensor.idUsuario = usuario.id;
			console.log("peticion AJAX GET " +  urlClientes+"/usuario/"+usuario.id);
			$.get(urlClientes+"/usuario/"+usuario.id, function(c, status){
				
				sensor.idCliente = c.id;
				
				//carga formulario
				$formCliente.find("#idCliente").val(c.id);
				$formCliente.find("#nombre").val(c.nombre);
				$formCliente.find("#email").val(c.email);
				$formCliente.find("#dni").val(c.dni);
				$formCliente.find("#cuenta").val(c.cuenta);
				$formCliente.find("#direccion").val(c.direccion);
				if (c.policia)
					$formCliente.find("#policia").attr("checked","checked");
				
				sensor.list(c.id,$table);
			}).fail(function(e) {
				sensor.error(e);
			});
		}).fail(function(e) {
			sensor.error(e);
		});
	},
	/**
	 *  actualizacion datos clientes
	 *
	 *  @method client.save
	 *
	 *  @param {object} clienteForm    :  datos clientes
	 */
	save : function(clienteForm) {
		 console.log("peticion AJAX POST " + clienteForm);
		 $.ajax({
		 	type: "POST",
	        contentType: "application/json",
	        url: urlClientes,
	        data: clienteForm,
	        dataType: 'json',
	        success: function (data) {
	        	sensor.confirm(data);
	        },
	        error: function (e) {
	        	sensor.error(e);
	        }
	    });
		 
	}
};

var sensor = {
		
	/**
	 *  Listado de sensores del cliente
	 *
	 *  @method sensor.list
	 *
	 *  @param {int} idCliente : id del cliente
	 *  @param {dom object} $table : tabla de sensores
	 */
	list : function(idCliente, $table) {
		console.log("peticion AJAX GET " +  urlSensores+"/"+idCliente);
		$.get(urlSensores+"/"+idCliente, function(data,status){
			var body = "";
			$table.find("tbody").html(body);
			$.each(data,function(i,sensor) {
				
				var classAlarma = "btn-info";
				if (sensor.estado=='DESACTIVADO') {
					classAlarma = "btn-default";
				} else if (sensor.estado=='ALARMA') {
					classAlarma = "btn-danger";
				}
				
				body += "<tr><td>"+sensor.zona+"</td>";
				body += "<td><a id='estado_"+ sensor.id + "' title='"+sensor.estado+"' href='#' class='btn "+classAlarma+" estadoAction'><span class='glyphicon glyphicon-bell'></span></a></td>";
				body += "<td><a id='delete_"+ sensor.id + "' href='#' class='btn btn-default eliminarAction'><span class='glyphicon glyphicon-remove'></span></a></td>";
			});
			$table.find("tbody").html(body);
			sensor.addEvents($table);
		}).fail(function(e) {
			sensor.error(e);
		});
	},
	/**
	 *  Eventos componentes de la lista sensores
	 *
	 *  @method sensor.addEvents
	 *  
	 *  @param {dom object} $table : tabla de sensores
	 */
	addEvents : function($table) {
		$table.find(".eliminarAction").click(function(){ 
			var idSensor = this.id.split("_")[1];
			sensor.delete(idSensor, $("table"));
		});
		
		$table.find(".estadoAction").click(function(){ 
			var idSensor = this.id.split("_")[1];
			sensor.change(idSensor, $("table"));
		});
	},
	/**
	 *  Alta de un nuevo sensor
	 *  
	 *  @method sensor.save
	 *  
	 *  @param {object} sensorForm : datos sensor
	 *  @param {dom object} $table : tabla de sensores
	 */
	save : function(sensorForm, $table) {
		console.log("peticion AJAX POST " +  urlSensores);
		 $.ajax({
		 	type: "POST",
	        contentType: "application/json",
	        url: urlSensores,
	        data: sensorForm,
	        dataType: 'json',
	        success: function (data) {
	        	sensor.confirm(data);
	        	sensor.list(sensor.idCliente,$table);
	        },
	        error: function (e) {
	        	sensor.error(e);
	        }
	    });
	},
	/**
	 *  Elimina sensor
	 *  
	 *  @method sensor.delete
	 *  
	 *  @param {int} idSensor : id sensor
	 *  @param {dom object} $table : tabla de sensores
	 */
	delete : function(idSensor,$table) {
		 console.log("peticion AJAX DELETE " + urlSensores+"/"+idSensor);
		 $.ajax({
		 	type: "DELETE",
	        contentType: "application/json",
	        url: urlSensores+"/"+idSensor,
	        success: function (data) {
	        	sensor.confirm(data);
	        	sensor.list(sensor.idCliente,$table);
	        },
	        error: function (e) {
	        	sensor.error(e);
	        }
	    });
		
	},
	/**
	 *  activar/desactivar sensor
	 *  
	 *  @method sensor.change
	 *  
	 *  @param {int} idSensor : id sensor
	 *  @param {dom object} $table : tabla de sensores
	 */
	change : function(idSensor,$table) {
		 console.log("peticion AJAX PUT " +  urlSensores+"/status/"+idSensor);
		 $.ajax({
		 	type: "PUT",
	        url: urlSensores+"/status/"+idSensor,
	        success: function (data) {
	        	sensor.confirm(data);
	        	sensor.list(sensor.idCliente,$table);
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

	 cliente.load(nombreUsuario, $("#formCliente"), $("table")); 
	 
	 $("#editClienteLink").click(function() {
		  $("#containerEditCliente").toggleClass("hidden");
	 });
	 
	 $("#editClienteAction").click(function(){
		 $("#formCliente input").removeAttr('disabled');
		 $("#editClienteAction").hide();
		 $("#saveClienteAction").show();
	 });
	 
	 $("#saveClienteAction").click(function(){
		$("div.alert").addClass("hidden");
		
		var clienteForm = {
				"id" : sensor.idCliente,
				"idUsuario" : sensor.idUsuario,
				"nombre": $("#nombre").val(),
				"email": $("#email").val(),
				"dni": $("#dni").val(),
				"cuenta": $("#cuenta").val(),
				"direccion": $("#direccion").val(),
				"estado": true,
				"policia": $("#policia").prop("checked"),
		}
		cliente.save(JSON.stringify(clienteForm));
		$("#formCliente input").attr("disabled","disabled")
		$("#editClienteAction").show();
		$("#saveClienteAction").hide();
	});
	
	$("#formSensorAction").click(function(){ 
		$("#listSensorContainer").hide();
		$("#formSensorContainer").show();
	});
	
	$("#listSensorAction").click(function(){ 
		$("#listSensorContainer").show();
		$("#formSensorContainer").hide();
	}); 
	 
	$("#altaSensorAction").click(function(){
		$("div.alert").addClass("hidden");

		var sensorForm = {
			"idCliente" : sensor.idCliente,
			"zona": $("#zona").val()
		}
		sensor.save(JSON.stringify(sensorForm), $("table"));
		$("#listSensorContainer").show();
		$("#formSensorContainer").hide();
	}); 
	
});