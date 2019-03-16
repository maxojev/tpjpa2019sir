package servlet;

import jpa.EntityManagerHelper;
import jpa.Lieu;
import jpa.Sdate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@WebServlet(name="login",
        urlPatterns={"/LoginAction"})
public class LoginAction extends HttpServlet {
    Boolean reunion;

    @PersistenceContext
    private EntityManager manager;

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");


        PrintWriter out = response.getWriter();

        String name =request.getParameter("name");
        String firstname =request.getParameter("firstname");
        String mail =request.getParameter("mail");


        if(request.getParameter("reunion") != null) {
            if (request.getParameter("reunion").equalsIgnoreCase("on")) {
                reunion = true;
            }
        } else {
            reunion = false;
        }

        if(!validateLogin(name,firstname,mail)){
            if(reunion){
                RequestDispatcher rd=request.getRequestDispatcher("AddReunion");
                rd.forward(request,response);
            }else {
                RequestDispatcher rd=request.getRequestDispatcher("ParticipeSondage");
                rd.forward(request,response);
            }
        }
        else{
            out.print("Error your are not exist in database (-,-)");
            RequestDispatcher rd=request.getRequestDispatcher("/login.html");
            rd.include(request,response);
        }

        out.close();
    }

    public boolean validateLogin(String name, String firstname, String mail){

        manager = EntityManagerHelper.getEntityManager();


        Query query = manager.createNativeQuery(  "SELECT * FROM PERSONNE where nom =? and prenom =? and mail =?");
        query.setParameter(1, name)
                .setParameter(2, firstname)
                .setParameter(3, mail);

        List<Integer> rs = query.getResultList();

        return rs.isEmpty();
    }

}
