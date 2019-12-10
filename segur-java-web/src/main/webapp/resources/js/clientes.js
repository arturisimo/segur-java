var usuario = {
		
		save : function(usuarioForm, clienteForm) {
			debugger;
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
		
		
};

var clientes = {
	
			save : function(clienteForm) {
				 console.log("peticion AJAX POST " + urlClientes);
				 
				 $.ajax({
					 	type: "POST",
				        contentType: "application/json",
				        url: urlClientes,
				        data: clienteForm,
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
			 var clienteForm = {
					"nombre": $("#nombre").val(),
					"email": $("#email").val(),
					"estado": true,
					"policia": false
		 };
		 usuario.save(JSON.stringify(usuarioForm), clienteForm);
			
	});
	 
	 
	
});