package organizacion;

import operacionComercial.EntidadPersistente;
import operacionComercial.OperacionEgreso;
import org.hibernate.annotations.ManyToAny;
import organizacion.categoria.Categoria;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 @Entity
 @Table
public abstract class Organizacion extends EntidadPersistente {


     @OneToMany
    protected List<OperacionEgreso> operacionesDeEgreso;

     @Column
     private boolean activo;

    public Map<String, String> getDatos(){
        return new HashMap<String, String>();
    }
}
