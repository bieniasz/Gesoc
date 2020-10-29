package domain.entities.seguridad.ContraseniasPrevias;

import domain.entities.operacionComercial.EntidadPersistente;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class ContraseniasPrevias extends EntidadPersistente {


    @Column
    private Integer usuarioId;

    @ElementCollection
    private List<String> contrasenias;

    public ContraseniasPrevias() {
    }

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

    public Integer getUsuarioId() { return usuarioId; }

    public void setContrasenias(List<String> contrasenias) { this.contrasenias = contrasenias; }

}