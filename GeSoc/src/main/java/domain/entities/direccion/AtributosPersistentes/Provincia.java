package domain.entities.direccion.AtributosPersistentes;

import domain.entities.operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Provincia extends EntidadPersistente {

    public Provincia() {
    }

    @Column
    public String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
