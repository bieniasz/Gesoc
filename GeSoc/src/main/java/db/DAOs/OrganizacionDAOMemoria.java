package db.DAOs;

import domain.entities.organizacion.EntidadJuridica;
import domain.entities.organizacion.Organizacion;

public class OrganizacionDAOMemoria implements OrganizacionDAO{

    @Override
    public Organizacion getOrganizacionPorUsuarioId(Integer idUsuario) {
        return new EntidadJuridica();
    }
}
