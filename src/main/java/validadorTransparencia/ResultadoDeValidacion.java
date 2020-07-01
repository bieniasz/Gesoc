package validadorTransparencia;

import operacionComercial.OperacionEgreso;
import usuario.Usuario;
import usuario.UsuarioRevisor;

import java.time.LocalDate;
import java.util.List;

public class ResultadoDeValidacion {
    private OperacionEgreso operacionEgreso;
    private List<String> resultados;
    private LocalDate fecha;
    private Boolean leido;

    public ResultadoDeValidacion() {
        this.leido = false;
    }

    public void registrarResultado(){

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
}
