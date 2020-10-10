package seguridad.ContraseniasPrevias;

import usuario.Usuario;

public interface ContraseniasPreviasDAO {

    public ContraseniasPrevias getContraseniasPrevias(Usuario usuario);
    public void persistirContraseniaPrevia(ContraseniasPrevias contraseniasPrevias);
}
