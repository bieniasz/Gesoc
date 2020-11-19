function mostrarBuscarProveedor() {
  document.getElementById("buscarProveedor").classList.toggle("show");
}

function llenarProveedor(proveedor, id, items) {
    document.getElementById("proveedor").value = proveedor;
    document.getElementById("proveedorId").value = id;
    document.getElementById("buscarProveedor").classList.toggle("show");

    $('#tbDetalles').empty();
    $('#rowItemNuevo0').remove();$('#rowItemNuevo1').remove();$('#rowItemNuevo2').remove();$('#rowItemNuevo3').remove();
    $('#rowItemNuevo4').remove();$('#rowItemNuevo5').remove();$('#rowItemNuevo6').remove();$('#rowItemNuevo7').remove();
    $('#rowItemNuevo8').remove();$('#rowItemNuevo9').remove();$('#rowItemNuevo10').remove();$('#rowItemNuevo11').remove();
    $('#rowItemNuevo12').remove();$('#rowItemNuevo13').remove();$('#rowItemNuevo14').remove();$('#rowItemNuevo15').remove();
    $('#rowItemNuevo16').remove();$('#rowItemNuevo17').remove();$('#rowItemNuevo18').remove();$('#rowItemNuevo19').remove();
    $('#rowItemNuevo20').remove();


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

                        $( "#itemsDelProveedor" ).append("<a onClick=\"llenarItem('"+item+"')\">"+item+"</a>");

    				});
    		}
   }


