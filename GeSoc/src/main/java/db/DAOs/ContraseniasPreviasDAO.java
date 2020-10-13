package db.DAOs;

import domain.entities.seguridad.ContraseniasPrevias.ContraseniasPrevias;
import domain.entities.usuario.Usuario;

public interface ContraseniasPreviasDAO {

    public ContraseniasPrevias getContraseniasPrevias(Usuario usuario);
    public void persistirContraseniaPrevia(ContraseniasPrevias contraseniasPrevias);
}
