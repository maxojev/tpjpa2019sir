package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaTest {


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Set<Sdate> dateProposees1 = new HashSet<Sdate>();
		Set<Sdate> dateProposees2 = new HashSet<Sdate>();
		Set<Sdate> dateProposees3 = new HashSet<Sdate>();

		Set<Lieu> lieuProposees1 = new HashSet<Lieu>();
		Set<Lieu> lieuProposees2 = new HashSet<Lieu>();



		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();


		try {
			Personne personneAdmin = new Personne("WOLOU","Mike","test");
			manager.persist(personneAdmin);
			Personne personneA = new Personne("CHABI", "Max", "maxtest");
			manager.persist(personneA);
			Personne personneB = new Personne("LOU","Jean", "JeanMail");
			manager.persist(personneB);

			Sdate date0 = new Sdate(new Date(2019,02,20),true);
			manager.persist(date0);
			Sdate date1 = new Sdate(new Date(2019,01,01),false);
			manager.persist(date1);
			Sdate date2 = new Sdate(new Date(2019,04,06),true);
			manager.persist(date2);
			Sdate date3 = new Sdate(new Date(2019,06,18),false);
			manager.persist(date3);
			Sdate date4 = new Sdate(new Date(2019,10,21),false);
			manager.persist(date4);
			Sdate date5 = new Sdate(new Date(2019,12,30),false);
			manager.persist(date5);

			dateProposees1.add(date0);
			dateProposees1.add(date2);
			dateProposees1.add(date5);

			dateProposees2.add(date1);
			dateProposees2.add(date3);

			dateProposees3.add(date4);
			dateProposees3.add(date5);


			Lieu lieu1 = new Lieu("Chine");
			manager.persist(lieu1);

			Lieu lieu2 = new Lieu("Togo");
			manager.persist(lieu2);

			Lieu lieu3 = new Lieu("Benin");
			manager.persist(lieu3);

			lieuProposees1.add(lieu1);
			lieuProposees1.add(lieu2);
			lieuProposees1.add(lieu3);

			lieuProposees2.add(lieu3);
			lieuProposees2.add(lieu2);


			Reunion reunion1 = new Reunion("première réunion", "ça va le faire", personneAdmin, dateProposees1,lieuProposees1);
			manager.persist(reunion1);
			Reunion reunion2 = new Reunion("deuxième réunion", "ce n'était pas si nul la première",personneAdmin,dateProposees2,lieuProposees1);
			manager.persist(reunion2);
			Reunion reunion3 = new Reunion("Autre réunion", "je m'essaye aussi",personneA,dateProposees3,lieuProposees2);
			manager.persist(reunion3);

			/**
			 * Sondage pour la réunion 1
			 */
			ElementSondageDate elementSondageDateA1 = new ElementSondageDate(date0,personneA,reunion1);
			manager.persist(elementSondageDateA1);
			ElementSondageDate elementSondageDateA2 = new ElementSondageDate(date5,personneA,reunion1);
			manager.persist(elementSondageDateA2);
			ElementSondageDate elementSondageDateB = new ElementSondageDate(date0,personneB,reunion1);
			manager.persist(elementSondageDateB);


			/**
			 * Sondage pour la réunion 2
			 */
			ElementSondageLieu elementSondageLieuA = new ElementSondageLieu(lieu1,personneA,reunion2);
			manager.persist(elementSondageLieuA);
			ElementSondageLieu elementSondageLieuB1 = new ElementSondageLieu(lieu2,personneB,reunion2);
			manager.persist(elementSondageLieuB1);

			/**
			 * Sondage pour la réunion 3
			 */
			ElementSondageDateLieu elementSondageDateLieuAd = new ElementSondageDateLieu(lieu3,date5,personneAdmin,reunion3);
			manager.persist(elementSondageDateLieuAd);
			ElementSondageDateLieu elementSondageDateLieuB2 = new ElementSondageDateLieu(lieu3,date5,personneB,reunion3);
			manager.persist(elementSondageDateLieuB2);


		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		/*
    test
     */


		/**
		 * la Date à choisir pour la réunion
		 */
/*
		List id_dateChoisie;
		id_dateChoisie = manager.createQuery("select e.dateChoisie.idDate, count(e.dateChoisie.idDate) " +
				"as nbr from ElementSondage e where e.mareunion.idReunion = 1 group by e.dateChoisie.idDate " +
				"order by nbr desc ").getResultList();

		long var= 0;

		for(Object ligne : id_dateChoisie)
		{
			Object[] l = (Object[]) ligne;
			Long i = (Long) l[0];

			if(var <= i){ var= i;}
		}
		System.out.println("l'idTest   "+var);

		Object dateChoisie =  manager.createQuery("select d.date from Sdate d where d.idDate =:var ").setParameter("var",var).getSingleResult();

		System.out.println("date  "+dateChoisie.toString());

*/
		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
	}


}
