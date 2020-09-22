package validadorTransparencia;

import operacionComercial.DetalleEgreso;
import operacionComercial.OperacionEgreso;
import usuario.UsuarioRevisor;
import validacionSeleccionProveedor.CriterioValidacionEgresosPresupuesto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuscripcionValidacionDeTransparencia {
    private List<UsuarioRevisor> revisores = new ArrayList<UsuarioRevisor>();
    private OperacionEgreso operacionEgreso;
    private List<CriterioValidacionEgresosPresupuesto> criteriosDeseados = new ArrayList<CriterioValidacionEgresosPresupuesto>();
    private ResultadoDeValidacion resultadoDeValidacion;

    public void validar() {
        List<String> errores = new ArrayList<String>();

        criteriosDeseados.forEach( (criterio) -> {
            try {
                criterio.validar(this.operacionEgreso);
            } catch (Exception e) {
                errores.add(e.getMessage());
            }
        });

        this.GenerarResultadoDeValidacion(errores);
        this.notificarRevisores();
    }

    private void GenerarResultadoDeValidacion(List<String> errores){
        this.resultadoDeValidacion = new ResultadoDeValidacion();

        if(errores.size() > 0) {
            this.resultadoDeValidacion.registrarResultado(errores, this.operacionEgreso);
        } else
        {
            List<String> mensajeTodasLasValidacionesDieronOk = new ArrayList<String>();
            mensajeTodasLasValidacionesDieronOk.add("La Operacion de Egreso cumple con todas las politicas");
            this.resultadoDeValidacion.registrarResultado(mensajeTodasLasValidacionesDieronOk, this.operacionEgreso);
        }

        this.resultadoDeValidacion.setFecha(LocalDate.now());
    }

    private void notificarRevisores(){
        this.revisores.stream().forEach( (revisor) -> {
            revisor.recibirResultado(this.resultadoDeValidacion);
        });
    }



    public void agregarRevisor(UsuarioRevisor usuario){
        this.revisores.add(usuario);
    }

    public void agregarCriterio(CriterioValidacionEgresosPresupuesto criterio){
        this.criteriosDeseados.add((criterio));
    }

    public void setOperacionEgreso(OperacionEgreso operacion){
        this.operacionEgreso = operacion;
    }

}
