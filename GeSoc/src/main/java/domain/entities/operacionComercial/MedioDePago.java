package domain.entities.operacionComercial;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="MedioDePago")
public class MedioDePago extends EntidadPersistente {


    @Column(name="Nombre")
    private String idMercadoPago;

    @Column(name="DescuentoMercadoPago")
    private String descMercadoPago;

    @Column(name="TipoMercardoPago")
    private String tipoMercadoPago;

    @Column
    private boolean activo;

    public void pagar(){}
    public void altaMedioDePago(){}
}
