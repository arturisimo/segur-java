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
			        	debugger;
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
			list : function() {
					console.log("peticion AJAX GET " +  urlClientes);
					$.get(urlClientes, function(data,status){
						var body = "";
						$.each(data,function(i,cliente) {
							body += "<tr><td>"+cliente.nombre+"</td><td>"+cliente.email+"</td><td>"+cliente.dni+"</td><td>"+cliente.cuenta+"</td><td>"+cliente.direccion+"</td><td>"+cliente.policia+"</td></tr>";
						});
						$("table tbody").html(body)
					}).fail(function(e) {
						clientes.error(e);
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

$(function(){
		
	 clientes.list();
	 
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