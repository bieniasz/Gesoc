function mostrarDocumentoComercialModal(id) {
    document.getElementById("idEgresoSeleccionadoParaAdjuntarRecibo").value = id;
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}

function guardarDocumentoComercialPresupuesto(idEgreso) {
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
                            location.reload(true);
                    });
       };
       reader.onerror = function (error) {
            contenidoDocumento = "Error al serializar el documento";
       };
}


function limpiarAgregarDocumentoModal() {
    document.getElementById("idEgresoSeleccionadoParaAdjuntarRecibo").value = null;
    document.getElementById("documentoComercialTipo").value = 'Fisico';
    document.getElementById("documentoComercialNumero").value = null;
    document.getElementById("documentoComercialTipoComprobanteId").value = null;
    document.getElementById("documentoComercialTipoComprobante").value = null;

    var adjunto = document.getElementById("documentoComercialAdjunto");
                adjunto.value = null;
                adjunto.type = "hidden";
    eliminarPreviewAnterior();

    document.getElementById("modalAgregarDetalle").style.display = 'none';
}