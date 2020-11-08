package db.DAOs;

import domain.entities.operacionComercial.MedioDePago;

import java.util.List;

public interface MedioDePagoDAO {

    void guardarMedioDePago(MedioDePago medioDePago);
    MedioDePago buscarMedioDePagoPorId(Integer id);
    List<MedioDePago> buscarTodosLosMediosDePago();
}
