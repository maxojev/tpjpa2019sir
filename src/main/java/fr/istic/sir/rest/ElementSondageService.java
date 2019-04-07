package fr.istic.sir.rest;

import DAO.*;
import jpa.*;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;;
import java.util.Collection;


@Path("/ElementSondageService")
public class ElementSondageService {

    ElementSondageDao elementSondageDao = new ElementSondageDao();
    LieuDao lieuDao = new LieuDao();
    SdateDao sdateDao = new SdateDao();
    UserDao userDao = new UserDao();
    ReunionDao reunionDao = new ReunionDao();
    Personne personne;

    @POST
    @Path("/getLieuProposees")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<Lieu> getAllLieuProposeForOneReunion(JSONObject reuElement) throws JSONException {

       Reunion r=  reunionDao.getOneReunion(reuElement.getLong("idReunion"));
       return r.getLieuProposes();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getDateProposees")
    public Collection<Sdate> getAllDateProposeForOneReunion(JSONObject reuElement) throws JSONException {

        Reunion r = reunionDao.getOneReunion(reuElement.getLong("idReunion"));
        return r.getDatesProposees();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addSondage")
    public void addSondage(JSONObject sondage) throws JSONException {


        Reunion r = reunionDao.getOneReunion(sondage.getLong("idReunion"));

        personne = new Personne(sondage.getJSONObject("participant").getString("nom"), sondage.getJSONObject("participant").getString("prenom"), sondage.getJSONObject("participant").getString("mail"));

        Personne participant = userDao.getUser(personne);


        if (!r.getDatesProposees().isEmpty() && !r.getLieuProposes().isEmpty()) {

            Sdate datechoisie = sdateDao.getDateById(sondage.getLong("idDatechoisie"));
            Lieu lieuchoisie = lieuDao.getLieuById(sondage.getLong("idLieuchoisie"));

           elementSondageDao.saveDL(new ElementSondageDateLieu(lieuchoisie, datechoisie, participant, r));

        } else if (!r.getDatesProposees().isEmpty() && r.getLieuProposes().isEmpty()) {

            Sdate datechoisie = sdateDao.getDateById(sondage.getLong("idDatechoisie"));
            elementSondageDao.saveD(new ElementSondageDate(datechoisie, participant, r));

        } else if (r.getDatesProposees().isEmpty() && !r.getLieuProposes().isEmpty()) {

            Lieu lieuchoisie = lieuDao.getLieuById(sondage.getLong("idLieuchoisie"));
            elementSondageDao.saveL(new ElementSondageLieu(lieuchoisie, participant, r));
        }


    }
}
