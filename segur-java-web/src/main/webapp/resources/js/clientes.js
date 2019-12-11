var usuario = {
		
		/**
		 *  persistir usuario, llamada persistir cliente
		 *
		 *  @method usuario.save
		 *
		 *  @param {object} usuarioForm : formulario datos usuario
		 *  @param {object} clienteForm : formulario datos cliente
		 *  @param {dom object} $contForm : contenedor formulario de registro
		 */
		save : function(usuarioForm, clienteForm, $contForm) {
			 console.log("peticion AJAX POST " + usuarioForm);
			 $.ajax({
			        type: "POST",
			        contentType: "application/json",
			        url: urlUsuario,
			        data: usuarioForm,
			        dataType: 'json',
			        success: function (data) {
			        	clienteForm.idUsuario = data.id;
			        	clientes.save(JSON.stringify(clienteForm), $contForm);
			        },
			        error: function (e) {
			        	clientes.error(e);
			        }
			    });
			 
		}
		
		
};

var clientes = {
		
		/**
		 *  persistir cliente
		 *
		 *  @method clientes.save
		 *
		 *  @param {object} clienteForm : formulario datos cliente
		 */
		save : function(clienteForm, $contForm) {
			 console.log("peticion AJAX POST " + urlClientes);
			 
			 $.ajax({
				 	type: "POST",
			        contentType: "application/json",
			        url: urlClientes,
			        data: clienteForm,
			        dataType: 'json',
			        success: function (data) {
			        	data.message = "Registro finalizado. Ahora pueden entrar en tu zona de cliente."
			        	clientes.confirm(data);
			        	$contForm.hide();
			        },
			        error: function (e) {
			        	debugger;
			        	clientes.error(e);
			        }
			    });
			 
		 },
		 redirect : function() {
			window.setTimeout(function() {
        	    window.location.href = urlLogin;
        	}, 4000);
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
	 
	 var $contForm = $("#containerRegistro");
	
	 $("#actionRegistro").click(function() {
		 $contForm.show();
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
		 usuario.save(JSON.stringify(usuarioForm), clienteForm, $contForm);
			
	});
	 
	 
	
});