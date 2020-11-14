function mostrarBuscarProveedor() {
  document.getElementById("buscarProveedor").classList.toggle("show");
}

function llenarProveedor(proveedor, id, items) {
    document.getElementById("proveedor").value = proveedor;
    document.getElementById("proveedorId").value = id;
    document.getElementById("buscarProveedor").classList.toggle("show");

    //elminar todas las rows de la tabla de detalles


    $.ajax({
    			url: "/obtenerItems",
    			type: "Post",
    			data: {
    				proveedor: proveedor,
    				idProveedor: id
    			},
    			dataType: 'json',
    			success: function(jsonResponse){

    				if (jsonResponse == "") {
    					console.error('No hubo respuesta del servidor');
    				}else {
    					handleResponse(jsonResponse);
    				}
    			}
    		});

    		function handleResponse (responseObject) {
                    $("#itemsDelProveedor").empty();

    				responseObject.forEach((item) => {
    				    console.log(item);

                        $( "#itemsDelProveedor" ).append("<a onClick=\"llenarItem('"+item+"')\">"+item+"</a>");

    				});
    		}
   }


