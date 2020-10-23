function mostrarBuscarProveedor() {
  document.getElementById("buscarProveedor").classList.toggle("show");
}

function filterFunction() {
  var input, filter, ul, li, a, i;
  input = document.getElementById("inputBuscarProveedor");
  filter = input.value.toUpperCase();
  div = document.getElementById("buscarProveedor");
  a = div.getElementsByTagName("a");
  for (i = 0; i < a.length; i++) {
    txtValue = a[i].textContent || a[i].innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      a[i].style.display = "";
    } else {
      a[i].style.display = "none";
    }
  }
}

function llenarProveedor(proveedor) {
    document.getElementById("proveedor").value = proveedor;
    document.getElementById("buscarProveedor").classList.toggle("show");
}


function agregarItem() {
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}

function guardarNuevoItem() {
    $.ajax({
        type: "post",
        url: "item",
        success: function(resultado){
            location.reload(true);
        }
    });
}
