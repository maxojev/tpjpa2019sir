package DAO;

import jpa.EntityManagerHelper;
import jpa.Lieu;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class LieuDao {
    private EntityManager manager = EntityManagerHelper.getEntityManager();
    private EntityTransaction tx = manager.getTransaction();

    public Lieu getLieuById(Long id){

        return manager.find(Lieu.class,id);

    }
}
