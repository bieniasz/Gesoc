function mostrarVinculadorModal() {
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}

/*
  <div id="divMensajesDelVinculador"></div>

  <div class="alert">
      <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
      <strong>Vinculacion en proceso</strong>
  </div>
*/

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
        organizacionID: organizacion
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
