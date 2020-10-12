package domain.entities.seguridad.ContraseniasPrevias;

import domain.entities.usuario.Usuario;

public interface ContraseniasPreviasDAO {

    public ContraseniasPrevias getContraseniasPrevias(Usuario usuario);
    public void persistirContraseniaPrevia(ContraseniasPrevias contraseniasPrevias);
}
