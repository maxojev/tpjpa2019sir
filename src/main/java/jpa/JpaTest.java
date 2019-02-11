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


			Reunion reunion1 = new Reunion("première réunion", "ça va le faire", personneAdmin, dateProposees1);
			manager.persist(reunion1);
			Reunion reunion2 = new Reunion("deuxième réunion", "ce n'était pas si nul la première",personneAdmin,dateProposees2);
			manager.persist(reunion2);
			Reunion reunion3 = new Reunion("Autre réunion", "je m'essaye aussi",personneA,dateProposees3);
			manager.persist(reunion3);

			/**
			 * Sondage pour la réunion 1
			 */
			ElementSondage elementSondageA1 = new ElementSondage(date0,personneA,reunion1);
			manager.persist(elementSondageA1);
			ElementSondage elementSondageA2 = new ElementSondage(date5,personneA,reunion1);
			manager.persist(elementSondageA2);
			ElementSondage elementSondageB = new ElementSondage(date0,personneB,reunion1);
			manager.persist(elementSondageB);


			/**
			 * Sondage pour la réunion 2
			 */
			ElementSondage elementSondageA = new ElementSondage(date1,personneA,reunion2);
			manager.persist(elementSondageA);
			ElementSondage elementSondageB1 = new ElementSondage(date1,personneB,reunion2);
			manager.persist(elementSondageB1);

			/**
			 * Sondage pour la réunion 3
			 */
			ElementSondage elementSondageAd = new ElementSondage(date5,personneAdmin,reunion3);
			manager.persist(elementSondageAd);
			ElementSondage elementSondageB2 = new ElementSondage(date5,personneB,reunion3);
			manager.persist(elementSondageB2);


		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		/**
		 * la Date à choisir pour la réunion
		 */

		List id_dateChoisie;
		 id_dateChoisie = manager.createQuery("select e.dateChoisie, count(e.dateChoisie) " +
				 "as nbr from ElementSondage e where e.mareunion.idReunion = 1 group by e.dateChoisie ").getResultList();

				 //"order by nbr asc ").getFirstResult();

	/*	 for(Object o : id_dateChoisie){
		 	Object [] ligne = (Object[]) o;
			 System.out.println(ligne[1].toString());
		 }
*/

		 System.out.println("l'idTest   "+id_dateChoisie.toString());
/*
		select max (nbr) from
				(select datechoisie_iddate, count(datechoisie_iddate) as nbr
						from elementsondage
						where mareunion_idreunion = 7
						group by datechoisie_iddate);

		select datechoisie_iddate, count(datechoisie_iddate) as nbr
		from elementsondage
		where mareunion_idreunion = 7
		group by datechoisie_iddate
		order by nbr desc
		limit 1

*/


		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
	}

	public void DateAChoisir(){
		/*List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
		System.out.println("num of employess:" + resultList.size());
		for (Employee next : resultList) {
			System.out.println("next employee: " + next);
*/
		}


}
