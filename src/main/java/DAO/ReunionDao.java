package DAO;

import jpa.EntityManagerHelper;
import jpa.Lieu;
import jpa.Reunion;
import jpa.Sdate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
        Query query = manager.createNativeQuery(  "SELECT idReunion,intitule, resume  FROM REUNION");

        List<Reunion> rs = query.getResultList();

        return rs;
    }

    public Reunion getOneReunion(Long idReunion){
        return manager.find(Reunion.class, idReunion);
    }

    public Sdate getDateChoisie(Long idReunion){

        List rs = manager.createQuery("select e.dateChoisie_bis.idDate, count(e.dateChoisie_bis.idDate) " +
                     "as nbr from ElementSondageDateLieu e where e.mareunion.idReunion =:name" +
                     " group by e.dateChoisie_bis.idDate " +
                     "order by nbr desc ")
                    .setParameter("name",idReunion)
                    .getResultList();

        long idDate = traitementListTolong(rs);

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

        return (new Sdate(var,pause));
    }

    public Lieu getLieuChoisie(Long idReunion){

        List rs = manager.createQuery("select e.lieuChoisie_bis.iDLieu, count(e.lieuChoisie_bis.iDLieu) " +
                "as nbr from ElementSondageDateLieu e where e.mareunion.idReunion =:name" +
                " group by e.lieuChoisie_bis.iDLieu " +
                "order by nbr desc ")
                .setParameter("name",idReunion)
                .getResultList();

        long iDLieu = traitementListTolong(rs);

        List rsd =  manager.createQuery("select l.nomLieu from Lieu l where l.iDLieu=:var ")
                .setParameter("var",iDLieu)
                .getResultList();

        String var = "";

        System.out.println("TestMax "+rsd.toString());

        for(Object ligne : rsd)
        {
            var = (String)ligne;
        }

        return (new Lieu(var));

    }

    private long traitementListTolong(List rs){

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


