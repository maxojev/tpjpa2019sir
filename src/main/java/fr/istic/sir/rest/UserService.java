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

    /**
     * Cette méthode permet de vérifier qu'un utilisateur voulant utiliser un service existe
     * dans la base et retourne l'Id de cette personne après vérification. Il est requis pour
     * toute utilisation.
     *
     * @param user
     * @return Personne
     * @throws JSONException
     */

    @POST
    @Path("/valideLogin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Personne ValidLogin(JSONObject user) throws JSONException {

        userDao =new UserDao();
        personne = new Personne(user.getString("nom"),user.getString("prenom"),user.getString("mail"));
        personne.setIdPersonne(userDao.validateLogin(personne));
        return personne;
    }

    /**
     *Cette méthode sera utilisée par un admin pour ajouter un utilisateur
     * dans la base de données
     * @param user
     * @return
     * @throws JSONException
     */

    @POST
    @Path("/addUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Personne AddUser(JSONObject user) throws JSONException {

        personne = new Personne(user.getString("nom"),user.getString("prenom"),user.getString("mail"));
        return(userDao.addUser(personne));
    }


    /**
     * Cette méthode tous les utilisateurs présents dans la BD
     * @return
     */
    @GET
    @Path("/getAllUser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> getAllUsers(){
        return userDao.getAllUser();
    }

}
