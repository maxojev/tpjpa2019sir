package servlet;

import jpa.EntityManagerHelper;
import jpa.Lieu;
import jpa.Personne;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="addlieu",
        urlPatterns={"/AddLieu"})
public class AddLieu extends HttpServlet {
    Lieu lieu ;
    @PersistenceContext
    private EntityManager manager;

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        lieu = new Lieu(request.getParameter("lieu"));

//        PrintWriter out = response.getWriter();

        //Insertion dans la table Lieu
        insertInToLieu(lieu);

        //Redirection vers la page d'insertion
        response.sendRedirect("http://localhost:8080/AddLieu.html");

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


    public void insertInToLieu(Lieu lieu){

        manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            manager.createNativeQuery("INSERT INTO Lieu (nomlieu) VALUES (?)")
                    .setParameter(1, lieu.getNomLieu())
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

    }
}
