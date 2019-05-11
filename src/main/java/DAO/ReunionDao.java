package DAO;

import jpa.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Date;
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

        return (manager.createQuery("select r from Reunion r where r.dateValidee is null and r.lieuValide is null").getResultList());
    }

    public Reunion getOneReunion(Long idReunion){
        return manager.find(Reunion.class, idReunion);
    }

    public Sdate getDateChoisie(Long idReunion){


            List rs = manager.createQuery("select e.dateChoisie.idDate, count(e.dateChoisie.idDate) " +
                    "as nbr from ElementSondageDate e where e.mareunion.idReunion =:name" +
                    " group by e.dateChoisie.idDate " +
                    "order by nbr desc ")
                    .setParameter("name",idReunion)
                    .getResultList();

        long idDate = traitementListTolong(rs);
        System.out.println("dateMax"+idDate);

         List rsd =  manager.createQuery("select d.madate,d.pause from Sdate d where d.idDate =:var ")
                .setParameter("var",idDate)
                .getResultList();

        Date var = null;
        Boolean pause= false;

        for(Object ligne : rsd)
        {
            Object[] l = (Object[]) ligne;
            var = (java.sql.Date) l[0];
            pause = (Boolean) l[1];
        }

        return (new Sdate(var,pause,idDate));
    }

    public Lieu getLieuChoisie(Long idReunion){

        List rs = manager.createQuery("select e.lieuChoisie.iDLieu, count(e.lieuChoisie.iDLieu) " +
                "as nbr from ElementSondageLieu e where e.mareunion.idReunion =:name" +
                " group by e.lieuChoisie.iDLieu " +
                "order by nbr desc ")
                .setParameter("name",idReunion)
                .getResultList();

        long iDLieu = traitementListTolong(rs);

        List rsd =  manager.createQuery("select l.nomLieu from Lieu l where l.iDLieu=:var ")
                .setParameter("var",iDLieu)
                .getResultList();

        String var = "";

        for(Object ligne : rsd)
        {
            var = (String)ligne;
        }

        return (new Lieu(var,iDLieu));

    }

    public Reunion setDateChoisie(Reunion r){

        tx.begin();
        manager.merge(r);
        tx.commit();

        return r;
    }

    public Reunion setLieuChoisie(Reunion r){
        tx.begin();
        manager.merge(r);
        tx.commit();
        return r;
    }

    public List<Reunion> getMyAllReunion(Personne personne){

        return (manager.createQuery("select r from Reunion r where r.dateValidee is null and r.lieuValide is null and r.createur =:pers and r.mesElementSondage is not empty")
                .setParameter("pers", personne)
                .getResultList());
    }

    protected long traitementListTolong(List rs){

        long var= 0;

        for(Object ligne : rs)
        {
            Object[] l = (Object[]) ligne;
            Long i = (Long) l[0];
            var = i;
        }
        return var;
    }


}


