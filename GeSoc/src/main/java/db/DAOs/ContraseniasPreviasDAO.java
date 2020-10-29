package db.DAOs;

import domain.entities.seguridad.ContraseniasPrevias.ContraseniasPrevias;
import domain.entities.usuario.Usuario;

public interface ContraseniasPreviasDAO {

    ContraseniasPrevias getContraseniasPrevias(Usuario usuario);
    void persistirContraseniaPrevia(ContraseniasPrevias contraseniasPrevias);
    void modificarContraseniasPrevias(ContraseniasPrevias contraseniasPrevias);
}
