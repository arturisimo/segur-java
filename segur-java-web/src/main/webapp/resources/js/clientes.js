clientes = {
			urlClientes : "http://localhost:9000/clientes/clientes",
			urlUsuario : "http://localhost:8080/segur-java-web/alta-usuario",
			listar : function() {
					console.log("peticion AJAX GET " +  clientes.urlClientes);
					$.get(clientes.urlClientes, function(data,status){
						var body = "";
						$.each(data,function(i,cliente) {
							body += "<tr><td>"+cliente.nombre+"</td><td>"+cliente.email+"</td><td>"+cliente.dni+"</td><td>"+cliente.cuenta+"</td><td>"+cliente.direccion+"</td><td>"+cliente.policia+"</td></tr>";
						});
						$("table tbody").html(body)
					}).fail(function(e) {
						clientes.error(e);
					});
			},
			saveUsuario : function(usuarioForm) {
				debugger;
				 console.log("peticion AJAX POST " + usuarioForm);
				 $.ajax({
				        type: "POST",
				        contentType: "application/json",
				        url: clientes.urlUsuario,
				        data: usuarioForm,
				        dataType: 'json',
				        success: function (data) {
				        	debugger;
				        	console.log("ei");
				        	var clienteForm = {
									"nombre": $("#nombre").val(),
									"email": $("#email").val(),
									"idUsuario": data.id
							};
				        	clientes.save(JSON.stringify(clienteForm));
				        },
				        error: function (e) {
				        	clientes.error(e);
				        }
				    });
				 
			},
			save : function(clienteForm) {
				 console.log("peticion AJAX POST " + reservaForm);
				 $.ajax({
					 	type: "POST",
				        contentType: "application/json",
				        url: clientes.urlClientes,
				        data: clienteForm,
				        dataType: 'json',
				        success: function (data) {
				        	clientes.confirm(data);
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
		        $("div#messageKO").removeClass("hidden");
				$("span#errorMessage").html("Error: " + e.status + " " + e.statusText);
		     }

};

$(function(){
	
	 clientes.listar();
	 
	 $("#actionRegistro").click(function() {
		  $("#containerRegistro").toggleClass("hidden");
	 });
	 
	 
	 $("#altaAction").click(function(){
			$("div.alert").addClass("hidden");
			var usuarioForm = {
					"usuario" : $("#usuario").val(),
					"password": $("#password").val(),
					"enabled": true
			}
			clientes.saveUsuario(JSON.stringify(usuarioForm));
			
	});
	 
	 
	
});