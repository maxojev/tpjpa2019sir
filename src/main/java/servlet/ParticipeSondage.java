package servlet;

import jpa.EntityManagerHelper;
import jpa.Sdate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//ddddddd
@WebServlet(name="adddate",
        urlPatterns={"/AddDate"})
public class ParticipeSondage extends HttpServlet {
    Boolean pause;
    Sdate sdate ;
    @PersistenceContext
    private EntityManager manager;

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String d = request.getParameter("date");
        DateFormat df= new SimpleDateFormat("yyyy-mm-dd");
        System.out.println("max");
        System.out.println(request.getParameter("pause"));

       if(request.getParameter("pause") != null) {
           if (request.getParameter("pause").equalsIgnoreCase("on")) {
               pause = true;
           }
       } else {
           pause = false;
       }

        try {
            Date dates = df.parse(d);
            java.sql.Date date = new  java.sql.Date(dates.getTime());
            sdate =new Sdate(date,pause);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //Insertion dans la table Personne
        insertInToSdate(sdate);

        //Redirection vers la page d'insertion
        response.sendRedirect("http://localhost:8080/AddDate.html");
    }


    public void insertInToSdate(Sdate sdate){

        manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            manager.createNativeQuery("INSERT INTO SDATE (madate, pause) VALUES (?,?)")
                    .setParameter(1, sdate.getDate())
                    .setParameter(2, sdate.getPause())
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
    }
}
