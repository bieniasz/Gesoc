package usuario;

import operacionComercial.EntidadPersistente;
import organizacion.Organizacion;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="<<TipoRol>>")
public abstract class Rol extends EntidadPersistente { //La hicimos clase abstracta para poder persistir

    @Column
    private String TipoRol;

    abstract public Organizacion altaOrganizacionJuridica();

    abstract public Usuario nuevoUsuarioEstandar(String usuario, String contrasenia, Organizacion organizacion) throws Exception;

    abstract public Organizacion getOrganizacion();
}
