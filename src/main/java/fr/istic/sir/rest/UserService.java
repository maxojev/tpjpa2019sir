package fr.istic.sir.rest;

import DAO.UserDao;
import jpa.Personne;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/UserService")
public class UserService {

    Personne personne ;
    UserDao userDao = new UserDao();

    @POST
    @Path("/valideLogin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean ValidLogin(JSONObject user) throws JSONException {

        userDao =new UserDao();
        personne = new Personne(user.getString("nom"),user.getString("prenom"),user.getString("mail"));
        personne.setIdPersonne(1);

        System.out.println(personne.getMail());
        return userDao.validateLogin(personne);
    }

    @POST
    @Path("/addUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Personne AddUser(JSONObject user) throws JSONException {

        personne = new Personne(user.getString("nom"),user.getString("prenom"),user.getString("mail"));
        return(userDao.addUser(personne));
    }


    @GET
    @Path("/getAllUser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> getAllUsers(){

       return userDao.getAllUser();
    }

}
