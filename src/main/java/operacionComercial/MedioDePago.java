package operacionComercial;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

@Entity
@Table(name="MedioDePago")
public class MedioDePago {

    @Id
    @GeneratedValue
    private int id;

    @Transient
    private String idMercadoPago;

    @Transient
    private String descMercadoPago;

    @Transient
    private String tipoMercadoPago;

    public void pagar(){}
    public void altaMedioDePago(){}
}
