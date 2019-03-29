package DAO;

import jpa.EntityManagerHelper;
import jpa.Reunion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;




public class ReunionDao {
    private EntityManager manager = EntityManagerHelper.getEntityManager();
    private EntityTransaction tx = manager.getTransaction();


    public Reunion addReunion(Reunion reunion){

        tx.begin();
        manager.persist(reunion);
        tx.commit();

        return reunion;

    }

    public List<Reunion> getAllReunion(){
        Query query = manager.createNativeQuery(  "SELECT idReunion,intitule, resume  FROM REUNION");

        List<Reunion> rs = query.getResultList();

        return rs;
    }

    public Reunion getOneReunion(Long idReunion){
        return manager.find(Reunion.class, idReunion);
    }
}


