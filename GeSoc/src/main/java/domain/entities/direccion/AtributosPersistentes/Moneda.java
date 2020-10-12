package domain.entities.direccion.AtributosPersistentes;


import domain.entities.operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Moneda extends EntidadPersistente {

    @Column
    public String nombre;

    public Moneda(String nombre){
        this.nombre = nombre;
    }
}
