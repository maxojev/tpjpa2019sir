package fr.istic.sir.rest;

import DAO.ReunionDao;
import DAO.UserDao;
import jpa.Lieu;
import jpa.Personne;
import jpa.Reunion;
import jpa.Sdate;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Path("/ReunionService")
public class ReunionService {

    Reunion reunion;
    ReunionDao reunionDao =new ReunionDao();
    UserDao userDao=new UserDao();
    Collection<Sdate> datesProposees =new HashSet<Sdate>();
    Collection<Lieu> lieuProposees = new HashSet<Lieu>();
    List<Reunion> lesPropositionsEnCours = new ArrayList<Reunion>();


    /**
     * Ajout d'une proposition de réunion par une personne avec un set de dates possibles
     * et/ou un set de lieux possibles. Le choix du type de proposition de réunion est controlé
     * par une variable option
     * @param reu
     * @return
     * @throws JSONException
     * @throws ParseException
     */
    @POST
    @Path("/addReunion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reunion addReunion(JSONObject reu) throws JSONException, ParseException {

        if(reu.getString("reunionType").equalsIgnoreCase("1")){

            datesProposees= createSetDateProposeForReunion(reu);
            lieuProposees=  createSetLieuProposeForReunion(reu);

        }else if(reu.getString("reunionType").equalsIgnoreCase("2")){

            datesProposees= createSetDateProposeForReunion(reu);
            lieuProposees=null;

        }else if(reu.getString("reunionType").equalsIgnoreCase("0")){

            lieuProposees=  createSetLieuProposeForReunion(reu);
            datesProposees=null;
        }

//        personne = new Personne(reu.getJSONObject("createur").getString("nom"),reu.getJSONObject("createur").getString("prenom"),reu.getJSONObject("createur").getString("mail"));

        Personne p= userDao.getUserById(reu.getLong("idPersonne"));

        System.err.println(reu.toString());

        reunion = new Reunion(reu.getJSONObject("reunion").optString("intitule"),reu.getJSONObject("reunion").optString("resume"),p,datesProposees,lieuProposees);

        return reunionDao.addReunion(reunion);
    }


    /**
     * Retourne la liste de toutes les réunions présentes dans la BD. en fonction de la valeur des
     * champs dateChoisie et lieuChoisie une réunion est considérée comme créée ou proposée
     * @return
     */
    @GET
    @Path("/getAllReunion")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reunion> getAllReunion(){

        return reunionDao.getAllReunion();
    }


    /**
     * retourne une réunion donnée en fonction de son iD
     * @param reunion
     * @return
     * @throws JSONException
     */
    @POST
    @Path("/getOneReunion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reunion getOneReunion(JSONObject reunion) throws JSONException {

        return reunionDao.getOneReunion(reunion.getLong("idReunion"));
    }


    /**
     * Renseigne les champs dateChoisie et/ou lieuChoisie en fonction du résultat des sondages
     * @param reunion
     * @return
     * @throws JSONException
     */
    @POST
    @Path("/createReunionAfterSondage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
   public Reunion createReunionAfterSondage(JSONObject reunion) throws JSONException {

        Reunion r = reunionDao.getOneReunion(reunion.getLong("idReunion"));

        if (!r.getDatesProposees().isEmpty() && !r.getLieuProposes().isEmpty()) {
            r.setDateValidee(reunionDao.getDateChoisie(r.getIdReunion()));
            r.setLieuValide(reunionDao.getLieuChoisie((r.getIdReunion())));
        } else if (r.getDatesProposees().isEmpty() && !r.getLieuProposes().isEmpty()) {
            r.setLieuValide(reunionDao.getLieuChoisie((r.getIdReunion())));
        } else if (!r.getDatesProposees().isEmpty() && r.getLieuProposes().isEmpty()) {
            r.setDateValidee(reunionDao.getDateChoisie(r.getIdReunion()));
        }

        return reunionDao.addReunion(r);
    }

    @POST
    @Path("/getMyAllReunions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Reunion> getMyAllReunions(JSONObject reunion) throws JSONException {

        Personne personne = userDao.getUserById(reunion.getLong("idUser"));

        return personne.getMesReunionsCreees();
    }

    private Date stringToDate (String value) throws ParseException {
        DateFormat df= new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date d = df.parse(value);
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    private Boolean stringCheckBoxToBoolean(String value){

        return !(value!="");
    }

    private Collection<Sdate> createSetDateProposeForReunion(JSONObject reu) throws JSONException, ParseException {


        Collection<Sdate> d =new HashSet<Sdate>();

        Date date1 = stringToDate(reu.getJSONObject("reunion").getString("date1"));
        Boolean pause1 = stringCheckBoxToBoolean(reu.getJSONObject("reunion").optString("pause1"));
        d.add(new Sdate(date1,pause1));

        Date date2 = stringToDate(reu.getJSONObject("reunion").getString("date2"));
        Boolean pause2 = stringCheckBoxToBoolean(reu.getJSONObject("reunion").optString("pause2"));
        d.add(new Sdate(date2,pause2));

        Date date3 = stringToDate(reu.getJSONObject("reunion").getString("date3"));
        Boolean pause3 = stringCheckBoxToBoolean(reu.getJSONObject("reunion").optString("pause3"));
        d.add(new Sdate(date3,pause3));

        return d;
    }

    private Collection<Lieu> createSetLieuProposeForReunion(JSONObject reu) throws JSONException {


        Collection<Lieu> l = new HashSet<Lieu>();

        l.add(new Lieu(reu.getJSONObject("reunion").getString("lieu1")));
        l.add(new Lieu(reu.getJSONObject("reunion").getString("lieu2")));
        l.add(new Lieu(reu.getJSONObject("reunion").getString("lieu3")));

        return l;
    }
}
