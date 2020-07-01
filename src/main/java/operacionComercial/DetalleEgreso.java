package operacionComercial;

import org.javatuples.Pair;

public class DetalleEgreso {
    private Item item;
    private int cantidad;
    private float valorTotal;
	public Item getItem() {
		return item;
	}
	public int getCantidad() {
		return cantidad;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	
	public Pair getItemCantidad() {
		
	return new Pair<Item, Integer> (this.item, this.cantidad);
				
	}
}
