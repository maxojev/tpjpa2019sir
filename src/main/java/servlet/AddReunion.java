package servlet;

import jpa.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@WebServlet(name="AddReunion",
        urlPatterns={"/AddReunion"})
public class AddReunion extends HttpServlet {
    Personne personne;
    Set<Sdate> dateProposees;
    Set<Lieu> lieuProposees;


    @PersistenceContext
    private EntityManager manager;

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {


        personne = new Personne(request.getParameter("name"),request.getParameter("firstname"),request.getParameter("mail"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        String lieu ="";
        String l = "";

        for (int i=0; i<lieuxPossibles().size(); i++){
          lieu = lieuxPossibles().get(i);
            l = l + "<option>"+lieu+"</option>";
        }


        Date dat;
        String d = "";

        for (int i=0; i<datesPossibles().size(); i++){
            dat = datesPossibles().get(i);
            d = d + "<option>"+dat+"</option>";
        }

        out.println("<HTML>\n<BODY>\n"+
                "<form method=\"get\" action = \"/AddReunion\">\n" +

                    "selectinnez les lieux possibles"+ "<select name=\"lieux\" multiple>" + l + "</select>"+"<BR>"+"<BR>"+


                    "selectinnez les dates possibles"+ "<select name=\"dates\" multiple>" + d + "</select>"+"<BR>"+

                    "Ajoutez l'intitulé de la réunion" + "<INPUT type=text name=intitule><BR>"+
                    "Ajoutez le resumé de la réunion" + "<INPUT type=text name=resume><BR>"+

                    "<INPUT type=submit value=Send>"+

                "</form>");

    }


    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String[] lieux = request.getParameterValues("lieux");

        String[] dates = request.getParameterValues("dates");

        String resume = request.getParameter("resume");

        String intitule = request.getParameter("intitule");


        dateProposees = CreateDateAndLieuFromForm(dates);

        for(int i=0; i<lieux.length;i++){
            Lieu l = new Lieu(lieux[i]);
            lieuProposees.add(l);
        }

        traitement(dateProposees,lieuProposees,intitule,resume);

        response.sendRedirect("http://localhost:8080/login.html");

    }


    private void traitement(Set<Sdate> dateProposees, Set<Lieu> lieuProposees, String intitule, String resume){
        manager = EntityManagerHelper.getEntityManager();

        Reunion reunion = new Reunion(intitule,resume,personne,dateProposees,lieuProposees);
        manager.persist(reunion);
    }

    private Set<Sdate> CreateDateAndLieuFromForm(String[] d){
        Set<Sdate> dateProposees = new HashSet<Sdate>();
        manager = EntityManagerHelper.getEntityManager();

        for (int i=0; i<d.length; i++){

             List rs = manager.createNativeQuery("SELECT * FROM Sdate where madate=?")
                     .setParameter(1,d[i])
                     .getResultList();

            for(Object lign : rs)
            {
                Object[] dat = (Object[]) lign;
                java.sql.Date l = (java.sql.Date) dat[1];
                Boolean p = (Boolean) dat[2];
                dateProposees.add(new Sdate(l,p));
            }
        }
        return dateProposees;
    }

    public List<String> lieuxPossibles(){

        manager = EntityManagerHelper.getEntityManager();

        List<String> lieuxPossibles = new ArrayList<String>();

        List rs = manager.createNativeQuery("SELECT * FROM LIEU")
                .getResultList();

        for(Object ligne : rs)
        {
            Object[] lieu = (Object[]) ligne;
            String  l = (String) lieu[1];
            lieuxPossibles.add(l);
        }
        return lieuxPossibles;
    }

    public List<Date> datesPossibles(){
        manager = EntityManagerHelper.getEntityManager();
        List<Date> datesPossibles = new ArrayList<Date>();

        List rs = manager.createNativeQuery("SELECT * FROM Sdate")
                .getResultList();

        for(Object lign : rs)
        {
            Object[] dat = (Object[]) lign;
            Date l = (Date) dat[1];
            datesPossibles.add(l);
        }
        return datesPossibles;
    }
}
