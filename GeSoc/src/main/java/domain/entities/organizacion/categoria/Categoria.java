package domain.entities.organizacion.categoria;

import domain.entities.operacionComercial.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name="Categoria")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="TipoCategoria")
public abstract class Categoria extends EntidadPersistente {

    @Column
    private boolean activo;
    //TODO plantear cambiar nombre a TipoOrganizacion
}                                 //La hicimos abstracta para poder persistir

