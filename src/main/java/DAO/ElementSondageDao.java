package DAO;

import jpa.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ElementSondageDao {

    private EntityManager manager = EntityManagerHelper.getEntityManager();
    private EntityTransaction tx = manager.getTransaction();


    public ElementSondageDateLieu saveDL(ElementSondageDateLieu elementSondage){

        tx.begin();
        manager.persist(elementSondage);
        tx.commit();

        return elementSondage;
    }

    public ElementSondageDate saveD(ElementSondageDate elementSondage){

        tx.begin();
        manager.persist(elementSondage);
        tx.commit();

        return elementSondage;
    }

    public ElementSondageLieu saveL(ElementSondageLieu elementSondage){

        tx.begin();
        manager.persist(elementSondage);
        tx.commit();

        return elementSondage;
    }



}
