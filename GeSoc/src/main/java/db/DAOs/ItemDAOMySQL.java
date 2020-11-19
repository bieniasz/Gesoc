package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.Item;

import javax.persistence.NoResultException;
import java.util.List;

public class ItemDAOMySQL implements ItemDAO{

    @Override
    public List<Item> getTodosLosItems() {
        List<Item> itemList;
        try{
            itemList = (List<Item>) EntityManagerHelper
                    .createQuery("from Item")
                    .getResultList();
        } catch (NoResultException e){
            itemList = null;
        }
        return itemList;
    }

    @Override
    public Item buscarItemPorId(Integer id) {
        return EntityManagerHelper.getEntityManager().find(Item.class, id);
    }

}
