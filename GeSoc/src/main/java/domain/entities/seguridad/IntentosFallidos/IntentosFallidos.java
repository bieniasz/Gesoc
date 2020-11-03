package domain.entities.seguridad.IntentosFallidos;

import domain.entities.operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
public class IntentosFallidos extends EntidadPersistente {

    @Column
    private Integer usuarioId;
    @Column
    private int intentosRealizados;
    @Column
    private LocalDateTime horaDelIntentoMaximo;


    public IntentosFallidos(){
        this.intentosRealizados = 1;
        this.horaDelIntentoMaximo = null;
    }

    public void nuevoIntentoFallido(){
        this.intentosRealizados++;
    }

    public void reiniciarIntentos(){
        this.intentosRealizados = 1;
        this.horaDelIntentoMaximo = null;
    }

    public void setHoraDelIntentoMaximo() {
        this.horaDelIntentoMaximo = LocalDateTime.now();
    }
    public void setUsuarioId(Integer id) { this.usuarioId = id; }
    public Integer getUsuarioId() {
        return usuarioId;
    }
    public int getIntentosRealizados() { return intentosRealizados; }
    public LocalDateTime getHoraDelIntentoMaximo() {
        return horaDelIntentoMaximo;
    }
}