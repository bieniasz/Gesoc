package operacionComercial;

import org.javatuples.Pair;

import javax.persistence.*;

@Entity
@Table(name="DetalleEgreso")
public class DetalleEgreso extends EntidadPersistente{

    @OneToOne
    private Item item;

    @Column
    private int cantidad;

    @Column
    private float valorTotal;

    public float getValorTotal() {
		return valorTotal;
	}
	
	

}
