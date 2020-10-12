package domain.entities.seguridad.ContraseniasPrevias;

import domain.entities.usuario.Usuario;

import java.util.*;

public class ContraseniasPreviasDAOMemoria implements ContraseniasPreviasDAO {
    private Map<Usuario, ContraseniasPrevias> contraseniasPreviasList = new HashMap<>();
    private Usuario usuarioEnUso;

    @Override
    public ContraseniasPrevias getContraseniasPrevias(Usuario usuario) {
        this.usuarioEnUso = usuario;
        return this.contraseniasPreviasList.get(usuario);
    }

    @Override
    public void persistirContraseniaPrevia(ContraseniasPrevias contraseniasPrevias) {
        this.contraseniasPreviasList.put(this.usuarioEnUso, contraseniasPrevias);
    }
}
