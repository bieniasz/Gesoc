package db.DAOs;

import domain.entities.ProveedorDocComer.TipoComprobante;

import java.util.List;

public interface TipoComprobanteDAO {
    
    List<TipoComprobante> buscarTodosLosTiposDeComprobantes();
    TipoComprobante buscarTipoComprobantePorId(Integer documentoComercialTipoComprobanteId);
}
