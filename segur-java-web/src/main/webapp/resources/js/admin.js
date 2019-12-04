clientes = {
			urlClientes : "http://localhost:9000/clientes/clientes",
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
		        $("divmessageKO").removeClass("hidden");
				$("span#errorMessage").html("Error: " + e.status + " " + e.statusText);
		     }

};

$(function(){
	
	 debugger;
	
	 clientes.listar();
	 
	 $("#altaAction").click(function(){
			$("div.alert").addClass("hidden");
			var clienteForm = {
							"idreserva":0,
							"hotel": $("#hoteles").val(),
							"vuelo": $("#vuelos").val(),
							"nombre": $("#nombre").val(),
							"dni": $("#dni").val(),
							"totalPersonas": $("#totalPersonas").val()
						
			};
			
			viaje.save(JSON.stringify(reservaForm));
			
	});
	 
	 
	
});