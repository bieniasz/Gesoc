package validadorTransparencia;

import operacionComercial.OperacionEgreso;
import usuario.Usuario;
import usuario.UsuarioRevisor;

import java.time.LocalDate;
import java.util.List;

public class ResultadoDeValidacion {
    private OperacionEgreso operacionEgreso;S
    private List<tring> resultados;
    private LocalDate fecha;
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
