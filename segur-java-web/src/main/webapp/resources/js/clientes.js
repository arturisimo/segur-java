clientes = {
			urlClientes : "http://localhost:9000/clientes/clientes",
			urlUsuario : "http://localhost:8080/segur-java-web/alta-usuario",
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
				        	clientes.save(data.id);
				        },
				        error: function (e) {
				        	clientes.error(e);
				        }
				    });
				 
			},
			save : function(idUsuario) {
				 console.log("peticion AJAX POST " + clienteForm);
				 
				 var clienteForm = {
							"nombre": $("#nombre").val(),
							"email": $("#email").val(),
							"estado": true,
							"policia": false,
							"idUsuario":idUsuario
				 };
				 
				 console.log(JSON.stringify(clienteForm));
				 
				 $.ajax({
					 	type: "POST",
				        contentType: "application/json",
				        url: clientes.urlClientes,
				        data: JSON.stringify(clienteForm),
				        dataType: 'json',
				        success: function (data) {
				        	debugger;
				        	clientes.confirm(data);
				        	$("#containerRegistro").toggleClass("hidden");
				        	window.setTimeout(function() {
				        	    window.location.href = 'http://localhost:8080/segur-java-web/cliente';
				        	}, 5000);
				        },
				        error: function (e) {
				        	debugger;
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
	
	debugger;
	 
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