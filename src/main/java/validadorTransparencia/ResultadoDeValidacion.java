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

    public void registrarResultado(){

    }

    public void marcarLeido(Usuario usuario){
        
    }
    public void marcarNoLeido(Usuario usuario){

    }
}
