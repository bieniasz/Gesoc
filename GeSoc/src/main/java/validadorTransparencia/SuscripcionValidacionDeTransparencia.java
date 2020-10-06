package validadorTransparencia;

import operacionComercial.OperacionEgreso;
import usuario.Usuario;
import usuario.UsuarioRevisor;
import validacionEgresos.CriterioValidacionEgresosPresupuesto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@DiscriminatorValue("Suscripcion")
public class SuscripcionValidacionDeTransparencia extends ValidacionPendiente{

    @OneToMany
    @JoinColumn(name="Revisores",referencedColumnName = "id")
    private List<UsuarioRevisor> revisores = new ArrayList<UsuarioRevisor>();

    @OneToOne
    private OperacionEgreso operacionEgreso;

    @OneToMany
    @JoinColumn(name="CriteriosDeseados",referencedColumnName = "id")
    private List<CriterioValidacionEgresosPresupuesto> criteriosDeseados = new ArrayList<CriterioValidacionEgresosPresupuesto>();

    @OneToOne
    private ResultadoDeValidacion resultadoDeValidacion;

    @Override
    public void validar(){
        List<String> errores = new ArrayList<String>();

        criteriosDeseados.stream().forEach( (criterio) -> {
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
