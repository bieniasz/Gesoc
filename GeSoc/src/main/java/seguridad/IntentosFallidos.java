package seguridad;

import operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table
public class IntentosFallidos extends EntidadPersistente {

    @Column
    private int intentosRealizados;
    @Column(columnDefinition = "DATE")
    private LocalDateTime horaDelIntentoMaximo;

    public IntentosFallidos(){
        this.intentosRealizados = 0;
        horaDelIntentoMaximo = null;
    }

    public int getCantidadIntentos() {
        return intentosRealizados;
    }



    public void nuevoIntentoFallido(){
        this.intentosRealizados ++;
    }

    public void reiniciarIntentos(){
        this.intentosRealizados = 0;
        this.horaDelIntentoMaximo = null;
    }


    public LocalDateTime getHoraDelIntentoMaximo() {
        return horaDelIntentoMaximo;
    }

    public void setHoraDelIntentoMaximo() {
        this.horaDelIntentoMaximo = LocalDateTime.now();
    }

}
