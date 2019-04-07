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


    public Long validateLogin(Personne personne){

        List rs =  manager.createQuery("select p.idPersonne from Personne p where p.nom=:nom and p.prenom=:prenom and p.mail=:mail")
                .setParameter("nom",personne.getNom())
                .setParameter("prenom",personne.getPrenom())
                .setParameter("mail",personne.getMail())
                .getResultList();

        long idPersonne=0;

        if(!rs.isEmpty()){

            for(Object ligne : rs)
            {
                idPersonne = (Long) ligne;
            }
        }

        return idPersonne;
    }

    public Personne addUser(Personne personne){

        tx.begin();
        manager.persist(personne);
        tx.commit();
        return personne;
    }

    public List<Personne> getAllUser(){

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
