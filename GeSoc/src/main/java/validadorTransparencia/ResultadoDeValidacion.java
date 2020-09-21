package validadorTransparencia;

import operacionComercial.EntidadPersistente;
import operacionComercial.OperacionEgreso;
import usuario.Usuario;
import usuario.UsuarioRevisor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class ResultadoDeValidacion  extends EntidadPersistente {

    @OneToOne
    private OperacionEgreso operacionEgreso;
    @ElementCollection
    private List<String> resultados;
    @Transient
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
        this.leido = true;
    }
    public void marcarNoLeido(){
        this.leido = false;
    }

    public Boolean leido() {
        return leido;
    }

    public LocalDate fecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<String> getResultados(){
        return  this.resultados;
    }

}
