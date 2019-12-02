sensores = {
			urlClientes : "http://localhost:9000/clientes/clientes",
}

$(function(){

  
  $("#actionRegistro").click(function() {
	  $("#containerRegistro").toggleClass("hidden");
  });
  
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