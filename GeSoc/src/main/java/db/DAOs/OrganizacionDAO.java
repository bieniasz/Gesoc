package db.DAOs;

import domain.entities.organizacion.Organizacion;

public interface OrganizacionDAO {
    public Organizacion getOrganizacionPorUsuarioId(Integer idUsuario);
}
