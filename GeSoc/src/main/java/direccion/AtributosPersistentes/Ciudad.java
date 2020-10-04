package direccion.AtributosPersistentes;

import operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Ciudad extends EntidadPersistente {

    @Column
    public String nombre;

    public Ciudad(String nombre){
        this.nombre = nombre;
    }
}
