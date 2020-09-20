package operacionComercial;

import org.javatuples.Pair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="DetalleEgreso")
public class DetalleEgreso extends EntidadPersistente{

    @Transient
    private Item item;

    @Column
    private int cantidad;

    @Column
    private float valorTotal;

    public float getValorTotal() {
		return valorTotal;
	}
	
	

}
