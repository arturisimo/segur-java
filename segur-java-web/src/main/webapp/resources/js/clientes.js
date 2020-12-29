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
				
				sensor.list(c.id, $table);
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
		 	type: "PUT",
	        contentType: "application/json",
	        url: urlClientes+"/"+clienteForm.id,
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
	
	sensores : [],
	
	/**
	 *  Listado reactivo de sensores del cliente. 
	 *  ms-servicios es un publicador que emite eventos si la lista de sensores del cliente cambia
	 *  y este metodo es el suscriptor
	 *
	 *  @method sensor.listReactive
	 *
	 *  @param {int} idCliente : id del cliente
	 *  @param {dom object} $table : tabla de sensores
	 */
	list: function(idCliente, $table) {
		
		var body = "";
		$table.find("tbody").html(body);
		var source = new EventSource(urlSensores+"/"+idCliente);	  
		
		source.addEventListener("message", function(flow){
			
			var sF = JSON.parse(flow.data);
			
			if (sensor.sensores.filter(s => s.id === sF.id).length == 0){
				sensor.print(sF, $table);
				sensor.sensores.push(sF);
			} else {
				var old = sensor.sensores.filter(s => s.id === sF.id)[0];
				if (old.estado != sF.estado) {
					$table.find("tbody").find("#sensor_"+sF.id).remove();
					sensor.print(sF, $table);
					sensor.sensores = sensor.sensores.filter(s => s.id != sF.id);
					sensor.sensores.push(sF);
				}
			}
		});
	},
	print : function(s, $table) {
		var body = $table.find("tbody").html();
		var tr = "";
		var classAlarma = "btn-info";
		if (s.estado=='DESACTIVADO') {
			classAlarma = "btn-default";
		} else if (s.estado=='ALARMA') {
			classAlarma = "btn-danger";
		}
			
		tr += "<tr id='sensor_"+s.id+"'><td>"+s.zona+"</td>";
		tr += "<td><a id='estado_"+ s.id + "' title='"+s.estado+"' href='#' class='btn "+classAlarma+" estadoAction'><span class='glyphicon glyphicon-bell'></span></a></td>";
		tr += "<td><a id='delete_"+ s.id + "' href='#' class='btn btn-default eliminarAction'><span class='glyphicon glyphicon-remove'></span></a></td></tr>";
		$table.find("tbody").html(body+tr);
		sensor.addEvents($table);
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
			sensor.delete(idSensor, $(this));
		});
		
		$table.find(".estadoAction").click(function(){ 
			var idSensor = this.id.split("_")[1];
			sensor.change(idSensor);
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
	save : function(sensorForm) {
		console.log("peticion AJAX POST " +  urlSensores);
		
		 $.ajax({
		 	type: "POST",
		 	contentType: "application/json",
	        url: urlSensores+"/",
	        data: sensorForm,
	        dataType: 'json',
	        crossDomain: true,
	        success: function (data) {
	        	sensor.confirm(data);	       
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
	delete : function(idSensor, $a) {
		 console.log("peticion AJAX DELETE " + urlSensores+"/"+idSensor);
		 $.ajax({
		 	type: "DELETE",
	        contentType: "application/json",
	        url: urlSensores+"/"+idSensor,
	        success: function (data) {
	        	sensor.confirm(data);
	        	$a.parents("tr").remove();
	        	sensor.sensores = sensor.sensores.filter(s => s.id != idSensor);	        	
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
	change : function(idSensor) {
		 console.log("peticion AJAX PUT " +  urlSensores+"/status/"+idSensor);
		 $.ajax({
		 	type: "PUT",
	        url: urlSensores+"/status/"+idSensor,
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
		};
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
		};
		sensor.save(JSON.stringify(sensorForm), $("table"));
		$("#listSensorContainer").show();
		$("#formSensorContainer").hide();
	}); 
	
});