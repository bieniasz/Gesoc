package db.DAOs;

import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import domain.entities.usuario.Usuario;

import java.util.HashMap;
import java.util.Map;

public class IntentosFallidosDAOMemoria implements IntentosFallidosDAO{

    private Map<Usuario, IntentosFallidos> intentos = new HashMap<>();
    private Usuario usuarioEnUso;

    @Override
    public IntentosFallidos getIntentosFallidos(Usuario usuario) {
        this.usuarioEnUso = usuario;
        return this.intentos.get(usuario);
    }

    @Override
    public void persistirIntentoFallido(IntentosFallidos intentoFallido) {
        this.intentos.put(usuarioEnUso, intentoFallido);
    }

    @Override
    public IntentosFallidos buscarIntentosFallidosPorId(Integer id) {
        return null;
    }
}
