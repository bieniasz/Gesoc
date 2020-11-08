function mostrarDocumentoComercialModal(id) {
    document.getElementById("idEgresoSeleccionadoParaAdjuntarRecibo").value = id;
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}

function guardarDocumentoComercial(idEgreso) {
    var contenidoDocumento = "Documento vacio";
    var reader = new FileReader();
       reader.readAsDataURL(document.getElementById("documentoComercialAdjunto").files[0]);
       reader.onload = function () {
                $.post( "/guardarDocumento",
                    {
                        egresoId: document.getElementById("idEgresoSeleccionadoParaAdjuntarRecibo").value,
                        tipoDocumento: document.getElementById("documentoComercialTipo").value,
                        numero: document.getElementById("documentoComercialNumero").value,
                        tipoComprobanteId: document.getElementById("documentoComercialTipoComprobanteId").value,
                        contenidoSerializado: reader.result
                    } ).done( function() {
                            document.getElementById("modalAgregarDocumentoComercial").style.display = 'none';
                    });
       };
       reader.onerror = function (error) {
            contenidoDocumento = "Error al serializar el documento";
       };
}

function mostrarBuscarDocumentoComercialTipoComprobante() {
    document.getElementById("buscarDocumentoComercialTipoComprobante").classList.toggle("show");
}

function llenarTipoComprobante(id, descripcion) {
    document.getElementById("documentoComercialTipoComprobante").value = descripcion;
    document.getElementById("documentoComercialTipoComprobanteId").value = id;

    document.getElementById("buscarDocumentoComercialTipoComprobante").classList.toggle("show");
}