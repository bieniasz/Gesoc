function serializarArchivo() {
    var archivoSerializado = getElementById("documentoComercialAdjunto").serialize();
    return archivoSerializado;

}

function mostrarBuscarDocumentoComercialTipoComprobante() {
    document.getElementById("buscarDocumentoComercialTipoComprobante").classList.toggle("show");
}

function llenarTipoComprobante(id, descripcion) {
    document.getElementById("documentoComercialTipoComprobante").value = descripcion;
    document.getElementById("documentoComercialTipoComprobanteId").value = id;

    document.getElementById("buscarDocumentoComercialTipoComprobante").classList.toggle("show");
}