function mostrarDocumentoComercialModal(id) {
    document.getElementById("idEgresoSeleccionadoParaAdjuntarRecibo").value = id;
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}

function previewFile() {
  console.log("Metodo preview");
  eliminarPreviewAnterior()
    var preview = document.querySelector('img');
    var file    = document.getElementById("documentoComercialAdjunto").files[0];
    var reader  = new FileReader();

  reader.onloadend = function () {
        var imagen = document.createElement("IMG");
        imagen.src = reader.result;
        imagen.class = "w3-circle";
        imagen.style = "width:150px";
        imagen.id = "previsualizacionDelDocumento"
        document.getElementById("seccionImagen").appendChild(imagen);
  }

  if (file) {
      reader.readAsDataURL(file);
  } else {
      preview.src = "";
  }
}

function eliminarPreviewAnterior() {
    var previewAnterior = document.getElementById("previsualizacionDelDocumento");

    if(previewAnterior != null) {
        previewAnterior.remove();
    }
}

function mostrarOcultarAdjuntarRecibo() {
    var seleccion = document.getElementById("documentoComercialTipo").value;

    if (seleccion == "Fisico") {
        var adjunto = document.getElementById("documentoComercialAdjunto");
            adjunto.value = null;
            adjunto.type = "hidden";

        eliminarPreviewAnterior();
    }
    if (seleccion == "Digital") {
        document.getElementById("documentoComercialAdjunto").type = "file";
    }

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

function mostrarBuscarDocumentoComercialTipoComprobante() {
    document.getElementById("buscarDocumentoComercialTipoComprobante").classList.toggle("show");
}

function llenarTipoComprobante(id, descripcion) {
    document.getElementById("documentoComercialTipoComprobante").value = descripcion;
    document.getElementById("documentoComercialTipoComprobanteId").value = id;

    document.getElementById("buscarDocumentoComercialTipoComprobante").classList.toggle("show");
}