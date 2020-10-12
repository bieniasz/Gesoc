package domain.entities.direccion;

import domain.entities.direccion.AtributosPersistentes.Ciudad;
import domain.entities.direccion.AtributosPersistentes.Moneda;
import domain.entities.direccion.AtributosPersistentes.Pais;
import domain.entities.direccion.AtributosPersistentes.Provincia;
import domain.entities.operacionComercial.EntidadPersistente;

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