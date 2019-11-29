$(function(){

  
  $("#listMovs").click(function() {
	  $("#movsContainer").toggleClass("hidden");
	  $("#titularesContainer").addClass("hidden");
	  $("#operacionFormContainer").addClass("hidden");
  });
  
  $("#operar").click(function() {
	  $("#operacionFormContainer").toggleClass("hidden");
	  $("#titularesContainer").addClass("hidden");
	  $("#movsContainer").addClass("hidden");
  });
  
  $("#listTitulares").click(function() {
	  $("#titularesContainer").toggleClass("hidden");
	  $("#operacionFormContainer").addClass("hidden");
	  $("#movsContainer").addClass("hidden");
  });
  
  $("#tipoOperacion").change(function() {
	  var tipo = $(this).val();
	  if (tipo == 'transferencia')
		  $("#cuentaDestinoContainer").removeClass("hidden");  
	  else
		  $("#cuentaDestinoContainer").addClass("hidden");
		  
  });
  

}); 