package domain.entities.validadorTransparencia;

import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.usuario.UsuarioRevisor;
import domain.entities.validacionEgresos.CriterioValidacionEgresosPresupuesto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("SuscripcionValidacionDeTransparencia")
public class SuscripcionValidacionDeTransparencia extends ValidacionPendiente{

    @ManyToMany(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    private List<UsuarioRevisor> revisores = new ArrayList<UsuarioRevisor>();

    @ManyToOne
    private OperacionEgreso operacionEgreso;

    @ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
    private List<CriterioValidacionEgresosPresupuesto> criteriosDeseados = new ArrayList<CriterioValidacionEgresosPresupuesto>();

    @OneToOne(cascade={CascadeType.ALL})
    private ResultadoDeValidacion resultadoDeValidacion;

    @Column
    private Boolean validada = false;

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
        this.validada = true;
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

    public List<UsuarioRevisor> getRevisores() {
        return revisores;
    }

    public void setRevisores(List<UsuarioRevisor> revisores) {
        this.revisores = revisores;
    }

    public OperacionEgreso getOperacionEgreso() {
        return operacionEgreso;
    }

    public List<CriterioValidacionEgresosPresupuesto> getCriteriosDeseados() {
        return criteriosDeseados;
    }

    public void setCriteriosDeseados(List<CriterioValidacionEgresosPresupuesto> criteriosDeseados) {
        this.criteriosDeseados = criteriosDeseados;
    }

    public ResultadoDeValidacion getResultadoDeValidacion() {
        return resultadoDeValidacion;
    }

    public void setResultadoDeValidacion(ResultadoDeValidacion resultadoDeValidacion) {
        this.resultadoDeValidacion = resultadoDeValidacion;
    }

    public Boolean getValidada() {
        return validada;
    }

    public void setValidada(Boolean validada) {
        this.validada = validada;
    }
}
