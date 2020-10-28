package domain.entities.organizacion;

import domain.entities.operacionComercial.EntidadPersistente;
import domain.entities.operacionComercial.OperacionEgreso;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 @Entity
 @Table
 @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 @DiscriminatorColumn(name = "TipoOrganizacion")
public abstract class Organizacion extends EntidadPersistente {

     @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    protected List<OperacionEgreso> operacionesDeEgreso;

     @Column
     private boolean activo;

     public Map<String, String> getDatos(){
        return new HashMap<String, String>();
    }
}
