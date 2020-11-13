function mostrarVinculadorModal() {
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}

var criteriosAdicionales = [];
function agregarCriterioExtra(criterioElegido) {

    criteriosAdicionales.push(criterioElegido);

    var criterio = document.createElement("SPAN");
        criterio.setAttribute("class", "label label-info");
        criterio.innerHTML = criterioElegido;
        document.getElementById("criteriosExtra").appendChild(criterio);

    console.log(criteriosAdicionales);
}

function correrVinculacion(organizacion) {
    var div = document.createElement("DIV");
        div.setAttribute("class", "alert");
        document.getElementById("divMensajesDelVinculador").appendChild(div);

    var span = document.createElement("SPAN");
        span.setAttribute("class", "closebtn");
        span.setAttribute("onclick", "this.parentElement.style.display='none';");
        span.innerHTML = "&times;";
        div.appendChild(span);

    var strong = document.createElement("STRONG");
        strong.innerHTML = "Vinculacion en proceso";
        div.appendChild(strong);

    document.getElementById("modalAgregarDetalle").style.display = 'none';

    $.post( "/vincular", {
        criterio: document.getElementById("criterioTipo").value,
        organizacionID: organizacion,
        criteriosAdicionales: criteriosAdicionales
    } ).done( mensajeComplete() );
}

function mensajeComplete() {
    var div = document.createElement("DIV");
        div.setAttribute("class", "alert success");
        document.getElementById("divMensajesDelVinculador").appendChild(div);

    var span = document.createElement("SPAN");
        span.setAttribute("class", "closebtn");
        span.setAttribute("onclick", "this.parentElement.style.display='none';");
        span.innerHTML = "&times;";
        div.appendChild(span);

    var strong = document.createElement("STRONG");
        strong.innerHTML = "Vinculacion completada";
        div.appendChild(strong);
}

function cerrarVinculadorModal() {
    document.getElementById("modalAgregarDetalle").style.display = 'none';
}

function mostrarMixBusqueda() {
    var criterio = document.getElementById("criterioTipo").value;

    if(criterio == "Mix") {
        document.getElementById("botonBusquedaMixHidden").style.visibility = 'visible';
    }
}

function mostrarMixDropdown() {
    document.getElementById("buscarMix").classList.toggle("show");
}

