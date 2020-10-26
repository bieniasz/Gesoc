package domain.entities.validadorTransparencia;

import domain.entities.operacionComercial.EntidadPersistente;
import domain.entities.operacionComercial.OperacionComercial;

import javax.persistence.*;


@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TipoSuscripcion")
public abstract class ValidacionPendiente extends EntidadPersistente {

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private OperacionComercial operacionComercial;


    public void validar(){};
}
