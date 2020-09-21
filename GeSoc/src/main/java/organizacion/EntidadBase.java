package organizacion;

import org.junit.ClassRule;

import javax.persistence.*;

@Entity
@Table
public class EntidadBase extends Organizacion {

    @Column
    private String nombreFicticio;

    @Column
    private String descripcion;

    @ManyToOne
    private EntidadJuridica entidadJuridica;

}
