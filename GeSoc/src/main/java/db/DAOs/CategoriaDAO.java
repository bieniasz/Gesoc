package db.DAOs;

import domain.entities.operacionComercial.CategoriaDeOperaciones;
import domain.entities.organizacion.categoria.Categoria;

import java.util.List;

public interface CategoriaDAO {
    List<CategoriaDeOperaciones> getTodasLasCategorias();

    CategoriaDeOperaciones buscarCategoriaPorId(int idDeLaCategoria);
}
