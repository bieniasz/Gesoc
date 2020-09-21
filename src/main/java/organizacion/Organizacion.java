package organizacion;

import operacionComercial.EntidadPersistente;
import operacionComercial.OperacionEgreso;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 @Entity
 @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Organizacion extends EntidadPersistente {

     @OneToMany
    protected List<OperacionEgreso> operacionesDeEgreso;

    public Map<String, String> getDatos(){
        return new HashMap<String, String>();
    }
}
