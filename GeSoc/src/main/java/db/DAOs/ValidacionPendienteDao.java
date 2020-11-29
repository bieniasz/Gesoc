package db.DAOs;

import domain.entities.validadorTransparencia.ValidacionPendiente;

import java.util.List;

public interface ValidacionPendienteDao {
    List<ValidacionPendiente> buscarValidacionesPendientes();
}
