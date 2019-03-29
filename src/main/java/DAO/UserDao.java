package DAO;

import jpa.EntityManagerHelper;
import jpa.Personne;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

public class UserDao {

    private EntityManager manager = EntityManagerHelper.getEntityManager();
    private EntityTransaction tx = manager.getTransaction();


    public boolean validateLogin(Personne personne){

//        return manager.contains(personne);
        Query query = manager.createNativeQuery(  "SELECT * FROM PERSONNE where nom =? and prenom =? and mail =?");
        query.setParameter(1, personne.getNom())
                .setParameter(2, personne.getPrenom())
                .setParameter(3, personne.getMail());

        List<Integer> rs = query.getResultList();

        return !rs.isEmpty();
    }


    public Personne addUser(Personne personne){

        tx.begin();
        manager.persist(personne);
        tx.commit();
        return personne;
    }

    public  List<Personne> getAllUser(){

        return   manager.createQuery("select p.nom, p.prenom,p.mail From Personne p").getResultList();

    }

    public Personne getUser(Personne personne){
        Query query = manager.createNativeQuery(  "SELECT * FROM PERSONNE where nom =? and prenom =? and mail =?");
        query.setParameter(1, personne.getNom())
                .setParameter(2, personne.getPrenom())
                .setParameter(3, personne.getMail());

        Object rs = query.getSingleResult();
        Object[] l = (Object[]) rs;

        Personne personne1 =new Personne();

        personne1.setIdPersonne(((BigInteger) l[0]).longValue());
        personne1.setNom((String) l[1]);
        personne1.setPrenom((String) l[2]);
        personne1.setMail((String) l[3]);

        return personne1;
    }

    public Personne getUserById(Long id){

        return manager.find(Personne.class,id);

    }
}
