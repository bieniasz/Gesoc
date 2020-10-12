package domain.entities.validadorTransparencia;

import java.util.ArrayList;
import java.util.List;


public class ValidadorDeTransparencia   {


    private List<ValidacionPendiente> pedidosDeValidacionPendientes = new ArrayList<ValidacionPendiente>();

    public void AgregarPedidos(ValidacionPendiente pedido) {
        this.pedidosDeValidacionPendientes.add(pedido);
    }

    public void ValidarEgresos(){
        this.pedidosDeValidacionPendientes.stream().forEach( (pedido) -> {
            pedido.validar();
        });
    };
}