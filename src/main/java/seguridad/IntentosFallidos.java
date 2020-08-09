package seguridad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class IntentosFallidos {

    private int intentosRealizados;
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
