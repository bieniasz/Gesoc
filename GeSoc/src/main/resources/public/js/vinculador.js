var criteriosAdicionales;

function mostrarVinculadorModal() {
    resetBusquedaMix();
    $("#modalVinculador").show();
}

function onCriterioSelected() {
    var criterio = $("#criterioTipo").val();
    if(criterio == "Mix") {
        $("#botonBusquedaMixHidden").removeClass("btn-search-hidden");
    } else {
        resetBusquedaMix();
    }
}

function resetBusquedaMix() {
    criteriosAdicionales = [];
    $("#botonBusquedaMixHidden").addClass("btn-search-hidden");
    $("#criteriosExtra").empty();
    $("#buscarMix a").removeClass("critSeleccionado");
    $("#buscarMix a").hide();
    $("#buscarMix").hide();
}

function toggleMixDropdown() {
    $criteriosDisponibles = $("#buscarMix a").filter(function(i){ return !$(this).hasClass("critSeleccionado"); });
    $criteriosDisponibles.show();
    $("#buscarMix").toggle();
}

function agregarCriterioExtra(criterioElegido) {
    criteriosAdicionales.push(criterioElegido);
    $elegido = $("#buscarMix a:contains('" + criterioElegido + "')");
    $elegido.addClass("critSeleccionado");
    $elegido.hide();
    var criterio = document.createElement("span");
        criterio.setAttribute("class", "label label-info");
        criterio.innerHTML = criterioElegido;
/*
    var span = document.createElement("span");
        span.setAttribute("class", "closebtn");
        span.setAttribute("onclick", "this.parentElement.style.display='none';");
        span.innerHTML = "&times;";

    criterio.appendChild(span);*/
    document.getElementById("criteriosExtra").appendChild(criterio);

    console.log(criteriosAdicionales);
}


function correrVinculacion(organizacion) {
    var div = document.createElement("DIV");
        div.setAttribute("class", "alert alert-progress");
        document.getElementById("divMensajesDelVinculador").appendChild(div);

    var span = document.createElement("SPAN");
        span.setAttribute("class", "closebtn");
        span.setAttribute("onclick", "this.parentElement.style.display='none';");
        span.innerHTML = "&times;";
        div.appendChild(span);

    var strong = document.createElement("STRONG");
        strong.innerHTML = "Vinculacion en proceso";
        div.appendChild(strong);

    document.getElementById("modalVinculador").style.display = 'none';

    $.post( "/vincular", {
        criterio: document.getElementById("criterioTipo").value,
        organizacionID: organizacion,
        criteriosAdicionales: criteriosAdicionales
    } ).done( mensajeComplete() );
}

function mensajeComplete() {
    setTimeout(function() {
        var $divAlert = $(".alert-progress");
            $divAlert.hide();

        var div = document.createElement("DIV");
            div.setAttribute("class", "alert alert-success");
            document.getElementById("divMensajesDelVinculador").appendChild(div);

        var span = document.createElement("SPAN");
            span.setAttribute("class", "closebtn");
            span.setAttribute("onclick", "this.parentElement.style.display='none';");
            span.innerHTML = "&times;";
            div.appendChild(span);

        var strong = document.createElement("STRONG");
            strong.innerHTML = "Vinculacion completada";
            div.appendChild(strong);
    }, 4000);
}

function cerrarVinculadorModal() {
    $("#criterioTipo option:contains('Egreso')").prop('selected',true);
    resetBusquedaMix();
    $("#modalVinculador").hide();
}
