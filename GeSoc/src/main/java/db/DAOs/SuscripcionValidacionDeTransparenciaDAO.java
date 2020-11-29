package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.TipoComprobante;
import domain.entities.validadorTransparencia.ValidacionPendiente;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class SuscripcionValidacionDeTransparenciaDAO implements ValidacionPendienteDao{
    @Override
    public List<ValidacionPendiente> buscarValidacionesPendientes() {
        List<ValidacionPendiente> validacionesPendientes;
        Query query = EntityManagerHelper.createQuery("from SuscripcionValidacionDeTransparencia where validada = 0");

        try{
            validacionesPendientes = (List<ValidacionPendiente>) query.getResultList();
        } catch (NoResultException e){
            validacionesPendientes = null;
            e.printStackTrace();
        }
        return validacionesPendientes;
    }
}
