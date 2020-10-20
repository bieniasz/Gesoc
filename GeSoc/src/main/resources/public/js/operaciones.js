function filterFunction() {
	var x = document.getElementById("filtros");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

function agregarItem() {
    document.getElementById("modalAgregarDetalle").style.display = 'block';
}