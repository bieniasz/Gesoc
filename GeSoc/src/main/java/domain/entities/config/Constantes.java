package domain.entities.config;

public class Constantes {
    /* Criterios de ordenamiento del Vinculador */
    public static final String vinculador_criterios_PrimeroEgreso = "Orden-Valor-PrimeroEgreso";
    public static final String vinculador_criterios_PrimeroIngreso = "Orden-Valor-PrimeroIngreso";
    public static final String vinculador_criterios_Fecha = "Fecha";
    public static final String vinculador_criterios_Mix = "Mix";
    public static final String[] vinculador_criterios = {
            vinculador_criterios_Fecha,
            vinculador_criterios_PrimeroIngreso,
            vinculador_criterios_PrimeroEgreso,
            vinculador_criterios_Mix
    };
}
