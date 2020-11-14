function filterFunction() {
	var x = document.getElementById("filtros");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }



var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.display === "block") {
      panel.style.display = "none";
    } else {
      panel.style.display = "block";
    }
  });
}

}

function mostrarMensajes(id){
        document.getElementById("idDelResultadoAUtilizar").value=id;
        var mensajes = document.getElementsByName("mensajesDeResultado"+id);
        mensajes.forEach(mensaje => cargarMensaje(mensaje.value));
        document.getElementById("modalAgregarDetalle").style.display = 'block';

}

function cargarMensaje(mensaje){

        var span = document.createElement("SPAN");
            span.innerHTML = mensaje;
            document.getElementById("seccionDeMensajes").appendChild(span);

        var break1 = document.createElement("BR");
            document.getElementById("seccionDeMensajes").appendChild(break1);

}

function marcarLeido() {

       var id = document.getElementById("idDelResultadoAUtilizar").value;
       console.log(id);
     $.post( "/marcarLeido",{idResultado: id}).done( function() {  location.reload(true); });

}

