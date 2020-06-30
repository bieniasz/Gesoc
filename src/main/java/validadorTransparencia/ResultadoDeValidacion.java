package validadorTransparencia;

import operacionComercial.OperacionEgreso;
import usuario.Usuario;
import usuario.UsuarioRevisor;

import java.time.LocalDate;
import java.util.List;

public class ResultadoDeValidacion {
    private OperacionEgreso operacionEgreso;
    private List<String> resultado;
    private LocalDate fecha;
    private Boolean leido;

    public ResultadoDeValidacion(){
        this.leido = false;
    }

    public void registrarResultado(){

    }

    public void marcarLeido(){
        this.leido = true;
    }
    public void marcarNoLeido(Usuario usuario){
        this.leido = false;
    }

    public Boolean leido() {
        return leido;
    }

    public LocalDate fecha() {
        return fecha;
    }
}
