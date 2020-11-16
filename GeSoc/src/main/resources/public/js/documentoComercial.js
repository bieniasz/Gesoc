function mostrarDocumentoComercialModal(id) {
    document.getElementById("idEgresoSeleccionadoParaAdjuntarRecibo").value = id;
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}

function mostrarDocumentoComercialModalPopulado(idEgreso, descripcionTipoDocumento, idTipoDocumento, numero, tipo, contenido) {

    mostrarDocumentoComercialModal(idEgreso);
    document.getElementById("documentoComercialNumero").value = numero;
    document.getElementById("documentoComercialTipoComprobanteId").value = idTipoDocumento;
    document.getElementById("documentoComercialTipoComprobante").value = descripcionTipoDocumento;
    document.getElementById("documentoComercialAdjunto").type = "file";

    document.getElementById("documentoComercialTipo").value = tipo;
    if (tipo == "Digital") {
             var imagen = document.createElement("IMG");
                 imagen.src = contenido;
                 imagen.class = "w3-circle";
                 imagen.style = "width:150px";
                 imagen.id = "previsualizacionDelDocumento"
                 document.getElementById("seccionImagen").appendChild(imagen);
         }
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
            adjunto.type = "hidden";
            document.getElementById("previsualizacionDelDocumento").style.visibility = "hidden";
    }
    if (seleccion == "Digital") {
        document.getElementById("documentoComercialAdjunto").type = "file";
        var imagenPreview = document.getElementById("previsualizacionDelDocumento")
        if (imagenPreview) {
            imagenPreview.style.visibility = "visible";
        }
    }

}

function guardarDocumentoComercial(idEgreso) {
    console.log(document.getElementById("documentoComercialTipo").value);
    if(document.getElementById("documentoComercialTipo").value == "Digital") {
        console.log("Guardando documento digital");
        postDocumentoDigital(idEgreso);
    } else {
        console.log("Guardando documento fisico");
        postDocumentoFisico(idEgreso);

    }
}

function postDocumentoDigital(idEgreso) {
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
                            mensajeDocumentoAdjuntado();
                    });
       };
       reader.onerror = function (error) {
            contenidoDocumento = "Error al serializar el documento";
       };
}

function postDocumentoFisico(idEgreso) {
    $.post( "/guardarDocumento",
        {
            egresoId: document.getElementById("idEgresoSeleccionadoParaAdjuntarRecibo").value,
            tipoDocumento: document.getElementById("documentoComercialTipo").value,
            numero: document.getElementById("documentoComercialNumero").value,
            tipoComprobanteId: document.getElementById("documentoComercialTipoComprobanteId").value,
            contenidoSerializado: ""
        } ).done( function() {
                mensajeDocumentoAdjuntado();
        });
}

function guardarDocumentoComercialPresupuesto() {
    if(document.getElementById("documentoComercialTipo").value == "Digital") {
        postDocumentoDigitalPresupuesto();
    } else {
        postDocumentoFisicoPresupuesto();
    }

}

function postDocumentoDigitalPresupuesto(idEgreso) {
   var contenidoDocumento = "Documento vacio";
       var reader = new FileReader();
          reader.readAsDataURL(document.getElementById("documentoComercialAdjunto").files[0]);
          reader.onload = function () {
                   $.post( "/guardarDocumentoPresupuesto",
                       {
                           egresoId: document.getElementById("idEgresoSeleccionadoParaAdjuntarRecibo").value,
                           tipoDocumento: document.getElementById("documentoComercialTipo").value,
                           numero: document.getElementById("documentoComercialNumero").value,
                           tipoComprobanteId: document.getElementById("documentoComercialTipoComprobanteId").value,
                           contenidoSerializado: reader.result
                       } ).done( function() {
                               mensajeDocumentoAdjuntado();
                       });
          };
          reader.onerror = function (error) {
               contenidoDocumento = "Error al serializar el documento";
          };
}

function postDocumentoFisicoPresupuesto(idEgreso) {
    $.post( "/guardarDocumentoPresupuesto",
        {
            egresoId: document.getElementById("idEgresoSeleccionadoParaAdjuntarRecibo").value,
            tipoDocumento: document.getElementById("documentoComercialTipo").value,
            numero: document.getElementById("documentoComercialNumero").value,
            tipoComprobanteId: document.getElementById("documentoComercialTipoComprobanteId").value,
            contenidoSerializado: ""
        } ).done( function() {
                mensajeDocumentoAdjuntado();
        });
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

function mensajeDocumentoAdjuntado() {
    setTimeout(function() {
        document.getElementById("modalAgregarDetalle").style.display = 'none';

        var div = document.createElement("DIV");
            div.setAttribute("class", "alert alert-success");
            document.getElementById("divMensajesDocumentoGuardado").appendChild(div);

        var span = document.createElement("SPAN");
            span.setAttribute("class", "closebtn");
            span.setAttribute("onclick", "this.parentElement.style.display='none';");
            span.innerHTML = "&times;";
            div.appendChild(span);

        var strong = document.createElement("STRONG");
            strong.innerHTML = "Documento guardado";
            div.appendChild(strong);
    }, 10);
}