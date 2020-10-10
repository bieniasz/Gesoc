package seguridad.IntentosFallidos;

import usuario.Usuario;

public interface IntentosFallidosDAO {

    public IntentosFallidos getIntentosFallidos(Usuario usuario);
    public void persistirIntentoFallido(IntentosFallidos intentoFallido);
}
