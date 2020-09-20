package direccion;

import direccion.AtributosPersistentes.Ciudad;
import direccion.AtributosPersistentes.Moneda;
import direccion.AtributosPersistentes.Pais;
import direccion.AtributosPersistentes.Provincia;
import operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Direccion extends EntidadPersistente{

    @Column
    public String calle;

    @Column
    public String altura;

    @Transient
    public Ciudad ciudad;

    @Transient
    public Provincia provincia;

    @Transient
    public Pais pais;

    @Transient
    public Moneda monedaLocal;
}