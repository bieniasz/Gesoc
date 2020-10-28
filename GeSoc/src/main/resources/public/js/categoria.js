function mostrarBuscarCategorias() {
  document.getElementById("buscarCategoria").classList.toggle("show");
}

var cantidadDeCategorias = 0;
function agregarCategoria(descripcion, id) {
    var div = document.getElementById("seccionCategorias");

    var divCategoriaNueva = document.createElement("DIV");
        div.appendChild(divCategoriaNueva);

    var categoria = document.createElement("INPUT");
        categoria.setAttribute("id", "categoriaNueva" + cantidadDeCategorias);
        categoria.setAttribute("name", "categoriaNueva" + cantidadDeCategorias);
        categoria.setAttribute("type", "text");
        categoria.setAttribute("value", descripcion);
        categoria.setAttribute("class", "w3-tag w3-padding w3-small w3-center w3-yellow");
        categoria.readOnly = true;

     var categoriaId = document.createElement("INPUT");
         categoriaId.setAttribute("id", "categoriaNuevaId" + cantidadDeCategorias);
         categoriaId.setAttribute("name", "categoriaNuevaId" + cantidadDeCategorias);
         categoriaId.setAttribute("type", "hidden");
         categoriaId.setAttribute("value", id);

    var iconoBorrar = document.createElement("I");
        iconoBorrar.setAttribute("class", "fas fa-close");

    var aBorrar = document.createElement("A");
        aBorrar.setAttribute("class", "btn btn-search");
        aBorrar.setAttribute("id", "eliminarCategoriaNueva"  + cantidadDeCategorias);
        aBorrar.setAttribute("onclick", "eliminarCategoriaNueva(" + cantidadDeCategorias  + ")");
        aBorrar.appendChild(iconoBorrar);

        divCategoriaNueva.appendChild(categoria);
        divCategoriaNueva.appendChild(aBorrar);
        divCategoriaNueva.appendChild(categoriaId);

    cantidadDeCategorias = cantidadDeCategorias + 1;
    document.getElementById("cantidadDeCategoriasNuevas").value = cantidadDeCategorias
    document.getElementById("buscarCategoria").classList.toggle("show");
}

function eliminarCategoriaNueva(idCategoria) {
    var categoria = document.getElementById('categoriaNueva' + idCategoria);
    categoria.remove();

    var cruzCategoria = document.getElementById('eliminarCategoriaNueva' + idCategoria);
        cruzCategoria.remove();

    var categoriaId = document.getElementById('categoriaNuevaId' + idCategoria);
        categoriaId.remove();
}

function eliminarCategoria(idCategoria) {
    var categoria = document.getElementById('categoria' + idCategoria);
    categoria.remove();

    var cruzCategoria = document.getElementById('eliminarCategoria' + idCategoria);
        cruzCategoria.remove();
}


// PUEDO USAR ESTO PARA ELIMINAR CATEGORIAS QUE YA FUERON GUARDADAS
       // var descripcion = document.getElementById("descripcionItem").value;
    //var cantidad = document.getElementById("cantidadItem").value;
    //var valor = document.getElementById("valorItem").value;
    //$.ajax({
      //  type: "post",
       // url: "item?descripcion=" + descripcion + "&cantidad=" + cantidad + "&valor=" +valor,
        //success: function(resultado){
          //  location.reload(true);
        //}
    //});