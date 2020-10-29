package domain.entities.operacionComercial;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="MedioDePago")
public class MedioDePago extends EntidadPersistente {

    public MedioDePago() {
    }

    @Column(name="Nombre")
    private String idMercadoPago;

    @Column(name="DescripcionMercadoPago")
    private String descMercadoPago;

    @Column(name="TipoMercardoPago")
    private String tipoMercadoPago;

    @Column
    private boolean activo;

    public void pagar(){}
    public void altaMedioDePago(){}

    public String getIdMercadoPago() {
        return idMercadoPago;
    }

    public void setIdMercadoPago(String idMercadoPago) {
        this.idMercadoPago = idMercadoPago;
    }

    public String getDescMercadoPago() {
        return descMercadoPago;
    }

    public void setDescMercadoPago(String descMercadoPago) {
        this.descMercadoPago = descMercadoPago;
    }

    public String getTipoMercadoPago() {
        return tipoMercadoPago;
    }

    public void setTipoMercadoPago(String tipoMercadoPago) {
        this.tipoMercadoPago = tipoMercadoPago;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
