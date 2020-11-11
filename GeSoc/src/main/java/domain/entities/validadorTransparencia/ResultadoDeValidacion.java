package domain.entities.validadorTransparencia;

import domain.entities.operacionComercial.EntidadPersistente;
import domain.entities.operacionComercial.OperacionEgreso;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class ResultadoDeValidacion  extends EntidadPersistente {

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private OperacionEgreso operacionEgreso;
    @ElementCollection
    private List<String> resultados;
    @Column(columnDefinition = "DATE")
    private LocalDate fecha;
    @Column
    private Boolean leido;

    public ResultadoDeValidacion() {
        this.leido = false;
    }

    public void registrarResultado(List<String> mensajes, OperacionEgreso operacion){
        this.resultados = mensajes;
        this.operacionEgreso = operacion;
    }

    public void marcarLeido(){
        setLeido(true);
    }

    public void setLeido(Boolean leido) { this.leido = leido; }

    public Boolean getLeido() {
        return leido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<String> getResultados(){
        return  this.resultados;
    }

    public void setResultados(List<String> resultados) { this.resultados = resultados; }

    public OperacionEgreso getOperacionEgreso() {
        return operacionEgreso;
    }

    public void setOperacionEgreso(OperacionEgreso operacionEgreso) {
        this.operacionEgreso = operacionEgreso;
    }
}
