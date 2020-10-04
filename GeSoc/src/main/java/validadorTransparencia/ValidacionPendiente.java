package validadorTransparencia;

import operacionComercial.EntidadPersistente;
import operacionComercial.OperacionComercial;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table
public abstract class ValidacionPendiente extends EntidadPersistente {

    @OneToOne
    private OperacionComercial operacionComercial;

    @OneToOne
    private ResultadoDeValidacion resultadoDeValidacion;

    public void validar(){};
}
