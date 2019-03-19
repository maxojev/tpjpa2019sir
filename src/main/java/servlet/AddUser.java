package servlet;

import jpa.EntityManagerHelper;
import jpa.Personne;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="adduser",
        urlPatterns={"/AddUser"})
public class AddUser extends HttpServlet {
    Personne personne ;
    @PersistenceContext
    private EntityManager manager;

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        personne = new Personne(request.getParameter("name"),request.getParameter("firstname"),request.getParameter("mail"));

//        PrintWriter out = response.getWriter();

        //Insertion dans la table Personne
        insertInToPersonne(personne);

        //Redirection vers la page d'insertion
        response.sendRedirect("http://localhost:8080/AddUser.html");

/*
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +
                " <LI>Nom: "
                + personne.getNom() + "\n" +
                " <LI>Prenom: "
                + personne.getPrenom()+ "\n" +
                " <LI>Age: "
                + personne.getMail() + "\n" +
                "</UL>\n" +
                "</BODY></HTML>");



        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +
                " <LI>Nom: "
                + request.getParameter("name") + "\n" +
                " <LI>Prenom: "
                + request.getParameter("firstname") + "\n" +
                " <LI>Age: "
                + request.getParameter("mail") + "\n" +
                "</UL>\n" +
                "</BODY></HTML>");
                */
    }


    public void insertInToPersonne(Personne personne){

        manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            manager.createNativeQuery("INSERT INTO Personne (mail, nom, prenom) VALUES (?,?,?)")
                    .setParameter(1, personne.getMail())
                    .setParameter(2, personne.getNom())
                    .setParameter(3, personne.getPrenom())
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

//        manager.close();
//        EntityManagerHelper.closeEntityManagerFactory();
    }
}
