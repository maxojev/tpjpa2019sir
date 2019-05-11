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

        Personne participant = userDao.getUserById(sondage.getLong("idparticipant"));

        if (!r.getDatesProposees().isEmpty() && !r.getLieuProposes().isEmpty()) {

            Sdate datechoisie = sdateDao.getDateById(sondage.getLong("idD"));
            Lieu lieuchoisie = lieuDao.getLieuById(sondage.getLong("idL"));

           elementSondageDao.saveDL(new ElementSondageDateLieu(lieuchoisie, datechoisie, participant, r));

        } else if (!r.getDatesProposees().isEmpty() && r.getLieuProposes().isEmpty()) {

            Sdate datechoisie = sdateDao.getDateById(sondage.getLong("idD"));
            elementSondageDao.saveD(new ElementSondageDate(datechoisie, participant, r));

        } else if (r.getDatesProposees().isEmpty() && !r.getLieuProposes().isEmpty()) {

            Lieu lieuchoisie = lieuDao.getLieuById(sondage.getLong("idL"));
            elementSondageDao.saveL(new ElementSondageLieu(lieuchoisie, participant, r));
        }


    }
}
