package db.DAOs;

import domain.entities.operacionComercial.CategoriaDeOperaciones;
import domain.entities.organizacion.categoria.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOMemoria implements CategoriaDAO {
    @Override
    public List<CategoriaDeOperaciones> getTodasLasCategorias() {

        CategoriaDeOperaciones categoriaDeOperaciones = new CategoriaDeOperaciones();
        categoriaDeOperaciones.setDescripcion("Alimentos");
        categoriaDeOperaciones.setId(1);

        CategoriaDeOperaciones categoriaDeOperaciones1 = new CategoriaDeOperaciones();
        categoriaDeOperaciones1.setDescripcion("Bebidas");
        categoriaDeOperaciones.setId(2);

        CategoriaDeOperaciones categoriaDeOperaciones2 = new CategoriaDeOperaciones();
        categoriaDeOperaciones2.setDescripcion("Telecomunicaciones");
        categoriaDeOperaciones.setId(3);

        CategoriaDeOperaciones categoriaDeOperaciones3 = new CategoriaDeOperaciones();
        categoriaDeOperaciones3.setDescripcion("Network");
        categoriaDeOperaciones.setId(4);

        List<CategoriaDeOperaciones> categorias = new ArrayList<>();
        categorias.add(categoriaDeOperaciones);
        categorias.add(categoriaDeOperaciones1);
        categorias.add(categoriaDeOperaciones2);
        categorias.add(categoriaDeOperaciones3);

        return categorias;
    }

    @Override
    public CategoriaDeOperaciones buscarCategoriaPorId(int idDeLaCategoria) {
        CategoriaDeOperaciones categoriaDeOperaciones = new CategoriaDeOperaciones();
        categoriaDeOperaciones.setDescripcion("Alimentos");
        categoriaDeOperaciones.setId(1);

        return categoriaDeOperaciones;
    }
}
