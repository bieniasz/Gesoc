package direccion;

import direccion.AtributosPersistentes.Ciudad;
import direccion.AtributosPersistentes.Moneda;
import direccion.AtributosPersistentes.Pais;
import direccion.AtributosPersistentes.Provincia;
import operacionComercial.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table
public class Direccion extends EntidadPersistente{

    @Column
    public String calle;

    @Column
    public String altura;

    @ManyToOne
    public Ciudad ciudad;

    @ManyToOne
    public Provincia provincia;

    @ManyToOne
    public Pais pais;

    @Column
    private boolean activo;

    @OneToOne
    public Moneda monedaLocal;
}