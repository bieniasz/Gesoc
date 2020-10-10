package seguridad.ContraseniasPrevias;

import operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class ContraseniasPrevias extends EntidadPersistente {

    @Column
    private Integer usuarioId;

    @ElementCollection
    private List<String> contrasenias;


    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<String> getContrasenias() {
        return contrasenias;
    }

    public void setContrasenia(List<String> contrasenias) {
        this.contrasenias = contrasenias;
    }

    public void agregarContrasenia(String contrasenia) { this.contrasenias.add(contrasenia); }

    public void removerContrseniaVieja() { this.contrasenias.remove(0);}
}
