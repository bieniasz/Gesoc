package db.DAOs;


import domain.entities.operacionComercial.Item;
import domain.entities.operacionComercial.MedioDePago;

import java.util.List;

public interface ItemDAO {
    public List<Item> getTodosLosItems();
    public Item buscarItemPorId(Integer id);
}
