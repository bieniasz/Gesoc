package domain.entities.validadorTransparencia;

import db.DAOs.SuscripcionValidacionDeTransparenciaDAO;
import db.DAOs.ValidacionPendienteDao;

import java.util.ArrayList;
import java.util.List;


public class ValidadorDeTransparencia   {

    private List<ValidacionPendiente> pedidosDeValidacionPendientes = new ArrayList<ValidacionPendiente>();
    private ValidacionPendienteDao validacionPendienteDao = new SuscripcionValidacionDeTransparenciaDAO();

    public void AgregarPedidos(ValidacionPendiente pedido) {
        this.pedidosDeValidacionPendientes.add(pedido);
    }

    public void ValidarEgresos(){
        boolean esUnTest = this.pedidosDeValidacionPendientes.size() > 0;

        if(esUnTest) {
            this.pedidosDeValidacionPendientes.stream().forEach( (pedido) -> pedido.validar() );
        }
        else {
            List<ValidacionPendiente> pedidosDeLaDB = this.validacionPendienteDao.buscarValidacionesPendientes();
            pedidosDeLaDB.stream().forEach( (pedido) -> pedido.validar() );
        }

    };
}