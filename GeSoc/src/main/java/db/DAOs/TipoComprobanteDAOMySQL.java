package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.TipoComprobante;
import domain.entities.operacionComercial.MedioDePago;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class TipoComprobanteDAOMySQL implements TipoComprobanteDAO {

    @Override
    public TipoComprobante buscarTipoComprobantePorId(Integer documentoComercialTipoComprobanteId) {
        return EntityManagerHelper.getEntityManager().find(TipoComprobante.class, documentoComercialTipoComprobanteId);
    }

    @Override
    public List<TipoComprobante> buscarTodosLosTiposDeComprobantes() {
        List<TipoComprobante> tiposComprobantes;
        Query query = EntityManagerHelper.createQuery("from TipoComprobante");

        try{
            tiposComprobantes = (List<TipoComprobante>) query.getResultList();
        } catch (NoResultException e){
            tiposComprobantes = null;
            e.printStackTrace();
        }
        return tiposComprobantes;
    }
}
