package DAO;

import jpa.EntityManagerHelper;
import jpa.Sdate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SdateDao {
    private EntityManager manager = EntityManagerHelper.getEntityManager();
    private EntityTransaction tx = manager.getTransaction();

    public Sdate getDateById(Long id){

        return manager.find(Sdate.class,id);

    }
}
