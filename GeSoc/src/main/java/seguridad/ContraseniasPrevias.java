package seguridad;

import operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class ContraseniasPrevias extends EntidadPersistente {

    @Column
    private String usuarioId;

    @Column
    private String contrasenia;


}
